package at.fhv.itb5c.ejb;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.ejb.interfaces.GetAllDepartmentsRemote;

@Stateless
public class GetAllDepartmentsEJB implements GetAllDepartmentsRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public Collection<DepartmentDTO> getAllDepartments(String sessionId) {
		return applicationFacade.getAllDepartments(sessionId);
	}

}
