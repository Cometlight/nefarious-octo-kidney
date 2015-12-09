package at.fhv.itb5c.view.util.stringconverter;

import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.logging.ILogger;
import javafx.util.StringConverter;

public class UserDTOStringConverter extends StringConverter<UserDTO> implements ILogger{	
		@Override
		public UserDTO fromString(String userDescriptor) {
			return null;
		}

		@Override
		public String toString(UserDTO user) {
			return user.getFirstName() + " " + user.getLastName();
		}
}
