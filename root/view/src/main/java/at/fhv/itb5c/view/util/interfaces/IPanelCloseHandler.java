package at.fhv.itb5c.view.util.interfaces;

import java.io.IOException;

import at.fhv.itb5c.view.util.factories.IViewFactory;

public interface IPanelCloseHandler {
	public void close();
	
	public void closeNext(IViewFactory viewFactory) throws IOException;
}
