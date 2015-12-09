package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.DepartmentDTO;

@Remote
public interface GetByIdDepartmentRemote {
	DepartmentDTO getDepartmentById(String sessionId, Long id);
}
