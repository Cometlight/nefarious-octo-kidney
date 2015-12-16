package at.fhv.itb5c.view.view.mainview;

import at.fhv.itb5c.view.view.util.factories.AbstractPanelAndViewFactory;

public class MainViewFactory extends AbstractPanelAndViewFactory{

	public MainViewFactory() {
		super("/view/fxml/MainView.fxml", new MainViewController());
	}
}
