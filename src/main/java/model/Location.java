package model;

public class Location {
	
	private String mCountry;
	private String mState;
	private String mCity;
	private String mPostal;
	
	public Location(String country, String state, String city, String postal) {
		super();
		mCountry = country;
		mState = state;
		mCity = city;
		mPostal = postal;
	}

	public String getCountry() {
		return mCountry;
	}

	public void setCountry(String country) {
		mCountry = country;
	}

	public String getState() {
		return mState;
	}

	public void setState(String state) {
		mState = state;
	}

	public String getCity() {
		return mCity;
	}

	public void setCity(String city) {
		mCity = city;
	}

	public String getPostal() {
		return mPostal;
	}

	public void setPostal(String postal) {
		mPostal = postal;
	}
	
	
}
