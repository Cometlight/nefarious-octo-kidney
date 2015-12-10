package at.fhv.itb5c.ejb.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import at.fhv.itb5c.logging.ILogger;

public class EJBClient implements ILogger {
	private static EJBClient _instance;
	private InitialContext _context;
	private Map<Class<?> /* EJB Remote Class */, Object /* LookedUp EJB Remote Object */> _ejbRemoteCache;
	
	private static final String EJB_SERVER_HOST = "localhost";	// TODO -> PropertyManager.getInstance().getProperty
	private static final String EJB_SERVER_PORT = "3700";		// TODO -- " --
	
	private EJBClient() {
		_ejbRemoteCache = new HashMap<>();
	}
	
	public static EJBClient getInstance() {
		if(_instance == null) {
			_instance = new EJBClient();
		}
		return _instance;
	}
	
	public void startUp() {
		try {
			initInitialContext();
		} catch (Exception e) {
			log.error(e);
		}
	}

	private void initInitialContext() throws NamingException {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
		props.setProperty("org.omg.CORBA.ORBInitialHost", EJB_SERVER_HOST);
		props.setProperty("org.omg.CORBA.ORBInitialPort", EJB_SERVER_PORT);
		_context = new InitialContext(props);
	}
	
	public void close() {
		_instance = null;
		try {
			_context.close();
		} catch (NamingException e) {
			log.error(e);
		}
	}
	
	public <T> T getEJBRemote(Class<T> ejbClazz) {
		if (ejbClazz == null) {
			return null;
		}
		
		// Check the cache
		if (_ejbRemoteCache.containsKey(ejbClazz)) {
			return ejbClazz.cast(_ejbRemoteCache.get(ejbClazz));
		}
		
		// We assume, that he default ejb names are used
		// those created when using @EJB without anything else
		String lookupName = ejbClazz.getName();
		
		Object lookedUpObject;
		try {
			lookedUpObject = _context.lookup(lookupName);
		} catch (NamingException e) {
			log.error(e);
			lookedUpObject = null;
		}
		
		if (lookedUpObject != null) {
			_ejbRemoteCache.put(ejbClazz, lookedUpObject);
			return ejbClazz.cast(lookedUpObject);
		} else {
			return null;
		}
	}
}
