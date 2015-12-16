package at.fhv.itb5c.view.communication;

public class CommunicationFacadeProvider {
	private static CommunicationFacadeProvider _instance;
	private ICommunicationFacade _currentFacade;

	private CommunicationFacadeProvider() {
		//Singleton
	}
	
	public static CommunicationFacadeProvider getInstance() {
		if(_instance == null) {
			_instance = new CommunicationFacadeProvider();
		}
		
		return _instance;
	}
	
	public void configureCommunicationFacade(CommunicationType communicationType) {
		switch (communicationType) {
			case EJB: {
				_currentFacade = new EJBCommunicationFacade();
			} break;
			case RMI: {
				_currentFacade = new RMICommunicationFacade();
			} break;
		}
	}
	
	public ICommunicationFacade getCurrentFacade() {
		return _currentFacade;
	}
}
