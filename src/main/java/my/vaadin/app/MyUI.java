package my.vaadin.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

import model.Borrower;
import model.Student;
import util.StringUtils;

import static constant.CommonConstants.ENTITY_MANAGER_FACTORY;
import static service.StudentService.*;

import java.util.List;

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

	private String username = "";
	private String password = "";

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		usernameText.setPlaceholder("enter username");
		usernameText.setValueChangeMode(ValueChangeMode.LAZY);
		usernameText.addValueChangeListener(e -> {
			username = e.getValue();
			checkAndEnableSubmitButton();
		});

		passwordText.setPlaceholder("enter password");
		passwordText.setValueChangeMode(ValueChangeMode.LAZY);
		passwordText.addValueChangeListener(e -> {
			password = e.getValue();
			checkAndEnableSubmitButton();
		});

		submitButton.setCaption("Submit");
		submitButton.setEnabled(false);
		submitButton.addClickListener(e -> validateUsernameAndPassword());

		final VerticalLayout layout = new VerticalLayout();
		layout.addComponents(usernameText, passwordText, submitButton);
		setContent(layout);
	}

	private void checkAndEnableSubmitButton() {
		if (!StringUtils.isNullOrEmpty(username) && !StringUtils.isNullOrEmpty(password)) {
			submitButton.setEnabled(true);
		}
	}

	private void validateUsernameAndPassword() {
		try {
			List<Borrower> students = null;

			EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
			EntityTransaction transaction = null;

			try {
				transaction = manager.getTransaction();
				transaction.begin();

				students = manager.createQuery("SELECT s FROM Borrower s", Borrower.class).getResultList();

				transaction.commit();
			} catch (Exception ex) {
				if (transaction != null) {
					transaction.rollback();
				}
				ex.printStackTrace();
			} finally {
				manager.close();
			}
			if (students != null) {
				for (Borrower student : students) {
					System.out.println(student);
				}
			}

		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();

		} finally {
			closeDb();
		}
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}
}
