package at.fhv.itb5c.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import at.fhv.itb5c.model.entity.PersistableObject;

public class PersistenceFacade {
	private static final String DEFAULT_DBFILENAME = "persistence.odb";

	private static PersistenceFacade _instance;
	private static EntityManagerFactory _entityManagerFactory;
	private static EntityManager _entityManager;
	private static String _persistenceUnitName = DEFAULT_DBFILENAME;

	private PersistenceFacade() {
		// Singleton
	}

	public static PersistenceFacade getInstance() {
		if (_instance == null) {
			_instance = new PersistenceFacade();
			_entityManagerFactory = Persistence.createEntityManagerFactory(_persistenceUnitName);
			// _entityManager needs to stay open for lazy loading to work
			_entityManager = _entityManagerFactory.createEntityManager();
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

		_entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	public static void shutdown() {
		if(_instance != null) {
			_entityManagerFactory.close(); // Closes all EntityManagers as well
			_entityManagerFactory = null;
			_entityManager = null;
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
	 *         number)
	 */
	public <T extends PersistableObject> T saveOrUpdate(T obj) {
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
			return null;
		}
		return obj;
	}

	/**
	 * Removes obj from the database.
	 * 
	 * @param obj
	 *            the object to be removed
	 */
	public void delete(PersistableObject obj) {
		if (obj == null) {
			return;
		}

		try {
			_entityManager.getTransaction().begin();
			_entityManager.remove(obj);
			_entityManager.getTransaction().commit();
		} catch (Exception e) {
			return;
		}
	}

}
