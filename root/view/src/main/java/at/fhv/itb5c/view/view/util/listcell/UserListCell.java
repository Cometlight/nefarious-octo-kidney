package at.fhv.itb5c.view.view.util.listcell;

import at.fhv.itb5c.commons.dto.UserDTO;

public class UserListCell extends SimpleListCell<UserDTO> {
	@Override
	protected void format(UserDTO item) {
		setText(item.getFirstName() + " " + item.getLastName());
	}
}
