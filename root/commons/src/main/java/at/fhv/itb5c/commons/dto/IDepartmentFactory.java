package at.fhv.itb5c.commons.dto;

import java.util.List;

public interface IDepartmentFactory {
	public List<? extends IDepartment> getAllDepartments() throws Exception;
	public IDepartment getDepartment(Long id) throws Exception;
}
