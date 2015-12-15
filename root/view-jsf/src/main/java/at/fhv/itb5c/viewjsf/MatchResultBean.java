package at.fhv.itb5c.viewjsf;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.ejb.interfaces.GetByIdDepartmentRemote;
import at.fhv.itb5c.ejb.interfaces.GetByIdMatchRemote;
import at.fhv.itb5c.ejb.interfaces.SaveMatchRemote;

@ManagedBean
public class MatchResultBean {
	private Long matchId;
	private String resultTeamOne;
	private String resultTeamTwo;
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

	@ManagedProperty(value = "#{loginBean}")
	LoginBean loginBean;

	public String getResultTeamOne() {
		return resultTeamOne;
	}

	public void setResultTeamOne(String resultTeamOne) {
		this.resultTeamOne = resultTeamOne;
	}

	public String getResultTeamTwo() {
		return resultTeamTwo;
	}

	public void setResultTeamTwo(String resultTeamTwo) {
		this.resultTeamTwo = resultTeamTwo;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
		if (matchId != null) {
			String sessionId = loginBean.getSessionId();
			match = getMatchById.getMatchById(sessionId, matchId);
			if (match.getResultTeamOne() != null) {
				setResultTeamOne(match.getResultTeamOne().toString());
			}
			if (match.getResultTeamTwo() != null) {
				setResultTeamTwo(match.getResultTeamTwo().toString());
			}
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

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String saveMatch() {
		if (matchId != null && resultTeamOne != null && resultTeamTwo != null) {
			String sessionId = loginBean.getSessionId();
			match.setResultTeamOne(toInteger(resultTeamOne));
			match.setResultTeamTwo(toInteger(resultTeamTwo));
			saveMatch.saveMatch(sessionId, match, getDepartmentById.getDepartmentById(sessionId, departmentId));
		}

		return "tournaments";
	}

	private Integer toInteger(String number) {
		try {
			return Integer.parseInt(number);
		} catch (Exception e) {
			return null;
		}
	}
}
