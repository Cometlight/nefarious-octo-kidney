package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.DepartmentDTO;

@Remote
public interface SaveDepartmentRemote {
	DepartmentDTO saveDepartment(String sessionId, DepartmentDTO department);
}
