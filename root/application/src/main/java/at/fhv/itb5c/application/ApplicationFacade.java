package at.fhv.itb5c.application;

import java.time.LocalDate;
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
import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.LeagueDTO;
import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.commons.dto.MessageDTO;
import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.commons.enums.TeamInvitationStatus;
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
			List<Tournament> entities = PersistenceFacade.getInstance().findTournaments(name, departmentId, null);
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

	private Boolean cloneNewlyAddedTeams(Tournament tournament) {
		Tournament originalEntity = PersistenceFacade.getInstance().getById(Tournament.class, tournament.getId());
		Set<Long> ids = new HashSet<>(tournament.getHomeTeamsIds()); // --> copy
		// filter out all IDs that are already in originalEntity
		ids = ids.stream().filter(id -> !originalEntity.getHomeTeamsIds().contains(id)).collect(Collectors.toSet());
		tournament.getHomeTeamsIds().removeAll(ids);

		for (Long teamId : ids) {
			Team team = PersistenceFacade.getInstance().getById(Team.class, teamId);

			Team teamCopy = new Team();
			teamCopy.setCoachId(team.getCoachId());
			teamCopy.setMemberIds(new HashSet<>(team.getMemberIds()));
			teamCopy.setName(team.getName());
			teamCopy.setTypeOfSport(team.getTypeOfSport());

			Team teamCopySaved = null;
			try {
				teamCopySaved = PersistenceFacade.getInstance().saveOrUpdate(teamCopy);
			} catch (Exception e) {
				log.error(e.getMessage());
				return false;
			}
			tournament.getHomeTeamsIds().add(teamCopySaved.getId());
			enqueueTournamentNotification(team.getCoachId(), tournament.getId(), teamCopySaved.getId());
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

	public Boolean rsvp(String sessionId, TeamDTO team, TeamInvitationStatus answer) {
		if (team == null || answer == null) {
			return null;
		}
		if (!answer.equals(TeamInvitationStatus.Accepted) && !answer.equals(TeamInvitationStatus.Declined)) {
			log.warn("rsvp: TeamInvitationStatus must be set to Accepted or Declined!");
			return null;
		}
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

		if (hasRole(sessionId, UserRole.Admin) || isCoach(sessionId, dept)) {
			if (tournament.getHomeTeamsIds().contains(team.getId())) {
				// add player to team if not exists
				if (team.getMemberIds().contains(player.getId())) {
					// add message to players queue
					enqueueTournamentInvitation(player.getId(), tournament.getId(), team.getId());
				}
			}
		}
	}

	public boolean isCoach(String sessionId, DepartmentDTO dept) {
		List<Team> res = PersistenceFacade.getInstance().findTeams(null, null, dept.getId(), null,
				SessionManager.getInstance().getUserId(sessionId));

		return !res.isEmpty();
	}

	private void enqueueTournamentInvitation(Long playerId, Long tournamentId, Long teamId) {
		QueueManager qm = new QueueManager(playerId.toString());
		String msgType = "INVITE_PLAYER_TOURNAMENT";
		HashMap<String, Object> msgData = new HashMap<>();
		msgData.put("tournamentId", tournamentId);
		msgData.put("teamId", teamId);
		Message message = new Message(msgType, msgData);
		qm.produce(message);
	}

	private void enqueueTournamentNotification(Long coachId, Long tournamentId, Long teamId) {
		QueueManager qm = new QueueManager(coachId.toString());
		String msgType = "NOTIFY_COACH_TOURNAMENT";
		HashMap<String, Object> msgData = new HashMap<>();
		msgData.put("tournamentId", tournamentId);
		msgData.put("teamId", teamId);
		Message message = new Message(msgType, msgData);
		qm.produce(message);
	}

	public Collection<LeagueDTO> findLeagues(String leagueName) {
		// TODO security?
		return ConverterLeagueDTO.toDTO(PersistenceFacade.getInstance().findLeagues(leagueName));
	}

	public boolean hasResults(TypeOfSport tos, LeagueDTO league, LocalDate tournamentDate) {
		// TODO security?
		if (tos == null || league == null || tournamentDate == null) {
			log.debug("One of the parameter was null. Returning null!");
			return false;
		}
		log.debug("start");
		for (TournamentDTO t : getTournaments(tos, league, tournamentDate)) {
			log.debug("Processing tournament: " + t.getName());
			if (t.getDone() == null || !t.getDone()) {
				log.debug("Tournament not finished yet!");
				return false;
			}
		}
		log.debug("end");
		return true;
	}

	public Collection<MatchDTO> getResults(TypeOfSport tos, LeagueDTO league, LocalDate date) {
		// TODO security?
		// TODO change to custom Response class
		// http://stackoverflow.com/questions/11107875/jax-ws-how-to-make-a-soap-response-return-a-hashmap-object
		Collection<MatchDTO> resultSet = null;

		if (tos == null || league == null || date == null) {
			log.debug("One of the parameter was null. Returning null!");
			return null;
		}

		if (hasResults(tos, league, date)) {
			resultSet = new HashSet<>();
			for (TournamentDTO t : getTournaments(tos, league, date)) {
				for (Long id : t.getMatchesIds()) {
					resultSet.add(ConverterMatchDTO.toDTO(PersistenceFacade.getInstance().getById(Match.class, id)));
				}
			}
		}

		return resultSet;
	}

	private Collection<TournamentDTO> getTournaments(TypeOfSport tos, LeagueDTO league, LocalDate tournamentDate) {
		Collection<TournamentDTO> tmnts = null;

		if (tos == null || league == null || tournamentDate == null) {
			log.debug("One of the parameter was null. Returning null!");
			return null;
		}

		if (league.getTypeOfSport().equals(tos)) {
			tmnts = ConverterTournamentDTO
					.toDTO(PersistenceFacade.getInstance().findTournaments(null, null, tournamentDate));
			log.debug(tmnts.size() + " tournaments found.");
			for (TournamentDTO t : tmnts) {
				DepartmentDTO dept = ConverterDepartmentDTO
						.toDTO(PersistenceFacade.getInstance().getById(Department.class, t.getDepartmentId()));
				if (!dept.getTypeOfSport().equals(tos)) {
					log.debug("Department of tournament doesn't match type of sport! tos: " + tos + ", dept: "
							+ dept.getName());
					tmnts.remove(t);
				}
			}
		}
		return tmnts;
	}
}
