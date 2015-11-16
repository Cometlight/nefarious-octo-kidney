package at.fhv.itb5c.view.util.listcell;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;

public class TournamentListCell extends SimpleListCell<ITournamentRMI> {

	@Override
	protected void format(ITournamentRMI item) throws RemoteException {
		setText(item.getName());
	}

}
