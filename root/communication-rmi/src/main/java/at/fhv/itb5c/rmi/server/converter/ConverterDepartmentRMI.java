package at.fhv.itb5c.rmi.server.converter;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.stream.Collectors;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.rmiclasses.DepartmentRMI;

public class ConverterDepartmentRMI implements ILogger {
	public static IDepartmentRMI toRMI(DepartmentDTO department) {
		if(department == null){
			return null;
		}
		
		IDepartmentRMI rmi = null;
		try {
			rmi = new DepartmentRMI();
			rmi.setId(department.getId());
			rmi.setVersion(department.getVersion());
			rmi.setHeadId(department.getHeadId());
			rmi.setName(department.getName());
			rmi.setTypeOfSport(department.getTypeOfSport());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return rmi;
	}
	
	public static Collection<IDepartmentRMI> toRMI(Collection<DepartmentDTO> departments) {
		if(departments == null){
			return null;
		}
		
		return departments.stream().map(ConverterDepartmentRMI::toRMI).collect(Collectors.toList());
	}
	
	public static DepartmentDTO toDTO(IDepartmentRMI department) {
		if(department == null){
			return null;
		}
		
		DepartmentDTO dto = null;
		try {
			dto = new DepartmentDTO();
			dto.setId(department.getId());
			dto.setVersion(department.getVersion());
			dto.setHeadId(department.getHeadId());
			dto.setName(department.getName());
			dto.setTypeOfSport(department.getTypeOfSport());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return dto;
	}
	
	public static Collection<DepartmentDTO> toDTO(Collection<IDepartmentRMI> departments) {
		if(departments == null) {
			return null;
		}
		
		return departments.stream().map(ConverterDepartmentRMI::toDTO).collect(Collectors.toList());
	}
}
