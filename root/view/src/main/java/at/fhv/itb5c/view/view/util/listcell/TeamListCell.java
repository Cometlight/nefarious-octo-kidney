package at.fhv.itb5c.view.view.util.listcell;

import at.fhv.itb5c.commons.dto.TeamDTO;

public class TeamListCell extends SimpleListCell<TeamDTO>{

	@Override
	protected void format(TeamDTO item) {
		setText(item.getName());
	}

}
