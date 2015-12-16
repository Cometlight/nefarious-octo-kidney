package at.fhv.itb5c.view.view.user;

import java.net.URL;

import at.fhv.itb5c.logging.ILogger;

public interface IUserViewState extends ILogger{
	
	public String getTitel();

	public URL getControlsFXML();
	
	public void activate();
}
