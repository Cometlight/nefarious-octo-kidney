package at.fhv.itb5c.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.DepartmentConverter;
import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.IDepartmentFactory;
import at.fhv.itb5c.model.PersistenceFacade;
import at.fhv.itb5c.model.entity.Department;

public class DepartmentFactory implements IDepartmentFactory {

	@Override
	public List<IDepartment> getAllDepartments() throws Exception {
		List<Department> departments = PersistenceFacade.getInstance().getAll(Department.class);
		List<IDepartment> departmentsConverted = departments.stream().map(DepartmentConverter::toDTO).collect(Collectors.toList());
		return departmentsConverted;
	}

	@Override
	public IDepartment getDepartment(Long id) throws Exception {
		if(id == null) {
			return null;
		}
		
		Department department = PersistenceFacade.getInstance().getById(Department.class, id);
		return DepartmentConverter.toDTO(department);
	}
}