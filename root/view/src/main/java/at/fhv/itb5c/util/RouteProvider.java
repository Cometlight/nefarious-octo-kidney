package at.fhv.itb5c.util;

import java.util.HashMap;

public class RouteProvider {
	private HashMap<Object, String> _rootMapping;
	
	private RouteProvider() {
		_rootMapping = new HashMap<>();
	}
	
	private RouteProvider _instance;
	public RouteProvider getInstance() {
		if(_instance == null) {
			_instance = new RouteProvider();
		}
		
		return _instance;
	}
	
	public void addRoot(Object controller, String root) throws ControllerHasRouteException {
		if(_rootMapping.containsKey(controller)) {
			throw new ControllerHasRouteException("The controller " + controller.getClass() + "has a registrated route.");
		}
		else {
			_rootMapping.put(controller, root);
		}
	}
	
	public String getRoot(Object controller) {
		if(_rootMapping.containsKey(controller)) {
			return null;
		}
		else {
			return _rootMapping.get(controller);
		}
	}
}
