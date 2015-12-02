package at.fhv.itb5c.view.util.listcell;

import at.fhv.itb5c.application.dto.LeagueDTO;

public class LeagueListCell extends SimpleListCell<LeagueDTO> {

	@Override
	protected void format(LeagueDTO item) {
		setText(item.getName());
	}

}
