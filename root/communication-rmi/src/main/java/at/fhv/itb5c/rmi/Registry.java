package at.fhv.itb5c.rmi;

public class Registry {
	private Registry _registry;
	
	private Registry(){
		
	}
	
	public Registry getRegistry(){
		if(_registry == null){
			_registry = new Registry();
		}
		return _registry;
	}
}