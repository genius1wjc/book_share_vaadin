package my.vaadin.app;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import logging.LogFormatter;
import view.LocationView;
import view.LoginView;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(MyUI.class.getName());
	public static final String LOCATION_VIEW = "location_view";

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		try {
			new File("logs").mkdir();
			FileHandler handler = new FileHandler("logs/Logging.txt");
			handler.setFormatter(new LogFormatter(MyUI.class.getName()));
			LOGGER.addHandler(handler);
			LOGGER.log(Level.SEVERE, "Hello world!");

			getPage().setTitle("Book Share Ninja");
			Navigator navigator = new Navigator(this, this);
			// "" is required to make LoginView as the starting view
			navigator.addView("", new LoginView());
			navigator.addView(LOCATION_VIEW, new LocationView());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Encountered exception in MyUI init:", e);
		}
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}
}
