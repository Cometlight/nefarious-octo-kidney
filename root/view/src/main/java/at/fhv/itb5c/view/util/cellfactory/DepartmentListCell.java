package at.fhv.itb5c.view.util.cellfactory;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.scene.control.ListCell;

public class DepartmentListCell extends ListCell<IDepartmentRMI> implements ILogger {
	@Override
	protected void updateItem(IDepartmentRMI item, boolean empty) {
		super.updateItem(item, empty);
		if((item != null) && (!empty)) {
			try {
				setText(item.getName());
			} catch (RemoteException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		}
	}
}