package at.fhv.itb5c.view.util.listcell;

import java.rmi.RemoteException;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;

public class UserListCell extends SimpleListCell<IUserRMI> {
	@Override
	protected void format(IUserRMI item) throws RemoteException {
		setText(item.getFirstName() + " " + item.getLastName());
	}
}
