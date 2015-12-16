package at.fhv.itb5c.view.view.util.listcell;

import at.fhv.itb5c.commons.dto.TeamDTO;

public class ObjectListCell extends SimpleListCell<Object> {

	@Override
	protected void format(Object item) {
		if (item instanceof TeamDTO) {
			setText(((TeamDTO) item).getName());
		} else {
			setText(item.toString());
		}
	}

}
