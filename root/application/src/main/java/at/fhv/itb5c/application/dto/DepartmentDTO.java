package at.fhv.itb5c.application.dto;

import at.fhv.itb5c.commons.dto.IDepartment;

public class DepartmentDTO implements IDepartment {
	private long _id;
	private long _version;
	private String _name;

	@Override
	public void setId(long id) throws Exception {
		_id = id;
	}

	@Override
	public long getId() throws Exception {
		return _id;
	}

	@Override
	public void setVersion(long version) throws Exception {
		_version = version;
	}

	@Override
	public long getVersion() throws Exception {
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
