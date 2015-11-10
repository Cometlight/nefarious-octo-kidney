package at.fhv.itb5c.view.department;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;

public class DepartmentViewController {	
    @FXML private Label _departmentNameLabel;
    @FXML private Label _headLabel;
    @FXML private ListView<?> _teamList;
    @FXML private Button _addTeamButton;
    @FXML private ListView<?> _tournamentList;
    @FXML private Label _typeOfSportLabel;
    @FXML private Button _addTournamentButton;

    private DepartmentViewModel _departmentViewModel;
    
    public DepartmentViewController(DepartmentViewModel departmentViewModel) {
    	_departmentViewModel = departmentViewModel;
    }
    
    @FXML
    public void initialize() {
    	/*_departmentNameLabel.textProperty().bind(_departmentViewModel.getDepartmentName());
    	_headLabel.textProperty().bind(_departmentViewModel.getNameHeadOfDepartment());
    	_typeOfSportLabel.textProperty().bind(_departmentViewModel.getTypeOfSport());*/
    }
    
    @FXML
    void _onAddTeamButtonClick(ActionEvent event) {

    }

    @FXML
    void _onAddTournamentButtonClick(ActionEvent event) {

    }

}
