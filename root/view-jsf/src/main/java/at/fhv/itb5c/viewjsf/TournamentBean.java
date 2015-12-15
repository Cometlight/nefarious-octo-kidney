package at.fhv.itb5c.viewjsf;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.ejb.interfaces.FindTournamentsRemote;

@ManagedBean
public class TournamentBean {
	@EJB
	FindTournamentsRemote findTournamentsEJB;	
	
	public Collection<TournamentDTO> getAllTournaments(){
		Collection<TournamentDTO> tournaments = findTournamentsEJB.findTournaments("webservice_request_session", null, null);
		if(tournaments==null){
			return new LinkedList<>();
		}
		return tournaments;
	}

}
