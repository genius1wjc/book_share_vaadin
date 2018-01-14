package service;

import static constant.CommonConstants.ENTITY_MANAGER_FACTORY;

import java.util.List;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Borrower;

public final class BorrowerService {

	private BorrowerService() {
		// Prevent instantiation of this class
	}

	/**
	 * Create a new Borrower.
	 */
	public static void create(long id, @Nonnull String email, @Nonnull String name, @Nonnull String username,
			@Nonnull String password) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			Borrower borrower = new Borrower(id, email, name, username, password);
			manager.persist(borrower);
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
	 * Find a borrower by username and password.
	 * 
	 * @return a List of Borrowers
	 */
	public static List<Borrower> find(@Nonnull String username, @Nonnull String password) {

		List<Borrower> borrowers = null;

		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			borrowers = manager.createQuery("SELECT b FROM Borrower b WHERE b.mUsername = 'genius1wjc'", Borrower.class).getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return borrowers;
	}

	/**
	 * Delete the existing Borrower.
	 * 
	 * @param id
	 */
	public static void delete(int id) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			Borrower borrower = manager.find(Borrower.class, id);
			manager.remove(borrower);
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
	 * Update the existing Borrower.
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
			Borrower borrower = manager.find(Borrower.class, id);
			borrower.mName = name;
			manager.persist(borrower);
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
