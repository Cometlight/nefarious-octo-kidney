package at.fhv.itb5c.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.model.entity.User;

public class PersistenceFacadeTest {
	private static final String DBFILE = "testdb.odb";

	@Before
	public void setUp() throws Exception {
		PersistenceFacade.setPersistenceUnitName(DBFILE);
	}

	@After
	public void tearDown() throws Exception {
		PersistenceFacade.shutdown();
		File file = new File(DBFILE);
		if(file.exists()) {
			file.delete();
		}
	}

	@Test
	public void testGetInstance() {
		assertNotNull(PersistenceFacade.getInstance());
	}

	@Test
	public void testGetById() {
		TestObj obj = new TestObj(42);
		obj.setId(2l);
		PersistenceFacade.getInstance().saveOrUpdate(obj);

		obj = PersistenceFacade.getInstance().getById(TestObj.class, 2l);
		assertNotNull(obj);
		assertEquals(42, obj.getValue());

		obj = PersistenceFacade.getInstance().getById(TestObj.class, 0l);
		assertNull(obj);
	}

	@Test
	public void testSaveOrUpdate() {
		TestObj obj = new TestObj(42);
		obj.setId(3l);
		PersistenceFacade.getInstance().saveOrUpdate(obj);

		obj = PersistenceFacade.getInstance().getById(TestObj.class, 3l);
		assertNotNull(obj);

		obj.setValue(1337);
		Long version = obj.getVersion();
		obj = PersistenceFacade.getInstance().saveOrUpdate(obj);
		assertNotNull(obj);
		assertEquals(1337, obj.getValue());
		assertNotEquals(version, obj.getVersion());
	}

	@Test
	public void testDelete() {
		TestObj obj = new TestObj(42);
		obj.setId(4l);
		PersistenceFacade.getInstance().saveOrUpdate(obj);

		obj = PersistenceFacade.getInstance().getById(TestObj.class, 4l);
		assertNotNull(obj);

		PersistenceFacade.getInstance().delete(obj);
		obj = PersistenceFacade.getInstance().getById(TestObj.class, 4l);
		assertNull(obj);
	}

	@Test
	public void testVersioning() {
		TestObj obj = new TestObj(42);
		obj.setId(5l);
		assertEquals(null, obj.getVersion());

		obj = PersistenceFacade.getInstance().saveOrUpdate(obj);
		assertEquals(1l, obj.getVersion().longValue());

		obj.setValue(1337);
		obj = PersistenceFacade.getInstance().saveOrUpdate(obj);
		assertEquals(2l, obj.getVersion().longValue());
	}

}
