package at.fhv.itb5c.communication;

import java.rmi.RemoteException;
import java.util.Collection;
import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.LeagueDTO;
import at.fhv.itb5c.application.dto.MatchDTO;
import at.fhv.itb5c.application.dto.MessageDTO;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.enums.TeamInvitationStatus;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.rmi.server.converter.ConverterDepartmentRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterLeagueRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterMatchRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterMessageRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterTeamRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterTournamentRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterUserRMI;

public class RMICommunicationFacade implements ICommunicationFacade {

	@Override
	public void startUp() throws CommunicationErrorException {
		RMIClient.getRMIClient().startUp();
	}

	@Override
	public void close() throws CommunicationErrorException {
		RMIClient.getRMIClient().close();
	}

	@Override
	public UserDTO createUser(String sessionId) throws CommunicationErrorException {
		try {
			return ConverterUserRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().createUser(sessionId));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public UserDTO getUserById(String sessionId, Long id) throws CommunicationErrorException {
		try {
			return ConverterUserRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().getUserById(sessionId, id));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public Collection<UserDTO> findUsers(String sessionId, String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) throws CommunicationErrorException {
		try {
			return ConverterUserRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().findUsers(sessionId,
					firstName, lastName, departmentId, membershipFeePaid));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}

	}

	@Override
	public Collection<UserDTO> findUsersSimple(String sessionId, String name) throws CommunicationErrorException {
		try {
			return ConverterUserRMI
					.toDTO(RMIClient.getRMIClient().getApplicationFacade().findUsersSimple(sessionId, name));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public UserDTO saveUser(String sessionId, UserDTO user) throws CommunicationErrorException {
		try {
			return ConverterUserRMI.toDTO(
					RMIClient.getRMIClient().getApplicationFacade().saveUser(sessionId, ConverterUserRMI.toRMI(user)));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public DepartmentDTO getDepartmentById(String sessionId, Long id) throws CommunicationErrorException {
		try {
			return ConverterDepartmentRMI
					.toDTO(RMIClient.getRMIClient().getApplicationFacade().getDepartmentById(sessionId, id));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public Collection<DepartmentDTO> getAllDepartments(String sessionId) throws CommunicationErrorException {
		try {
			return ConverterDepartmentRMI
					.toDTO(RMIClient.getRMIClient().getApplicationFacade().getAllDepartments(sessionId));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public TeamDTO getTeamById(String sessionId, Long id) throws CommunicationErrorException {
		try {
			return ConverterTeamRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().getTeamById(sessionId, id));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public Collection<TeamDTO> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId,
			Long leagueId, Long coachId) throws CommunicationErrorException {
		try {
			return ConverterTeamRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().findTeams(sessionId, name,
					typeOfSport, departmentId, leagueId, coachId));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public TeamDTO saveTeam(String sessionId, TeamDTO team) throws CommunicationErrorException {
		try {
			return ConverterTeamRMI.toDTO(
					RMIClient.getRMIClient().getApplicationFacade().saveTeam(sessionId, ConverterTeamRMI.toRMI(team)));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public LeagueDTO getLeagueById(String sessionId, Long id) throws CommunicationErrorException {
		try {
			return ConverterLeagueRMI
					.toDTO(RMIClient.getRMIClient().getApplicationFacade().getLeagueById(sessionId, id));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public Collection<LeagueDTO> getAllLeagues(String sessionId) throws CommunicationErrorException {
		try {
			return ConverterLeagueRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().getAllLeagues(sessionId));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public TeamDTO createTeam(String sessionId) throws CommunicationErrorException {
		try {
			return ConverterTeamRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().createTeam(sessionId));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public String loginLDAP(String username, String password) throws CommunicationErrorException {
		try {
			return RMIClient.getRMIClient().getApplicationFacade().loginLDAP(username, password);
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public UserDTO getCurrentUser(String sessionId) throws CommunicationErrorException {
		try {
			return ConverterUserRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().getCurrentUser(sessionId));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public Boolean hasRole(String sessionId, UserRole... roles) throws CommunicationErrorException {
		try {
			return RMIClient.getRMIClient().getApplicationFacade().hasRole(sessionId, roles);
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public TournamentDTO createTournament(String sessionId, DepartmentDTO dept) throws CommunicationErrorException {
		try {
			return ConverterTournamentRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade()
					.createTournament(sessionId, ConverterDepartmentRMI.toRMI(dept)));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public TournamentDTO saveTournament(String sessionId, TournamentDTO tournament, DepartmentDTO dept)
			throws CommunicationErrorException {
		try {
			return ConverterTournamentRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().saveTournament(
					sessionId, ConverterTournamentRMI.toRMI(tournament), ConverterDepartmentRMI.toRMI(dept)));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public TournamentDTO getTournamentById(String sessionId, Long id) throws CommunicationErrorException {
		try {
			return ConverterTournamentRMI
					.toDTO(RMIClient.getRMIClient().getApplicationFacade().getTournamentById(sessionId, id));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public TeamDTO addPlayerToTeam(String sessionId, TeamDTO team, UserDTO player) throws CommunicationErrorException {
		try {
			return ConverterTeamRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().addPlayerToTeam(sessionId,
					ConverterTeamRMI.toRMI(team), ConverterUserRMI.toRMI(player)));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public MatchDTO createMatch(String sessionId) throws CommunicationErrorException {
		try {
			return ConverterMatchRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().createMatch(sessionId));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public TournamentDTO addMatchToTournament(String sessionId, TournamentDTO tournament, MatchDTO match)
			throws CommunicationErrorException {
		try {
			return ConverterTournamentRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().addMatchToTournament(
					sessionId, ConverterTournamentRMI.toRMI(tournament), ConverterMatchRMI.toRMI(match)));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public Collection<TournamentDTO> findTournaments(String sessionId, String name, Long departmentId)
			throws CommunicationErrorException {
		try {
			return ConverterTournamentRMI.toDTO(
					RMIClient.getRMIClient().getApplicationFacade().findTournaments(sessionId, name, departmentId));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public MatchDTO getMatchById(String sessionId, Long matchId) throws CommunicationErrorException {
		try {
			return ConverterMatchRMI
					.toDTO(RMIClient.getRMIClient().getApplicationFacade().getMatchById(sessionId, matchId));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public MatchDTO saveMatch(String sessionId, MatchDTO match, DepartmentDTO dept) throws CommunicationErrorException {
		try {
			return ConverterMatchRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().saveMatch(sessionId,
					ConverterMatchRMI.toRMI(match), ConverterDepartmentRMI.toRMI(dept)));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public Boolean isDepartmentHead(String sessionId, DepartmentDTO dept) throws CommunicationErrorException {
		try {
			return RMIClient.getRMIClient().getApplicationFacade().isDepartmentHead(sessionId,
					ConverterDepartmentRMI.toRMI(dept));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public Boolean isCoach(String sessionId, TeamDTO team) throws CommunicationErrorException {
		try {
			return RMIClient.getRMIClient().getApplicationFacade().isCoach(sessionId, ConverterTeamRMI.toRMI(team));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public Boolean rsvp(String sessionId, TeamDTO team, TeamInvitationStatus answer)
			throws CommunicationErrorException {
		try {
			return RMIClient.getRMIClient().getApplicationFacade().rsvp(sessionId, ConverterTeamRMI.toRMI(team),
					answer);
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public MessageDTO getMessage(String sessionId) throws CommunicationErrorException {
		try {
			return ConverterMessageRMI.toDTO(RMIClient.getRMIClient().getApplicationFacade().getMessage(sessionId));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}

	@Override
	public void invitePlayer(String sessionId, UserDTO player, TeamDTO team, TournamentDTO tournament)
			throws CommunicationErrorException {
		try {
			RMIClient.getRMIClient().getApplicationFacade().invitePlayer(sessionId, ConverterUserRMI.toRMI(player),
					ConverterTeamRMI.toRMI(team), ConverterTournamentRMI.toRMI(tournament));
		} catch (RemoteException e) {
			throw new CommunicationErrorException(e);
		}
	}
}
