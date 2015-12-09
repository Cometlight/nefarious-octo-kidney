package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.ejb.interfaces.GetByIdDepartmentRemote;

@Stateless
public class GetByIdDepartmentEJB implements GetByIdDepartmentRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public DepartmentDTO getDepartmentById(String sessionId, Long id) {
		return applicationFacade.getDepartmentById(sessionId, id);
	}

}
