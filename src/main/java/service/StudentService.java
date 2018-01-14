package service;

import static constant.CommonConstants.ENTITY_MANAGER_FACTORY;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Student;

public final class StudentService {

	private StudentService() {
		// Prevent instantiation of this class
	}

	/**
	 * Create a new Student.
	 * 
	 * @param name
	 * @param age
	 */
	public static void create(int id, String name, int age) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();

			Student stu = new Student();
			stu.id = id;
			stu.name = name;
			stu.age = age;

			manager.persist(stu);

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
	 * Read all the Students.
	 * 
	 * @return a List of Students
	 */
	public static List<Student> readAll() {

		List<Student> students = null;

		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();

			students = manager.createQuery("SELECT s FROM Student s", Student.class).getResultList();

			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return students;
	}

	/**
	 * Delete the existing Student.
	 * 
	 * @param id
	 */
	public static void delete(int id) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();

			Student stu = manager.find(Student.class, id);

			manager.remove(stu);

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
	 * Update the existing Student.
	 * 
	 * @param id
	 * @param name
	 * @param age
	 */
	public static void update(int id, String name, int age) {
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();

			Student stu = manager.find(Student.class, id);

			stu.name = name;
			stu.age = age;

			manager.persist(stu);

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
	
	public static void closeDb() {
		ENTITY_MANAGER_FACTORY.close();
	}

}
