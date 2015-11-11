package at.fhv.itb5c.view.util.listcell;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;

public class DepartmentListCell extends SimpleListCell<IDepartmentRMI>{
	@Override
	protected void format(IDepartmentRMI item) throws RemoteException {
		setText(item.getName());
	}
}