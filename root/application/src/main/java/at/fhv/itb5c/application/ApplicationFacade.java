package at.fhv.itb5c.application;

import java.util.Collection;
import java.util.List;

import at.fhv.itb5c.application.converter.ConverterDepartmentDTO;
import at.fhv.itb5c.application.converter.ConverterUserDTO;
import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.PersistenceFacade;
import at.fhv.itb5c.model.entity.Department;
import at.fhv.itb5c.model.entity.User;

public class ApplicationFacade implements ILogger {
	public UserDTO createUser() {
		return new UserDTO();
	}
	
	public UserDTO getUserById(Long id) {
		User user = PersistenceFacade.getInstance().getById(User.class, id);
		return ConverterUserDTO.toDTO(user);
	}

	public Collection<UserDTO> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid) {
		return ConverterUserDTO.toDTO(PersistenceFacade.getInstance().findUsers(firstName, lastName, departmentId, membershipFeePaid));
	}

	public Collection<UserDTO> findUsersSimple(String name) {
		return ConverterUserDTO.toDTO(PersistenceFacade.getInstance().findUsersSimple(name));
	}

	public UserDTO saveUser(UserDTO user) {
		try {
			return ConverterUserDTO.toDTO(PersistenceFacade.getInstance().saveOrUpdate(ConverterUserDTO.toEntity(user)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	public DepartmentDTO getDepartmentById(Long id) {
		Department entity = PersistenceFacade.getInstance().getById(Department.class, id);
		return ConverterDepartmentDTO.toDTO(entity);
	}
	
	public Collection<DepartmentDTO> getAllDepartments() {
		List<Department> departments = PersistenceFacade.getInstance().getAll(Department.class);
		return ConverterDepartmentDTO.toDTO(departments);
	}
	
	public DepartmentDTO saveDepartment(DepartmentDTO department) {
		Department entity = ConverterDepartmentDTO.toEntity(department);
		try {
			entity = PersistenceFacade.getInstance().saveOrUpdate(entity);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		return ConverterDepartmentDTO.toDTO(entity);
	}
}
