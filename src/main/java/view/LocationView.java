package view;

import java.util.Collections;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import model.Location;
import service.LocationService;

public class LocationView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public LocationView() {
		setSizeFull();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Location location = LocationService.getCityResponse();

		Grid<Location> grid = new Grid<>();
		grid.setItems(Collections.singletonList(location));
		grid.addColumn(Location::getCountry).setCaption("Country");
		grid.addColumn(Location::getState).setCaption("State");
		grid.addColumn(Location::getCity).setCaption("City");
		grid.addColumn(Location::getPostal).setCaption("Postal");

		addComponent(grid);
	}
}
