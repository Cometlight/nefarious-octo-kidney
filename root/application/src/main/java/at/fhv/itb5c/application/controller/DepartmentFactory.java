package at.fhv.itb5c.application.controller;

import java.util.LinkedList;
import java.util.List;

import at.fhv.itb5c.application.dto.DepartmentConverter;
import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.IDepartmentFactory;
import at.fhv.itb5c.model.PersistenceFacade;
import at.fhv.itb5c.model.entity.Department;

public class DepartmentFactory implements IDepartmentFactory {

	@SuppressWarnings("unchecked")
	@Override
	public List<IDepartment> getAllDepartments() throws Exception {
		// TODO Auto-generated method stub
		List<Department> depts = (List<Department>) PersistenceFacade.getInstance().getAll(IDepartment.class);
		List<IDepartment> deptsNew = new LinkedList<IDepartment>();
		for(Department dept : depts){
			deptsNew.add(DepartmentConverter.toDTO(dept));
		}
		return deptsNew;
	}

}
