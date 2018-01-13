package constant;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class CommonConstants {
	
	public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("BookShare");
	
	private CommonConstants() {
		// Prevent instantiation of this class
	}
}
