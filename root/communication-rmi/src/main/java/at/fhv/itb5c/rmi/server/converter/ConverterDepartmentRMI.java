package at.fhv.itb5c.rmi.server.converter;

import java.rmi.RemoteException;

import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.rmiclasses.DepartmentRMI;

public class ConverterDepartmentRMI implements ILogger {
	public static IDepartmentRMI toRMI(DepartmentDTO dto) {
		IDepartmentRMI rmi = null;
		try {
			rmi = new DepartmentRMI();
			rmi.setId(dto.getId());
			rmi.setVersion(dto.getVersion());
			rmi.setHeadId(dto.getHeadId());
			rmi.setName(dto.getName());
			rmi.setTypeOfSport(dto.getTypeOfSport());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return rmi;
	}
	
	public static DepartmentDTO toDTO(IDepartmentRMI rmi) {
		DepartmentDTO dto = null;
		try {
			dto = new DepartmentDTO();
			dto.setId(rmi.getId());
			dto.setVersion(rmi.getVersion());
			dto.setHeadId(rmi.getHeadId());
			dto.setName(rmi.getName());
			dto.setTypeOfSport(rmi.getTypeOfSport());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return dto;
	}
}
