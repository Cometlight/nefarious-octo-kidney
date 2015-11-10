package at.fhv.itb5c.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.model.entity.Department;
import at.fhv.itb5c.model.entity.PersistableObject;
import at.fhv.itb5c.model.entity.User;


public class GetAllTest {
//  private static final String DBFILE = "testdb.odb";
//
//  @Before
//  public void setUp() throws Exception {
//    PersistenceFacade.setPersistenceUnitName(DBFILE);
//    this.addDepartment("Tennis");
//    this.addDepartment("Soccer");
//    this.addUser("Max", "Mustermann");
//    this.addUser("Klaudia", "Müller");
//    this.addUser("Frederike", "Huber");
//    this.addUser("Markus", "Susmann");
//    this.addUser("Massimo", "Maxer");
//  }
//
//  private User addUser(String firstName, String lastName) throws Exception {
//    User user = new User();
//    user.setFirstName(firstName);
//    user.setLastName(lastName);
//    return PersistenceFacade.getInstance().saveOrUpdate(user);
//  }
//
//  private Department addDepartment(String name) throws Exception {
//    Department dept = new Department();
//    dept.setName(name);
//    return PersistenceFacade.getInstance().saveOrUpdate(dept);
//  }
//
//  @After
//  public void tearDown() throws Exception {
//    PersistenceFacade.shutdown();
//    File file = new File(DBFILE);
//    if (file.exists()) {
//      file.delete();
//    }
//  }
//
//  @Test
//  public void getAllDepartments() {
//    List<Department> depts = (List<Department>)PersistenceFacade.getInstance().<Department>getAll(Department.class);
//    assertEquals(2, depts.size());
//  }
//
//  @Test
//  public void getAllUsers() {
//    List<User> users = (List<User>)PersistenceFacade.getInstance().<User>getAll(User.class);
//    assertEquals(5, users.size());
//  }
//
//  @Test
//  public void getAllObjects() {
//    List<PersistableObject> users = (List<PersistableObject>)PersistenceFacade.getInstance().<PersistableObject>getAll(PersistableObject.class);
//    assertEquals(7, users.size());
//  }
//
//  @Test
//  public void getAllForNonePersistableObject() {
//    List<PersistableObject> users = (List<PersistableObject>)PersistenceFacade.getInstance().<PersistableObject>getAll(null);
//    assertEquals(null, users);
//  }

}
