package service;

import static constant.CommonConstants.ENTITY_MANAGER_FACTORY;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Lender;

public final class LenderService {

	private LenderService() {
		// Prevent instantiation of this class
	}

	/**
	 * Create a new Lender.
	 */
	public static void create(long id, @Nonnull String email, @Nonnull String name, @Nonnull String username,
			@Nonnull String password) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			Lender lender = new Lender(id, email, name, username, password);
			manager.persist(lender);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}

	/**
	 * Find a lender by username and password.
	 * 
	 * @return a List of Lenders
	 */
	public static Lender find(@Nonnull String username, @Nonnull String password) {

		Lender lender = null;

		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager
					.createQuery("SELECT b FROM Lender b WHERE b.mUsername = :username AND b.mPassword = :password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			lender = (Lender) query.getSingleResult();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return lender;
	}

	/**
	 * Delete the existing Lender.
	 * 
	 * @param id
	 */
	public static void delete(int id) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			Lender lender = manager.find(Lender.class, id);
			manager.remove(lender);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}

	/**
	 * Update the existing Lender.
	 * 
	 * @param id
	 * @param name
	 */
	public static void update(int id, @Nonnull String name) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			Lender lender = manager.find(Lender.class, id);
			lender.mName = name;
			manager.persist(lender);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
	}
}
