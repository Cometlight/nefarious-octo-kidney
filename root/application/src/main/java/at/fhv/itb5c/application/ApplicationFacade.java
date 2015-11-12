package at.fhv.itb5c.application;

import java.util.Collection;
import java.util.List;

import at.fhv.itb5c.application.converter.ConverterDepartmentDTO;
import at.fhv.itb5c.application.converter.ConverterLeagueDTO;
import at.fhv.itb5c.application.converter.ConverterTeamDTO;
import at.fhv.itb5c.application.converter.ConverterTournamentDTO;
import at.fhv.itb5c.application.converter.ConverterUserDTO;
import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.LeagueDTO;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.commons.util.auth.LDAPAuth;
import at.fhv.itb5c.commons.util.auth.SessionManager;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.PersistenceFacade;
import at.fhv.itb5c.model.entity.Department;
import at.fhv.itb5c.model.entity.League;
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
		if (hasRole(sessionId, UserRole.Admin)) {
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
		if (hasRole(sessionId, UserRole.Admin)) {
			return ConverterUserDTO.toDTO(
					PersistenceFacade.getInstance().findUsers(firstName, lastName, departmentId, membershipFeePaid));
		}
		return null;
	}

	/**
	 * If a parameter is null, it is ignored.
	 */
	public Collection<UserDTO> findUsersSimple(String sessionId, String name) {
		if (hasRole(sessionId, UserRole.Admin)) {
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
		if (hasRole(sessionId, UserRole.Admin)) {
			Department entity = PersistenceFacade.getInstance().getById(Department.class, id);
			return ConverterDepartmentDTO.toDTO(entity);
		}
		return null;
	}

	public Collection<DepartmentDTO> getAllDepartments(String sessionId) {
		if (hasRole(sessionId, UserRole.Admin)) {
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
		if (hasRole(sessionId, UserRole.Admin)) {
			Team entity = PersistenceFacade.getInstance().getById(Team.class, id);
			return ConverterTeamDTO.toDTO(entity);
		}
		return null;
	}

	/**
	 * If a parameter is null, it is ignored.
	 */
	public Collection<TeamDTO> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId,
			Long leagueId) {
		if (hasRole(sessionId, UserRole.Admin)) {
			List<Team> entities = PersistenceFacade.getInstance().findTeams(name, typeOfSport, departmentId, leagueId);
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

	public LeagueDTO getLeagueById(String sessionId, Long id) {
		if (hasRole(sessionId, UserRole.Admin)) {
			return ConverterLeagueDTO.toDTO(PersistenceFacade.getInstance().getById(League.class, id));
		}
		return null;
	}

	public Collection<LeagueDTO> getAllLeagues(String sessionId) {
		if (hasRole(sessionId, UserRole.Admin)) {
			List<League> entities = PersistenceFacade.getInstance().getAll(League.class);
			return ConverterLeagueDTO.toDTO(entities);
		}
		return null;
	}

	public Collection<TournamentDTO> findTournaments(String sessionId, String name, Long departmentId) {
		if (hasRole(sessionId, UserRole.Admin)) {
			List<Tournament> entities = PersistenceFacade.getInstance().findTournaments(name, departmentId);
			return ConverterTournamentDTO.toDTO(entities);
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
					User user = PersistenceFacade.getInstance().findUserByLDAP(username);
					return SessionManager.getInstance().createNewSession(user.getId(), user.getRoles());
				}
			}
		
		return null;
	}

	public UserDTO getCurrentUser(String sessionId) {
		return ConverterUserDTO.toDTO(
				PersistenceFacade.getInstance().getById(User.class, SessionManager.getInstance().getUserId(sessionId)));
	}

	private boolean hasRole(String sessionId, UserRole... roles) {
		for (UserRole role : roles) {
			if (_sessionManager.hasRole(sessionId, role)) {
				return true;
			}
		}
		return false;
	}
}
