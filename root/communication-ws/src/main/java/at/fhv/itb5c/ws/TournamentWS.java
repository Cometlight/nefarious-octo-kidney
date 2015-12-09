package at.fhv.itb5c.ws;

import java.time.LocalDate;
import java.util.Collection;

import javax.jws.WebService;

import at.fhv.itb5c.application.ApplicationFacade;
import at.fhv.itb5c.application.dto.LeagueDTO;
import at.fhv.itb5c.application.dto.MatchDTO;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.logging.ILogger;

@WebService
public class TournamentWS implements ILogger {

	private ApplicationFacade _appFacade;

	private TypeOfSport _tos;
	private LeagueDTO _league;
	private LocalDate _date;

	public TournamentWS() {
		_appFacade = new ApplicationFacade();
	}

	public boolean hasResults(String typeOfSport, String leagueName, String date) {
		try {
			_appFacade = new ApplicationFacade();

			// set parameters
			setParams(typeOfSport, leagueName, date);

			if (_tos != null || _league != null || _date != null) {
				return _appFacade.hasResults(_tos, _league, _date);
			} else {
				log.debug("One of the parameter was null. Returning null!");
				return false;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public Collection<MatchDTO> getResults(String typeOfSport, String leagueName, String date) {
		try {
			_appFacade = new ApplicationFacade();

			// set parameters
			setParams(typeOfSport, leagueName, date);

			if (_tos != null || _league != null || _date != null) {
				return _appFacade.getResults(_tos, _league, _date);
			}

			log.debug("One of the parameter was null. Returning null!");
			return null;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;// "An application error occured!";
		}
	}

	private void setParams(String typeOfSport, String leagueName, String date) {
		if (typeOfSport != null) {
			try {
				_tos = TypeOfSport.fromString(typeOfSport);
			} catch (Exception e) {
				log.error("Error occured trying to set type of sport. Param: " + typeOfSport);
			}
		}
		if (leagueName != null) {
			try {
				for (LeagueDTO l : _appFacade.findLeagues(leagueName)) {
					_league = l;
				}
			} catch (Exception e) {
				log.error("Error occured trying to set league. Param: " + leagueName);
			}
		}
		if (date != null) {
			try {
				_date = LocalDate.parse(date);
			} catch (Exception e) {
				log.error("Error occured trying to set the date. Param: " + date);
			}
		}
		log.debug("Webservice Parameter set: _tos = " + _tos + ", _league = " + _league + ", _date = " + _date);
	}
}
