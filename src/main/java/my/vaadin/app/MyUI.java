package my.vaadin.app;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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

	private static final long serialVersionUID = -768836224971950051L;

	private TextField usernameText = new TextField();
	private PasswordField passwordText = new PasswordField();
	private Button submitButton = new Button();

	private String username = "";
	private String password = "";

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		usernameText.setPlaceholder("enter username");
		usernameText.addValueChangeListener(e -> username = e.getValue());
		usernameText.setValueChangeMode(ValueChangeMode.LAZY);

		passwordText.setPlaceholder("enter password");
		passwordText.addValueChangeListener(e -> password = e.getValue());
		passwordText.setValueChangeMode(ValueChangeMode.LAZY);

		submitButton.setCaption("Submit");
		submitButton.addClickListener(e -> validUsernameAndPassword());

		final VerticalLayout layout = new VerticalLayout();
		layout.addComponents(usernameText, passwordText, submitButton);
		setContent(layout);
	}

	private void validUsernameAndPassword() {

	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 2339094934408237194L;
	}
}
