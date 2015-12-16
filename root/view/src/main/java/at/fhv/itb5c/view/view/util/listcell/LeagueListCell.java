package at.fhv.itb5c.view.view.util.listcell;

import at.fhv.itb5c.commons.dto.LeagueDTO;

public class LeagueListCell extends SimpleListCell<LeagueDTO> {

	@Override
	protected void format(LeagueDTO item) {
		setText(item.getName());
	}

}
