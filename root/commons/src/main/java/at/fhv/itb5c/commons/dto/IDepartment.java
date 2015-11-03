package at.fhv.itb5c.commons.dto;

public interface IDepartment {
	public void setId(Long id) throws Exception;

	public Long getId() throws Exception;

	public void setVersion(Long version) throws Exception;

	public Long getVersion() throws Exception;

	public void setName(String name) throws Exception;

	public String getName() throws Exception;
}
