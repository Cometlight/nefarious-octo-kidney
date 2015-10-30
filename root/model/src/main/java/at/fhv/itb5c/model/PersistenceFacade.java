package at.fhv.itb5c.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.entity.PersistableObject;
import at.fhv.itb5c.model.entity.User;

public class PersistenceFacade implements ILogger {
	private static final String DEFAULT_DBFILENAME = "persistence.odb";

	private static PersistenceFacade _instance;
	private EntityManagerFactory _entityManagerFactory;
	private EntityManager _entityManager;
	private static String _persistenceUnitName = DEFAULT_DBFILENAME;

	private PersistenceFacade() {
		_entityManagerFactory = Persistence.createEntityManagerFactory(_persistenceUnitName);
		// _entityManager needs to stay open for lazy loading to work
		_entityManager = _entityManagerFactory.createEntityManager();
	}

	public static PersistenceFacade getInstance() {
		if (_instance == null) {
			_instance = new PersistenceFacade();
		}

		return _instance;
	}

	/**
	 * Sets the database file. If this facade is already connected to a
	 * database, the connection is closed and the facade connects to the
	 * persistenceUnitName specified.
	 * 
	 * @see #DEFAULT_DBFILENAME
	 * 
	 * @param persistenceUnitName
	 *            the filename of the database
	 */
	public static void setPersistenceUnitName(String persistenceUnitName) {
		if (persistenceUnitName == null) {
			return;
		}

		_persistenceUnitName = persistenceUnitName;

		if (_instance != null) {
			shutdown();
		}

		_instance = new PersistenceFacade();
	}

	public static void shutdown() {
		if (_instance != null) {
			_instance._entityManagerFactory.close(); // Closes all
														// EntityManagers as
														// well
			_instance = null;
		}
	}

	/**
	 * Retrieves an object stored in the database by its id.
	 * 
	 * @param clazz
	 *            the entity class of the object to be retrieved
	 * @param id
	 *            the object's id
	 * @return the retrieved object, or null if it was not possible
	 */
	public <T extends PersistableObject> T getById(Class<T> clazz, long id) {
		if (clazz == null) {
			return null;
		}

		T obj = null;
		try {
			_entityManager.getTransaction().begin();
			obj = _entityManager.getReference(clazz, id);
			_entityManager.getTransaction().commit();
		} catch (Exception e) {
			obj = null;
		}

		return obj;
	}

	/**
	 * If obj is not stored in the database yet, it is persisted. If obj already
	 * exists in the database, it is updated.
	 * 
	 * @param obj
	 *            the object to persist
	 * @return reference to updated persisted object (with incremented version
	 *         number). Returns null if obj was null.
	 * 
	 * @throws Exception
	 *             thrown if save or update failed
	 */
	public <T extends PersistableObject> T saveOrUpdate(T obj) throws Exception {
		if (obj == null) {
			return null;
		}

		try {
			// The default value of id is null. If an entity is saved without
			// setting the id explicitly, the auto generated id starts with 1.
			_entityManager.getTransaction().begin();

			if (obj.getId() == null) { // This is a new entity
				_entityManager.persist(obj);
			} else { // Old entity that should be updated
				obj = _entityManager.merge(obj);
			}

			_entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		}
		return obj;
	}

	/**
	 * Removes obj from the database.
	 * 
	 * @param obj
	 *            the object to be removed
	 * @return true if obj was successfully deleted, false otherwise (incl. if
	 *         obj is null)
	 */
	public boolean delete(PersistableObject obj) {
		if (obj == null) {
			return false;
		}

		try {
			_entityManager.getTransaction().begin();
			_entityManager.remove(obj);
			_entityManager.getTransaction().commit();
		} catch (Exception e) {
			log.warn("Failed to delete " + obj, e);
			return false;
		}
		return true;
	}

	public List<User> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid) {
		List<User> resultSet;

		CriteriaBuilder cb = _entityManager.getCriteriaBuilder();

		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);

		List<Predicate> predicates = new LinkedList<>();

		if (firstName != null) {
			predicates.add(cb.like(cb.lower(root.get("_firstName")), "%" + firstName.toLowerCase() + "%"));
		}

		if (lastName != null) {
			predicates.add(cb.like(cb.lower(root.get("_lastName")), "%" + lastName.toLowerCase() + "%"));
		}

		if (departmentId != null) {
			predicates.add(cb.equal(root.get("_departmentId"), departmentId));
		}

		if (membershipFeePaid != null) {
			predicates.add(cb.equal(root.get("_membershipFeePaid"), membershipFeePaid));
		}

		query.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<User> typedQuery = _entityManager.createQuery(query);
		resultSet = typedQuery.getResultList();

		return resultSet;
	}
}
