package at.fhv.itb5c.application.dto;

import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public class DepartmentDTO implements IDepartment {
	private Long _id;
	private Long _version;
	private String _name;
	private IUser _head;
	private TypeOfSport _typeOfSport;

	@Override
	public void setId(Long id) throws Exception {
		_id = id;
	}

	@Override
	public Long getId() throws Exception {
		return _id;
	}

	@Override
	public void setVersion(Long version) throws Exception {
		_version = version;
	}

	@Override
	public Long getVersion() throws Exception {
		return _version;
	}

	@Override
	public void setName(String name) throws Exception {
		_name = name;
	}

	@Override
	public String getName() throws Exception {
		return _name;
	}

	@Override
	public IUser getHead() throws Exception {
		return _head;
	}

	@Override
	public void setHead(IUser head) throws Exception {
		_head = head;
	}

	@Override
	public TypeOfSport getTypeOfSport() throws Exception {
		return _typeOfSport;
	}

	@Override
	public void setTypeOfSport(TypeOfSport typeOfSport) throws Exception {
		_typeOfSport = typeOfSport;
	}

}
