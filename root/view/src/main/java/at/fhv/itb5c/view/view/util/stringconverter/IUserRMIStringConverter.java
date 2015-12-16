package at.fhv.itb5c.view.view.util.stringconverter;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.view.util.popup.ErrorPopUp;
import javafx.util.StringConverter;

public class IUserRMIStringConverter extends StringConverter<IUserRMI> implements ILogger{	
		@Override
		public IUserRMI fromString(String userDescriptor) {
			return null;
		}

		@Override
		public String toString(IUserRMI user) {
			try {
				return user.getFirstName() + " " + user.getLastName();
			} catch (RemoteException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
				return null;
			}
		}
}
