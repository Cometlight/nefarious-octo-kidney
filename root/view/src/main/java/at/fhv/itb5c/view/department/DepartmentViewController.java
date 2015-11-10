package at.fhv.itb5c.view.department;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.team.add.TeamAddViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;

public class DepartmentViewController implements IPanelClosable, ILogger {	
    @FXML private Label _departmentNameLabel;
    @FXML private Label _headLabel;
    @FXML private ListView<?> _teamList;
    @FXML private Button _addTeamButton;
    @FXML private ListView<?> _tournamentList;
    @FXML private Label _typeOfSportLabel;
    @FXML private Button _addTournamentButton;

    private DepartmentViewModel _departmentViewModel;
    private IPanelCloseHandler _panelCloseHandler;
    
    public DepartmentViewController(DepartmentViewModel departmentViewModel) {
    	_departmentViewModel = departmentViewModel;
    }
    
    @FXML
    public void initialize() {
    	_departmentNameLabel.textProperty().bind(_departmentViewModel.getDepartmentName());
    	_headLabel.textProperty().bind(_departmentViewModel.getNameHeadOfDepartment());
    	_typeOfSportLabel.textProperty().bind(_departmentViewModel.getTypeOfSport());
    }
    
    @FXML
    void _onAddTeamButtonClick(ActionEvent event) {
    	try {
			_panelCloseHandler.closeNext(new TeamAddViewFactory(_departmentViewModel.getDepartment()));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
    }

    @FXML
    void _onAddTournamentButtonClick(ActionEvent event) {

    }

	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}

}
