package at.fhv.itb5c.application.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.model.entity.Department;

/**
 * Converts between Department entity and Department DTO.
 */
public class ConverterDepartmentDTO {
	/**
	 * Converts a department entity to its respective DTO. If the specified
	 * department is null, the return value is null.
	 * 
	 * @param department
	 *            the department entity to convert to a DTO
	 * @return a DTO representing the specified department or null, if the
	 *         specified department is null
	 */
	public static DepartmentDTO toDTO(Department department) {
		if(department == null){
			return null;
		}
		
		DepartmentDTO dto = null;

		if (department != null) {
			dto = new DepartmentDTO();
			dto.setId(department.getId());
			dto.setVersion(department.getVersion());
			dto.setHeadId(department.getHeadId());
			dto.setName(department.getName());
			dto.setTypeOfSport(department.getTypeOfSport());
		}

		return dto;
	}

	/**
	 * Converts a collection of department entities to a collection of
	 * department DTO.
	 * 
	 * @param departments
	 *            a collection of department entities
	 * @return a collection of department DTOs
	 */
	public static Collection<DepartmentDTO> toDTO(Collection<Department> departments) {
		if(departments == null){
			return null;
		}
		
		return departments.stream().map(ConverterDepartmentDTO::toDTO).collect(Collectors.toList());
	}

	/**
	 * Converts the specified department DTO to a department entity. If the
	 * specified DTO is null, the returned value is null.
	 * 
	 * @param dto
	 *            the DTO to convert
	 * @return a department entity; or null, if the specified DTO was null
	 */
	public static Department toEntity(DepartmentDTO dto) {
		if(dto == null){
			return null;
		}
		
		Department entity = null;

		if (dto != null) {
			entity = new Department();
			entity.setId(dto.getId());
			entity.setVersion(dto.getVersion());
			entity.setHeadId(dto.getHeadId());
			entity.setName(dto.getName());
			entity.setTypeOfSport(dto.getTypeOfSport());
		}

		return entity;
	}
}
