package at.fhv.itb5c.ws;

import java.time.LocalDate;

import javax.jws.WebService;

import at.fhv.itb5c.application.ApplicationFacade;
import at.fhv.itb5c.commons.dto.LeagueDTO;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.logging.ILogger;

@WebService
public class TournamentWS implements ILogger {

	private ApplicationFacade _appFacade;

	public TournamentWS() {
		_appFacade = new ApplicationFacade();
	}

	public boolean hasResults(String typeOfSport, String leagueName, String dateString) {
		try {
			_appFacade = new ApplicationFacade();

			// set parameters
			TypeOfSport tos = getTos(typeOfSport);
			LeagueDTO league = getLeague(leagueName);
			LocalDate date = getDate(dateString);

			if (tos != null || league != null || date != null) {
				log.debug("Webservice called with params tos " + tos + ", league: " + league + ", date " + date);
				return _appFacade.hasResults(tos, league, date);
			} else {
				log.debug("One of the parameter was null. Returning null!");
				return false;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public TournamentResponse getResults(String typeOfSport, String leagueName, String dateString) {
		try {
			_appFacade = new ApplicationFacade();

			// set parameters
			TypeOfSport tos = getTos(typeOfSport);
			LeagueDTO league = getLeague(leagueName);
			LocalDate date = getDate(dateString);

			if (tos != null || league != null || date != null) {
				return new TournamentResponse(_appFacade.getResults(tos, league, date));
			}

			log.debug("One of the parameter was null. Returning null!");
			return null;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;// "An application error occured!";
		}
	}

	private TypeOfSport getTos(String typeOfSport) {
		if (typeOfSport != null) {
			try {
				return TypeOfSport.fromString(typeOfSport);
			} catch (Exception e) {
				log.error("Error occured trying to set type of sport. Param: " + typeOfSport);
			}
		}
		return null;
	}

	private LeagueDTO getLeague(String leagueName) {
		if (leagueName != null) {
			try {
				for (LeagueDTO l : _appFacade.findLeagues(leagueName)) {
					return l;
				}
			} catch (Exception e) {
				log.error("Error occured trying to set league. Param: " + leagueName);
			}
		}
		return null;
	}

	private LocalDate getDate(String date) {
		if (date != null) {
			try {
				return LocalDate.parse(date);
			} catch (Exception e) {
				log.error("Error occured trying to set the date. Param: " + date);
			}
		}
		return null;
	}
	
}
