package at.fhv.itb5c.rmi.server;

import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.logging.ILogger;

public class DepartmentConverterRMI implements ILogger {
	public static IDepartmentRMI toRMI(IDepartment dept){
		if(dept==null){
			return null;
		}
		
		IDepartmentRMI deptRMI = null;
		try {
			deptRMI = new DepartmentRMI();
			deptRMI.setId(dept.getId());
			deptRMI.setVersion(dept.getVersion());
			deptRMI.setName(dept.getName());
			deptRMI.setTypeOfSport(dept.getTypeOfSport());
			deptRMI.setHead(dept.getHead());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return deptRMI;
	}
	
	public static IDepartment toDTO(IDepartmentRMI deptRMI){
		if(deptRMI==null){
			return null;
		}
		
		IDepartment dept = null;
		try {
			dept = new DepartmentDTO();
			dept.setId(deptRMI.getId());
			dept.setVersion(deptRMI.getVersion());
			dept.setName(deptRMI.getName());
			dept.setTypeOfSport(deptRMI.getTypeOfSport());
			dept.setHead(deptRMI.getHead());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return dept;
	}
}
