package at.fhv.itb5c.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import at.fhv.itb5c.commons.dto.ITeam;
import at.fhv.itb5c.commons.dto.rmi.ITeamFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.logging.ILogger;

public class TeamFactoryStub extends UnicastRemoteObject implements ITeamFactoryRMI, RMIStub, ILogger {
	private static final long serialVersionUID = 1l;
	
	private ITeamFactoryRMI _teamFactory;
	
	protected TeamFactoryStub() throws RemoteException {
		super();
	}
	
	@Override
	public void init(String host, String port) {
		Object obj;
		try {
			obj = Naming.lookup("rmi://" + host + ":" + port + "/TeamFactory");
			_teamFactory = (ITeamFactoryRMI) obj;
		} catch (MalformedURLException | NotBoundException | RemoteException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public ITeamRMI createTeam() throws RemoteException {
		return _teamFactory.createTeam();
	}

	@Override
	public ITeamRMI saveOrUpdate(ITeam team) throws RemoteException {
		return _teamFactory.createTeam();
	}

	@Override
	public List<ITeamRMI> findTeams(String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId)
			throws RemoteException {
		return _teamFactory.findTeams(name, typeOfSport, departmentId, leagueId);
	}
}
