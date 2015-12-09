package at.fhv.itb5c.rmi.server;

import java.rmi.RemoteException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import at.fhv.itb5c.logging.ILogger;

@Singleton
@Startup
public class RMIServerBean implements ILogger {
	@PostConstruct 
    void atStartup() { 
		String[] args = new String[1];
		args[0] = "test";
		try {
			RMIServer.main(args);
		} catch (NumberFormatException | RemoteException e) {
			log.error(e.getMessage());
		}

	}
}
