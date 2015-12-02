package at.fhv.itb5c.view.util.listcell;

import at.fhv.itb5c.application.dto.TeamDTO;

public class TeamListCell extends SimpleListCell<TeamDTO>{

	@Override
	protected void format(TeamDTO item) {
		setText(item.getName());
	}

}
