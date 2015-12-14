package at.fhv.itb5c.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface TournamentSEI {
	@WebMethod
	public boolean hasResults(String typeOfSport, String leagueName, String date);

	@WebMethod
	public TournamentResponse getResults(String typeOfSport, String leagueName, String date);
}
