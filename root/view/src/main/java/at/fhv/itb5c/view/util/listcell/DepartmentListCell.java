package at.fhv.itb5c.view.util.listcell;

import at.fhv.itb5c.application.dto.DepartmentDTO;

public class DepartmentListCell extends SimpleListCell<DepartmentDTO>{
	@Override
	protected void format(DepartmentDTO item) {
		setText(item.getName());
	}
}