package at.fhv.itb5c.view.util.interfaces;

import java.io.IOException;

public interface PanelCloseHandler {
	public void close();
	
	public void closeNext(IViewFactory viewFactory) throws IOException;
}
