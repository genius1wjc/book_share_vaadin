package logging;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.annotation.Nonnull;

public final class LogUtil {

	private static final String LOGGING_FOLDER = "application-logs";

	private LogUtil() {
		// Disable instantiation
	}

	public static Logger getLogger(@Nonnull final String className) {
		new File(LOGGING_FOLDER).mkdir();
		try {
			Logger logger = Logger.getLogger(className);
			FileHandler handler = new FileHandler(LOGGING_FOLDER + "/" + className + ".txt");
			handler.setFormatter(new LogFormatter());
			logger.addHandler(handler);
			return logger;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}