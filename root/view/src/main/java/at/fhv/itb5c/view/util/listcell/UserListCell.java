package at.fhv.itb5c.view.util.listcell;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.scene.control.ListCell;

public class UserListCell extends ListCell<IUserRMI> implements ILogger {
	@Override
	protected void updateItem(IUserRMI item, boolean empty) {
		super.updateItem(item, empty);
		if((item != null) && (!empty)) {
			try {
				setText(item.getFirstName() + " " + item.getLastName());
			} catch (RemoteException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		}
	}
}
