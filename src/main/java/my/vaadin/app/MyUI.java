package my.vaadin.app;

import javax.annotation.Nonnull;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import util.LoginUtil;

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

	private TextField usernameText = new TextField();
	private PasswordField passwordText = new PasswordField();
	private Button submitButton = new Button();
	private Label loginStatus = new Label();

	@Nonnull
	private String mUsername = "";
	@Nonnull
	private String mPassword = "";

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		usernameText.setPlaceholder("enter username");
		usernameText.setValueChangeMode(ValueChangeMode.LAZY);
		usernameText.addValueChangeListener(e -> {
			mUsername = e.getValue();
			checkAndEnableSubmitButton();
		});

		passwordText.setPlaceholder("enter password");
		passwordText.setValueChangeMode(ValueChangeMode.EAGER);
		passwordText.addValueChangeListener(e -> {
			mPassword = e.getValue();
			checkAndEnableSubmitButton();
		});

		submitButton.setCaption("Submit");
		submitButton.setEnabled(false);
		submitButton.setClickShortcut(KeyCode.ENTER);
		submitButton.addClickListener(e -> {
			boolean success = LoginUtil.validateUsernameAndPassword(mUsername, mPassword);
			if (!success) {
				loginStatus.setValue("Login failed, try again");
			} else {
				loginStatus.setValue("Login succeeded");
			}
			loginStatus.setVisible(true);
		});

		loginStatus.setVisible(false);

		final VerticalLayout layout = new VerticalLayout();
		layout.addComponents(usernameText, passwordText, submitButton, loginStatus);
		setContent(layout);
	}

	private void checkAndEnableSubmitButton() {
		if (!mUsername.isEmpty() && !mPassword.isEmpty()) {
			submitButton.setEnabled(true);
		}
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}
}
