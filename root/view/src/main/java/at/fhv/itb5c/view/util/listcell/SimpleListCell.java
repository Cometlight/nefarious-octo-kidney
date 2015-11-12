package at.fhv.itb5c.view.util.listcell;

import java.rmi.RemoteException;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.scene.control.ListCell;

public abstract class SimpleListCell<T> extends ListCell<T> implements ILogger{
	@Override
	protected void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		if((item != null) && (!empty)) {
			try {
				format(item);
			} catch (RemoteException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		}
	}
	
	protected abstract void format(T item) throws RemoteException;
}
