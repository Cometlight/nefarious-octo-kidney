package at.fhv.itb5c.view.mainview;

import java.util.Collection;

import at.fhv.itb5c.application.dto.DepartmentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainViewModel {
	private ObservableList<DepartmentDTO> _departments;
	
	public MainViewModel(Collection<DepartmentDTO> departments) {
		_departments = FXCollections.observableArrayList(departments);
	}
	
	public ObservableList<DepartmentDTO> getDepartments() {
		return _departments;
	}
}
