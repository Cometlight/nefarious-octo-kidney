package at.fhv.itb5c.commons.dto;

public interface IDepartment {
	public void setId(long id) throws Exception;

	public long getId() throws Exception;

	public void setVersion(long version) throws Exception;

	public long getVersion() throws Exception;

	public void setName(String name) throws Exception;

	public String getName() throws Exception;
}
