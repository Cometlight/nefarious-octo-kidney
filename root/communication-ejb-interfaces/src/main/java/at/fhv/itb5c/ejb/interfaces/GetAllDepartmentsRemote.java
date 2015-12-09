package at.fhv.itb5c.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.DepartmentDTO;

@Remote
public interface GetAllDepartmentsRemote {
	Collection<DepartmentDTO> getAllDepartments(String sessionId);
}
