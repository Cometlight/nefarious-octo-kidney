package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.ejb.interfaces.SaveDepartmentRemote;

@Stateless
public class SaveDepartmentEJB implements SaveDepartmentRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public DepartmentDTO saveDepartment(String sessionId, DepartmentDTO department) {
		return applicationFacade.saveDepartment(sessionId, department);
	}

}
