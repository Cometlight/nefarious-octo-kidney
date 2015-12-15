package at.fhv.itb5c.viewjsf;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.ejb.interfaces.GetByIdDepartmentRemote;
import at.fhv.itb5c.ejb.interfaces.GetByIdMatchRemote;
import at.fhv.itb5c.ejb.interfaces.SaveMatchRemote;

@ManagedBean
public class MatchResultBean {
	private Long matchId;
	private Integer resultTeamOne;
	private Integer resultTeamTwo;
	private Long departmentId;
	private String teamOne;
	private String teamTwo;
	private MatchDTO match;

	@EJB
	GetByIdMatchRemote getMatchById;

	@EJB
	SaveMatchRemote saveMatch;

	@EJB
	GetByIdDepartmentRemote getDepartmentById;
	
	@Inject
	LoginBean loginBean;

	public Integer getResultTeamOne() {
		return resultTeamOne;
	}

	public void setResultTeamOne(Integer resultTeamOne) {
		this.resultTeamOne = resultTeamOne;
	}

	public Integer getResultTeamTwo() {
		return resultTeamTwo;
	}

	public void setResultTeamTwo(Integer resultTeamTwo) {
		this.resultTeamTwo = resultTeamTwo;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
		if (matchId != null) {
			// TODO replace with session from session bean!!
			String sessionId = loginBean.getSessionId();
			match = getMatchById.getMatchById(sessionId, matchId);
			setResultTeamOne(match.getResultTeamOne());
			setResultTeamTwo(match.getResultTeamTwo());
		}
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getTeamOne() {
		return teamOne;
	}

	public void setTeamOne(String teamOne) {
		this.teamOne = teamOne;
	}

	public String getTeamTwo() {
		return teamTwo;
	}

	public void setTeamTwo(String teamTwo) {
		this.teamTwo = teamTwo;
	}

	public void saveMatch() {
		if (matchId != null && resultTeamOne != null && resultTeamTwo != null) {
			// TODO replace with session from session bean!!
			String sessionId = loginBean.getSessionId();
			match.setResultTeamOne(resultTeamOne);
			match.setResultTeamTwo(resultTeamTwo);
			saveMatch.saveMatch(sessionId, match, getDepartmentById.getDepartmentById(sessionId, departmentId));
		}
	}
}
