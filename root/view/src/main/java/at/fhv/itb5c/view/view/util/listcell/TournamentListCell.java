package at.fhv.itb5c.view.view.util.listcell;

import at.fhv.itb5c.commons.dto.TournamentDTO;

public class TournamentListCell extends SimpleListCell<TournamentDTO> {

	@Override
	protected void format(TournamentDTO item) {
		setText(item.getName());
	}

}
