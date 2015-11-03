package at.fhv.itb5c.application.dto;

import at.fhv.itb5c.commons.dto.IDepartment;

public class DepartmentDTO implements IDepartment {
	private Long _id;
	private Long _version;
	private String _name;

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

}
