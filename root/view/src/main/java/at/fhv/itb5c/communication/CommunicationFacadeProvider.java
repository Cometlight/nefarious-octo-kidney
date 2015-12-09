package at.fhv.itb5c.communication;

public class CommunicationFacadeProvider {

	private CommunicationFacadeProvider() {
		//Singleton
	}
	
	private static CommunicationFacadeProvider _instance;
	public static CommunicationFacadeProvider getInstance() {
		if(_instance == null) {
			_instance = new CommunicationFacadeProvider();
		}
		
		return _instance;
	}
	
	private ICommunicationFacade _currentFacade;
	public void configureCommunicationFacade(CommunicationType communicationType) {
		switch (communicationType) {
		case EJB: {
			throw new UnsupportedOperationException();
		}
		case RMI: {
			_currentFacade = new RMICommunicationFacade();
		}
		}
	}
	
	public ICommunicationFacade getCurrentFacade() {
		return _currentFacade;
	}
}
