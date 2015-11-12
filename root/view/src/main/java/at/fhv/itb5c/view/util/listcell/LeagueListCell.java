package at.fhv.itb5c.view.util.listcell;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.ILeagueRMI;

public class LeagueListCell extends SimpleListCell<ILeagueRMI> {

	@Override
	protected void format(ILeagueRMI item) throws RemoteException {
		setText(item.getName());
	}

}
