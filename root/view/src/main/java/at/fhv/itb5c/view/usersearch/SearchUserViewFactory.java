package at.fhv.itb5c.view.usersearch;

import at.fhv.itb5c.view.util.factories.AbstractFactory;

public final class SearchUserViewFactory extends AbstractFactory {
	
	public SearchUserViewFactory() {
		super("/view/fxml/SearchUserView.fxml", new SearchUserController());
	}
}