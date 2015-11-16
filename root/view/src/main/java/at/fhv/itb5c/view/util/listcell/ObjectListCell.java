package at.fhv.itb5c.view.util.listcell;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;

public class ObjectListCell extends SimpleListCell<Object> {

	@Override
	protected void format(Object item) throws RemoteException {
		if (item instanceof ITeamRMI) {
			setText(((ITeamRMI) item).getName());
		} else {
			setText(item.toString());
		}
	}

}
