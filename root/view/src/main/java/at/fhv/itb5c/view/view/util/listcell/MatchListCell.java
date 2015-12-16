package at.fhv.itb5c.view.view.util.listcell;

import java.time.format.DateTimeFormatter;

import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.view.communication.CommunicationErrorException;
import at.fhv.itb5c.view.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.view.view.util.popup.ErrorPopUp;

public class MatchListCell extends SimpleListCell<MatchDTO> {
	private String _sessionID;

	public MatchListCell(String sessionID) {
		super();
		_sessionID = sessionID;
	}

	@Override
	protected void format(MatchDTO item)  {
		String result = "";
		
		if (item.getStartDate() != null) {
			result += item.getStartDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")) + " - ";
		}

		String textTeamOne = null;
		if (item.getTeamOne() instanceof Long) {
			try {
				textTeamOne = CommunicationFacadeProvider.getInstance().getCurrentFacade()
						.getTeamById(_sessionID, (Long) item.getTeamOne()).getName();
			} catch (CommunicationErrorException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		} else {
			textTeamOne = (String) item.getTeamOne();
		}

		String textTeamTwo = null;
		if (item.getTeamTwo() instanceof Long) {
			try {
				textTeamTwo = CommunicationFacadeProvider.getInstance().getCurrentFacade()
						.getTeamById(_sessionID, (Long) item.getTeamTwo()).getName();
			} catch (CommunicationErrorException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		} else {
			textTeamTwo = (String) item.getTeamTwo();
		}

		result += textTeamOne + " : " + textTeamTwo;

		if (item.getResultTeamOne() != null || item.getResultTeamTwo() != null) {
			result += " - Match Result " + item.getResultTeamOne() + ":" + item.getResultTeamTwo();
		}

		setText(result);
	}

}
