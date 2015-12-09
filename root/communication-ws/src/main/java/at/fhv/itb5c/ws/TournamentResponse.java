package at.fhv.itb5c.ws;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlRootElement;

import at.fhv.itb5c.application.ApplicationFacade;
import at.fhv.itb5c.application.dto.MatchDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;

@XmlRootElement
public class TournamentResponse {
	// TODO wrap in <tournaments>
	private Collection<TournamentData> _data;

	public TournamentResponse() {
	}

	public TournamentResponse(Collection<TournamentDTO> results) {
		_data = new LinkedList<>();
		ApplicationFacade appFacade = new ApplicationFacade();
		for (TournamentDTO t : results) {
			TournamentData tData = new TournamentData();
			tData.setName(t.getName());
			tData.setDate(t.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			for(Long id : t.getMatchesIds()){
				MatchDTO match = appFacade.getMatchById("webservice_request", id);
				MatchData data = new MatchData();
				data.setDate(match.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
				if (match.getTeamOne() instanceof Long) {
					data.setTeamOne(appFacade.getTeamById("webservice_request", ((Long) match.getTeamOne())).getName());
				} else {
					data.setTeamOne((String) match.getTeamOne());
				}
				if (match.getTeamTwo() instanceof Long) {
					data.setTeamTwo(appFacade.getTeamById("webservice_request", ((Long) match.getTeamTwo())).getName());
				} else {
					data.setTeamTwo((String) match.getTeamTwo());
				}
				data.setResultTeamOne(match.getResultTeamOne());
				data.setResultTeamTwo(match.getResultTeamTwo());
				tData.addMatch(data);
			}
			_data.add(tData);
		}
	}

	public Collection<TournamentData> getData() {
		return _data;
	}

	public void setData(Collection<TournamentData> data) {
		_data = data;
	}

	@XmlRootElement
	static class TournamentData {
		private String _name;
		private String _date;
		private Collection<MatchData> _matches;

		public String getName() {
			return _name;
		}

		public void setName(String name) {
			_name = name;
		}

		public String getDate() {
			return _date;
		}

		public void setDate(String date) {
			_date = date;
		}

		public Collection<MatchData> getMatches() {
			return _matches;
		}

		public void setMatches(Collection<MatchData> matches) {
			_matches = matches;
		}
		
		public void addMatch(MatchData match){
			if(_matches == null){
				_matches = new LinkedList<>();
			}
			_matches.add(match);
		}
	}

	@XmlRootElement
	static class MatchData {
		private String _teamOne;
		private String _teamTwo;
		private Integer _resultTeamOne;
		private Integer _resultTeamTwo;
		private String _date;

		public String getTeamOne() {
			return _teamOne;
		}

		public void setTeamOne(String teamOne) {
			_teamOne = teamOne;
		}

		public String getTeamTwo() {
			return _teamTwo;
		}

		public void setTeamTwo(String teamTwo) {
			_teamTwo = teamTwo;
		}

		public Integer getResultTeamOne() {
			return _resultTeamOne;
		}

		public void setResultTeamOne(Integer resultTeamOne) {
			_resultTeamOne = resultTeamOne;
		}

		public Integer getResultTeamTwo() {
			return _resultTeamTwo;
		}

		public void setResultTeamTwo(Integer resultTeamTwo) {
			_resultTeamTwo = resultTeamTwo;
		}

		public String getDate() {
			return _date;
		}

		public void setDate(String date) {
			_date = date;
		}
	}
}
