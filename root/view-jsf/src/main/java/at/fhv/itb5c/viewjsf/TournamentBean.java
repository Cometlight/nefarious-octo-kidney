package at.fhv.itb5c.viewjsf;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.ejb.interfaces.FindTournamentsRemote;
import at.fhv.itb5c.ejb.interfaces.GetByIdMatchRemote;
import at.fhv.itb5c.ejb.interfaces.GetByIdTeamRemote;

@ManagedBean
public class TournamentBean {
	@EJB
	FindTournamentsRemote findTournamentsEJB;
	
	@EJB
	GetByIdMatchRemote getMatchById;
	
	@EJB
	GetByIdTeamRemote getTeamById;
	
	public Collection<TournamentDTO> getAllTournaments(){
		Collection<TournamentDTO> tournaments = findTournamentsEJB.findTournaments("webservice_request_session", null, null);
		
		if(tournaments==null){
			return new LinkedList<>();
		}
		return tournaments;
	}

	public Collection<MatchDTO> getMatches(TournamentDTO tournament){
		LinkedList<MatchDTO> matches = new LinkedList<>();
		
		for(Long matchId : tournament.getMatchesIds()){
			matches.add(getMatchById.getMatchById("webservice_request_session", matchId));
		}
		
		return matches;
	}
	
	public String getTeamName(Object team){
		if(team instanceof String){
			return (String) team;
		}
		
		if(team instanceof Long){
			return ((TeamDTO) getTeamById.getTeamById("webservice_request_session", (Long) team)).getName();
		}
		
		return null;
	}
	
	public String getMatchResult(MatchDTO match){
		if(match.getResultTeamOne()!=null && match.getResultTeamTwo()!=null){
			return match.getResultTeamOne() + " : " + match.getResultTeamTwo();
		}
		return "";
	}
}
