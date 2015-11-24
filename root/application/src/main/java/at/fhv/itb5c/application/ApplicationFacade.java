package at.fhv.itb5c.application;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.converter.ConverterDepartmentDTO;
import at.fhv.itb5c.application.converter.ConverterLeagueDTO;
import at.fhv.itb5c.application.converter.ConverterMatchDTO;
import at.fhv.itb5c.application.converter.ConverterMessageDTO;
import at.fhv.itb5c.application.converter.ConverterTeamDTO;
import at.fhv.itb5c.application.converter.ConverterTournamentDTO;
import at.fhv.itb5c.application.converter.ConverterUserDTO;
import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.LeagueDTO;
import at.fhv.itb5c.application.dto.MatchDTO;
import at.fhv.itb5c.application.dto.MessageDTO;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.commons.util.auth.LDAPAuth;
import at.fhv.itb5c.commons.util.auth.SessionManager;
import at.fhv.itb5c.jms.QueueManager;
import at.fhv.itb5c.jms.entity.Message;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.PersistenceFacade;
import at.fhv.itb5c.model.entity.Department;
import at.fhv.itb5c.model.entity.League;
import at.fhv.itb5c.model.entity.Match;
import at.fhv.itb5c.model.entity.Team;
import at.fhv.itb5c.model.entity.Tournament;
import at.fhv.itb5c.model.entity.User;

public class ApplicationFacade implements ILogger {
	SessionManager _sessionManager;

	public ApplicationFacade() {
		_sessionManager = SessionManager.getInstance();
	}

	public UserDTO createUser(String sessionId) {
		if (hasRole(sessionId, UserRole.Admin)) {
			return ConverterUserDTO.toDTO(new User());
		}
		return null;
	}

	public UserDTO getUserById(String sessionId, Long id) {
		if (hasRole(sessionId, UserRole.StandardUser, UserRole.Admin)) {
			User user = PersistenceFacade.getInstance().getById(User.class, id);
			return ConverterUserDTO.toDTO(user);
		}
		return null;
	}

	/**
	 * If a parameter is null, it is ignored.
	 */
	public Collection<UserDTO> findUsers(String sessionId, String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) {
		if (hasRole(sessionId, UserRole.StandardUser, UserRole.Admin)) {
			return ConverterUserDTO.toDTO(
					PersistenceFacade.getInstance().findUsers(firstName, lastName, departmentId, membershipFeePaid));
		}
		return null;
	}

	/**
	 * If a parameter is null, it is ignored.
	 */
	public Collection<UserDTO> findUsersSimple(String sessionId, String name) {
		if (hasRole(sessionId, UserRole.StandardUser, UserRole.Admin)) {
			return ConverterUserDTO.toDTO(PersistenceFacade.getInstance().findUsersSimple(name));
		}
		return null;
	}

	public UserDTO saveUser(String sessionId, UserDTO user) {
		if (hasRole(sessionId, UserRole.Admin)) {
			try {
				return ConverterUserDTO
						.toDTO(PersistenceFacade.getInstance().saveOrUpdate(ConverterUserDTO.toEntity(user)));
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		}
		return null;
	}

	public DepartmentDTO getDepartmentById(String sessionId, Long id) {
		if (hasRole(sessionId, UserRole.StandardUser, UserRole.Admin)) {
			Department entity = PersistenceFacade.getInstance().getById(Department.class, id);
			return ConverterDepartmentDTO.toDTO(entity);
		}
		return null;
	}

	public Collection<DepartmentDTO> getAllDepartments(String sessionId) {
		if (hasRole(sessionId, UserRole.Admin, UserRole.StandardUser)) {
			List<Department> departments = PersistenceFacade.getInstance().getAll(Department.class);
			return ConverterDepartmentDTO.toDTO(departments);
		}
		return null;
	}

	public DepartmentDTO saveDepartment(String sessionId, DepartmentDTO department) {
		if (hasRole(sessionId, UserRole.Admin)) {
			Department entity = ConverterDepartmentDTO.toEntity(department);
			try {
				entity = PersistenceFacade.getInstance().saveOrUpdate(entity);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
			return ConverterDepartmentDTO.toDTO(entity);
		}
		return null;
	}

	public TeamDTO createTeam(String sessionId) {
		if (hasRole(sessionId, UserRole.Admin)) {
			return ConverterTeamDTO.toDTO(new Team());
		}
		return null;
	}

	public TeamDTO getTeamById(String sessionId, Long id) {
		if (hasRole(sessionId, UserRole.StandardUser, UserRole.Admin)) {
			Team entity = PersistenceFacade.getInstance().getById(Team.class, id);
			return ConverterTeamDTO.toDTO(entity);
		}
		return null;
	}

	/**
	 * If a parameter is null, it is ignored.
	 */
	public Collection<TeamDTO> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId,
			Long leagueId, Long coachId) {
		if (hasRole(sessionId, UserRole.StandardUser, UserRole.Admin)) {
			List<Team> entities = PersistenceFacade.getInstance().findTeams(name, typeOfSport, departmentId, leagueId,
					coachId);
			return ConverterTeamDTO.toDTO(entities);
		}
		return null;
	}

	public TeamDTO saveTeam(String sessionId, TeamDTO team) {
		if (hasRole(sessionId, UserRole.Admin)) {
			Team entity = ConverterTeamDTO.toEntity(team);
			try {
				entity = PersistenceFacade.getInstance().saveOrUpdate(entity);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
			return ConverterTeamDTO.toDTO(entity);
		}
		return null;
	}

	/**
	 * Adds the specified player to the specified team.
	 *
	 * This method is currently untested!
	 *
	 * @param sessionId
	 *            a session id
	 * @param team
	 *            the team the player should be added to
	 * @param player
	 *            the user that should be added to the team
	 * @return the updated team if the session can add players; null, otherwise.
	 */
	public TeamDTO addPlayerToTeam(String sessionId, TeamDTO team, UserDTO player) {
		if (team != null && player != null) {
			UserDTO currentUser = getCurrentUser(sessionId);
			if (currentUser != null
					&& (hasRole(sessionId, UserRole.Admin) || team.getCoachId().equals(currentUser.getId()))) {
				Team teamEntity = ConverterTeamDTO.toEntity(team);
				if (teamEntity != null) {
					Set<Long> memberIds = teamEntity.getMemberIds();
					memberIds.add(player.getId());
					teamEntity.setMemberIds(memberIds);
					try {
						teamEntity = PersistenceFacade.getInstance().saveOrUpdate(teamEntity);
						return ConverterTeamDTO.toDTO(teamEntity);
					} catch (Exception e) {
						log.error(e.getMessage());
					}
				}
			}
		}
		return null;
	}

	public LeagueDTO getLeagueById(String sessionId, Long id) {
		if (hasRole(sessionId, UserRole.StandardUser, UserRole.Admin)) {
			return ConverterLeagueDTO.toDTO(PersistenceFacade.getInstance().getById(League.class, id));
		}
		return null;
	}

	public Collection<LeagueDTO> getAllLeagues(String sessionId) {
		if (hasRole(sessionId, UserRole.StandardUser, UserRole.Admin)) {
			List<League> entities = PersistenceFacade.getInstance().getAll(League.class);
			return ConverterLeagueDTO.toDTO(entities);
		}
		return null;
	}

	public Collection<TournamentDTO> findTournaments(String sessionId, String name, Long departmentId) {
		if (hasRole(sessionId, UserRole.StandardUser, UserRole.Admin)) {
			List<Tournament> entities = PersistenceFacade.getInstance().findTournaments(name, departmentId);
			return ConverterTournamentDTO.toDTO(entities);
		}
		return null;
	}

	public MatchDTO getMatchById(String sessionId, Long matchId) {
		if (hasRole(sessionId, UserRole.Admin, UserRole.StandardUser)) {
			Match match = PersistenceFacade.getInstance().getById(Match.class, matchId);
			return ConverterMatchDTO.toDTO(match);
		}
		return null;
	}

	public MatchDTO createMatch(String sessionId) {
		return ConverterMatchDTO.toDTO(new Match());
	}

	/**
	 * Adds a Match to a Tournament. Only administrators and the head of the
	 * hosting department have permissions to do this.
	 *
	 * @param sessionId
	 *            the session id of the currently logged in user
	 * @param tournament
	 *            the tournament, the match should be added to
	 * @param match
	 *            the match that should be added to the tournament
	 * @return the updated tournament; or null, if the match could not be added
	 */
	public TournamentDTO addMatchToTournament(String sessionId, TournamentDTO tournament, MatchDTO match) {
		if (tournament != null && match != null) {
			UserDTO currentUser = getCurrentUser(sessionId);
			DepartmentDTO currentDepartment = this.getDepartmentById(sessionId, tournament.getDepartmentId());
			if (currentUser != null) {
				if (hasRole(sessionId, UserRole.Admin) || currentUser.getId().equals(currentDepartment.getHeadId())) {
					Tournament tournamentEntity = ConverterTournamentDTO.toEntity(tournament);
					if (tournamentEntity != null) {
						try {
							// first: save match (this is a new entity)
							Match matchEntity = ConverterMatchDTO.toEntity(match);
							matchEntity = PersistenceFacade.getInstance().saveOrUpdate(matchEntity);
							// add match id to tournament
							Set<Long> matchIds = tournamentEntity.getMatchesIds();
							matchIds.add(matchEntity.getId());
							tournamentEntity.setMatchesIds(matchIds);
							tournamentEntity = PersistenceFacade.getInstance().saveOrUpdate(tournamentEntity);
							return ConverterTournamentDTO.toDTO(tournamentEntity);
						} catch (Exception e) {
							log.error(e.getMessage());
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * checks the login credentials on the ldap server and returns the new
	 * created session id
	 *
	 * @param username
	 *            ldap username
	 * @param password
	 *            ldap password
	 * @return session id
	 */
	public String loginLDAP(String username, String password) {
		if (username != null && password != null) {
			if (LDAPAuth.ldapLogin(username, password) != null) {
				log.debug("User login successful, searching for user in system ...");
				User user = PersistenceFacade.getInstance().findUserByLDAP(username);
				if (user != null) {
					log.debug("... user found.");
					return SessionManager.getInstance().createNewSession(user.getId(), user.getRoles());
				}
				log.debug("... user not found.");
			}
		}

		return null;
	}

	public UserDTO getCurrentUser(String sessionId) {
		return ConverterUserDTO.toDTO(
				PersistenceFacade.getInstance().getById(User.class, SessionManager.getInstance().getUserId(sessionId)));
	}

	public Boolean hasRole(String sessionId, UserRole... roles) {
		for (UserRole role : roles) {
			if (_sessionManager.hasRole(sessionId, role)) {
				return true;
			}
		}
		return false;
	}

	public TournamentDTO createTournament(String sessionId, DepartmentDTO dept) {
		if (hasRole(sessionId, UserRole.Admin) || isDepartmentHead(sessionId, dept)) {
			return ConverterTournamentDTO.toDTO(new Tournament());
		}
		return null;
	}

	public TournamentDTO saveTournament(String sessionId, TournamentDTO tournament, DepartmentDTO dept) {
		if (hasRole(sessionId, UserRole.Admin) || isDepartmentHead(sessionId, dept)) {

			Tournament entity = ConverterTournamentDTO.toEntity(tournament);
			if (entity.getId() != null) {
				if (!cloneNewlyAddedTeams(entity)) {
					return null; // something went horribly wrong
				}
			}

			try {
				entity = PersistenceFacade.getInstance().saveOrUpdate(entity);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
			return ConverterTournamentDTO.toDTO(entity);
		}
		return null;
	}

	private Boolean cloneNewlyAddedTeams(Tournament entity) {
		Tournament originalEntity = PersistenceFacade.getInstance().getById(Tournament.class, entity.getId());
		Set<Long> ids = new HashSet<>(entity.getHomeTeamsIds()); // --> copy
		ids = ids.stream().filter(id -> !originalEntity.getHomeTeamsIds().contains(id)).collect(Collectors.toSet()); // filter
																														// out
																														// all
																														// IDs
																														// that
																														// are
																														// already
																														// in
																														// originalEntity
		entity.getHomeTeamsIds().removeAll(ids);
		for (Long teamId : ids) {
			Team team = PersistenceFacade.getInstance().getById(Team.class, teamId);
			team.setId(null);
			team.setVersion(null);
			try {
				team = PersistenceFacade.getInstance().saveOrUpdate(team);
			} catch (Exception e) {
				log.error(e.getMessage());
				return false;
			}
			entity.getHomeTeamsIds().add(team.getId());
		}
		return true;
	}

	public TournamentDTO getTournamentById(String sessionId, Long id) {
		if (hasRole(sessionId, UserRole.StandardUser, UserRole.Admin)) {
			return ConverterTournamentDTO.toDTO(PersistenceFacade.getInstance().getById(Tournament.class, id));
		}
		return null;
	}

	public Boolean isDepartmentHead(String sessionId, DepartmentDTO dept) {
		return dept.getHeadId().equals(_sessionManager.getUserId(sessionId));
	}

	public Boolean isCoach(String sessionId, TeamDTO team) {
		return team.getCoachId().equals(_sessionManager.getUserId(sessionId));
	}

	public MatchDTO saveMatch(String sessionId, MatchDTO matchDTO, DepartmentDTO dept) {
		if (hasRole(sessionId, UserRole.Admin) || isDepartmentHead(sessionId, dept)) {
			Match entity = ConverterMatchDTO.toEntity(matchDTO);
			try {
				entity = PersistenceFacade.getInstance().saveOrUpdate(entity);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
			return ConverterMatchDTO.toDTO(entity);
		}
		return null;
	}

	public Boolean rsvp(String sessionId, TeamDTO team, Boolean answer) {
		try {
			UserDTO player = getCurrentUser(sessionId);
			Team teamEntity = ConverterTeamDTO.toEntity(team);
			teamEntity.setMemberStatus(player.getId(), answer);
			return PersistenceFacade.getInstance().saveOrUpdate(teamEntity) != null;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public MessageDTO getMessage(String sessionId) {
		QueueManager qm = new QueueManager(SessionManager.getInstance().getUserId(sessionId).toString());
		return ConverterMessageDTO.toDTO(qm.consume());
	}

	/**
	 * adds a player to a team from the specified tournament and creates a
	 * notification for the player
	 * 
	 * @param sessionId
	 *            session id
	 * @param player
	 *            player to be added
	 * @param team
	 *            team of the player
	 * @param tournament
	 *            tournament
	 */
	public void invitePlayer(String sessionId, UserDTO player, TeamDTO team, TournamentDTO tournament) {
		DepartmentDTO dept = ConverterDepartmentDTO
				.toDTO(PersistenceFacade.getInstance().getById(Department.class, tournament.getDepartmentId()));

		if (hasRole(sessionId, UserRole.Admin) || isDepartmentHead(sessionId, dept)) {
			if (tournament.getHomeTeamsIds().contains(team.getId())) {
				// add player to team if not exists
				if (!team.getMemberIds().contains(player.getId())) {
					Team teamEntity = ConverterTeamDTO.toEntity(team);
					teamEntity.getMemberIds().add(player.getId());
					try {
						PersistenceFacade.getInstance().saveOrUpdate(teamEntity);
					} catch (Exception e) {
						log.error(e.getMessage());
					}

					// add message to players queue
					QueueManager qm = new QueueManager(SessionManager.getInstance().getUserId(sessionId).toString());
					String msgType = "INVITE_PLAYER_TOURNAMENT";
					HashMap<String, Object> msgData = new HashMap<>();
					msgData.put("tournamentId", tournament.getId());
					msgData.put("teamId", team.getId());
					Message message = new Message(msgType, msgData);
					qm.produce(message);
				}
			}
		}
	}

	public TournamentDTO addTeamToTournament(String sessionId, TournamentDTO tournament, TeamDTO team) {
		DepartmentDTO dept = ConverterDepartmentDTO
				.toDTO(PersistenceFacade.getInstance().getById(Department.class, tournament.getDepartmentId()));

		if (hasRole(sessionId, UserRole.Admin) || isDepartmentHead(sessionId, dept) || isCoach(sessionId, dept)) {
			if (tournament != null && team != null) {
				if (!tournament.getHomeTeamsIds().contains(team.getId())) {
					Tournament tournamentEntity = ConverterTournamentDTO.toEntity(tournament);
					tournamentEntity.getHomeTeamsIds().add(team.getId());
					try {
						tournament = ConverterTournamentDTO
								.toDTO(PersistenceFacade.getInstance().saveOrUpdate(tournamentEntity));
					} catch (Exception e) {
						log.error(e.getMessage());
						return null;
					}
					return tournament;
				}
			}
		}
		return null;
	}

	public boolean isCoach(String sessionId, DepartmentDTO dept) {
		List<Team> res = PersistenceFacade.getInstance().findTeams(null, null, dept.getId(), null,
				SessionManager.getInstance().getUserId(sessionId));

		return !res.isEmpty();
	}
}
