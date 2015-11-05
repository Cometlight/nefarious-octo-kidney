package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.controller.TeamFactory;
import at.fhv.itb5c.commons.dto.ITeam;
import at.fhv.itb5c.commons.dto.rmi.ITeamFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.logging.ILogger;

public class TeamFactoryRMI extends UnicastRemoteObject implements ITeamFactoryRMI, RMIServant, ILogger {
	private static final long serialVersionUID = 1l;
	private TeamFactory _factory;

	protected TeamFactoryRMI() throws RemoteException {
		super();
		_factory = new TeamFactory();
	}

	@Override
	public void init(String host, String port) throws RemoteException, MalformedURLException {
		log.info("... initializing TeamFactoryRMI");
		Naming.rebind("rmi://" + host + ":" + port + "/TeamFactory", this);
	}

	@Override
	public ITeam createTeam() throws RemoteException {
		log.debug("new team created");
		ITeam team = _factory.createTeam();
		return TeamConverterRMI.toRMI(team);
	}

	@Override
	public ITeam saveOrUpdate(ITeam team) throws RemoteException {
		log.debug("saving team ...");
		team = _factory.saveOrUpdate(team);
		log.debug("team saved: " + team);
		return team;
	}

	@Override
	public List<ITeamRMI> findTeams(String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId)
			throws RemoteException {
		List<ITeam> teamsFound = _factory.findTeams(name, typeOfSport, departmentId, leagueId);
		
		List<ITeamRMI> teamsFoundRMI = teamsFound.stream().map(TeamConverterRMI::toRMI).collect(Collectors.toList());
		
		return teamsFoundRMI;
	}
	
	

}
