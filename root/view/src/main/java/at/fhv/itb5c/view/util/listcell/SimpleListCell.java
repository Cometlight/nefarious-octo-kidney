package at.fhv.itb5c.view.util.listcell;

import at.fhv.itb5c.logging.ILogger;
import javafx.scene.control.ListCell;

public abstract class SimpleListCell<T> extends ListCell<T> implements ILogger{
	@Override
	protected void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else if (item != null) {
			format(item);
		}
	}
	
	protected abstract void format(T item);
}
