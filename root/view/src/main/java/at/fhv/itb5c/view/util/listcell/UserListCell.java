package at.fhv.itb5c.view.util.listcell;

import at.fhv.itb5c.application.dto.UserDTO;

public class UserListCell extends SimpleListCell<UserDTO> {
	@Override
	protected void format(UserDTO item) {
		setText(item.getFirstName() + " " + item.getLastName());
	}
}
