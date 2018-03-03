package logging;

import java.util.logging.FileHandler;
import javax.annotation.Nonnull;

import my.vaadin.app.MyUI;

public final class Logger {

	private static final String LOGGING_FOLDER = "app-logs";

	private Logger() {
		// Disable instantiation
	}

	public static java.util.logging.Logger getLogger(@Nonnull final String className) {
		try {
			java.util.logging.Logger logger = java.util.logging.Logger.getLogger(className);
			FileHandler handler = new FileHandler(LOGGING_FOLDER + "/Logs.txt");
			handler.setFormatter(new LogFormatter(MyUI.class.getName()));
			logger.addHandler(handler);
			return logger;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}