package at.fhv.itb5c.view.util.listcell;

import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;

import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;
import at.fhv.itb5c.rmi.client.RMIClient;

public class MatchListCell extends SimpleListCell<IMatchRMI> {
	private String _sessionID;

	public MatchListCell(String sessionID) {
		super();
		_sessionID = sessionID;
	}

	@Override
	protected void format(IMatchRMI item) throws RemoteException {
		String result = null;

		if (item.getStartDate() != null) {
			result = item.getStartDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")) + " ";
		}

		String textTeamOne = null;
		if (item.getTeamOne() instanceof Long) {
			textTeamOne = RMIClient.getRMIClient().getApplicationFacade()
					.getTeamById(_sessionID, (Long) item.getTeamOne()).getName();
		} else {
			textTeamOne = (String) item.getTeamOne();
		}

		String textTeamTwo = null;
		if (item.getTeamTwo() instanceof Long) {
			textTeamTwo = RMIClient.getRMIClient().getApplicationFacade()
					.getTeamById(_sessionID, (Long) item.getTeamTwo()).getName();
		} else {
			textTeamTwo = (String) item.getTeamTwo();
		}

		result = textTeamOne + " : " + textTeamTwo;

		if (item.getResultTeamOne() != null || item.getResultTeamTwo() != null) {
			result += " - Match Result " + item.getResultTeamOne() + ":" + item.getResultTeamTwo();
		}

		setText(result);
	}

}
