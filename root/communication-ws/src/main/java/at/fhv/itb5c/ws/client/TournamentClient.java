package at.fhv.itb5c.ws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceRef;

import at.fhv.itb5c.commons.property.PropertyManager;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.ws.TournamentSEI;

public class TournamentClient implements ILogger {
	@WebServiceRef
	private static TournamentSEI _serviceTournament;

	public static void main(String[] args) {
		if (args.length == 4) {
			String service = args[0];
			String typeOfSport = args[1];
			String leagueName = args[2];
			String date = args[3];

			try {
				init();
			} catch (MalformedURLException e) {
				log.error("Error while initializing web service: " + e.getMessage());
				System.out.println("Error while initializing web service: " + e.getMessage());
			}

			if (service.equals("hasResult")) {
				System.out.println(_serviceTournament.hasResults(typeOfSport, leagueName, date));
			} else if (service.equals("getResult")) {
				System.out.println(_serviceTournament.getResults(typeOfSport, leagueName, date));
			} else {
				throw new IllegalArgumentException("Unknown service: " + service);
			}
		} else {
			throw new IllegalArgumentException(
					"All arguments need to be provided! service, typeOfSport, leagueName, date");
		}

	}

	private static void init() throws MalformedURLException {
		// set service URL
		URL url = new URL(PropertyManager.getInstance().getProperty("at.fhv.itb5c.ws.url"));
		// set TNS and service name
		QName qname = new QName(PropertyManager.getInstance().getProperty("at.fhv.itb5c.ws.tns"),
				PropertyManager.getInstance().getProperty("at.fhv.itb5c.ws.name"));

		// create service
		Service service = Service.create(url, qname);

		// get real service via port
		_serviceTournament = service.getPort(TournamentSEI.class);
	}
}
