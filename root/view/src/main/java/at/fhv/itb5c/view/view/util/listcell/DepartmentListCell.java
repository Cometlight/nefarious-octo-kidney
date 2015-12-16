package at.fhv.itb5c.view.view.util.listcell;

import at.fhv.itb5c.commons.dto.DepartmentDTO;

public class DepartmentListCell extends SimpleListCell<DepartmentDTO>{
	@Override
	protected void format(DepartmentDTO item) {
		setText(item.getName());
	}
}