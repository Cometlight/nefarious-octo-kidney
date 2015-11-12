package at.fhv.itb5c.view.util.listcell;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;

public class TeamListCell extends SimpleListCell<ITeamRMI>{

	@Override
	protected void format(ITeamRMI item) throws RemoteException {
		setText(item.getName());
	}

}
