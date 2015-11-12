package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

public interface IUserRMI extends IBaseRMI {
	public String getFirstName() throws RemoteException;

	public void setFirstName(String firstName) throws RemoteException;

	public String getLastName() throws RemoteException;

	public void setLastName(String lastName) throws RemoteException;

	public String getEmail() throws RemoteException;

	public void setEmail(String email) throws RemoteException;

	public String getTelephoneNumber() throws RemoteException;

	public void setTelephoneNumber(String telephoneNumber) throws RemoteException;

	public Gender getGender() throws RemoteException;

	public void setGender(Gender gender) throws RemoteException;

	public String getAddress() throws RemoteException;

	public void setAddress(String address) throws RemoteException;

	public LocalDate getDateOfBirth() throws RemoteException;

	public void setDateOfBirth(LocalDate dateOfBirth) throws RemoteException;

	public Double getMembershipFee() throws RemoteException;

	public void setMembershipFee(Double membershipFee) throws RemoteException;

	public Set<UserRole> getRoles() throws RemoteException;

	public void setRoles(Set<UserRole> roles) throws RemoteException;

	public Set<TypeOfSport> getTypeOfSports() throws RemoteException;

	public void setTypeOfSports(Set<TypeOfSport> typeOfSports) throws RemoteException;
	
	public Boolean getMembershipFeePaid() throws RemoteException;

	public void setMembershipFeePaid(Boolean membershipFeePaid) throws RemoteException;

	public Long getDepartmentId() throws RemoteException;
	
	public void setDepartmentId(Long departmentId) throws RemoteException;
	
	public String getLdapUID() throws RemoteException;
	
	public void setLdapUID(String ldapUID) throws RemoteException;
}
