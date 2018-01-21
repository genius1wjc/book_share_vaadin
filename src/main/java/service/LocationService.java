package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

import javax.annotation.Nullable;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;

import model.Location;

public final class LocationService {

	private static final String DB_LOCATION = "/Users/jiechao/eclipse-workspace/app/asset/GeoLite2-City_20180102/GeoLite2-City.mmdb";
	private static final String AWS_CHECKIP_SERVICE = "http://checkip.amazonaws.com";

	private LocationService() {
		// Prevent instantiation of this class
	}

	@Nullable
	private static String getIp() {
		try {
			URL awsCheckIp = new URL(AWS_CHECKIP_SERVICE);
			BufferedReader in = new BufferedReader(new InputStreamReader(awsCheckIp.openStream()));
			return in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Nullable
	public static Location getCityResponse() {
		try {
			File database = new File(DB_LOCATION);
			DatabaseReader dbReader = new DatabaseReader.Builder(database).build();

			InetAddress ipAddress = InetAddress.getByName(getIp());
			CityResponse response = dbReader.city(ipAddress);
			return new Location(response.getCountry().getName(), response.getMostSpecificSubdivision().getName(),
					response.getCity().getName(), response.getPostal().getCode());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
