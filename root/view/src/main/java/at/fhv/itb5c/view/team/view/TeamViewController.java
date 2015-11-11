package at.fhv.itb5c.view.team.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.department.DepartmentViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;

public class TeamViewController implements IPanelClosable, ILogger{
    @FXML private Label _leagueLabel;
    @FXML private Button _editButton;
    @FXML private Label _teamNameLabel;
    @FXML private Button _cancelButton;
    @FXML private Label _coachLabel;
    
    private TeamViewModel _teamViewModel;
    
    public TeamViewController(IDepartmentRMI department, ITeamRMI team) {
		_teamViewModel = new TeamViewModel(department, team);
	}
    
    @FXML
    public void initialize() {
    	_teamNameLabel.textProperty().bindBidirectional(_teamViewModel.getTeamName());
    	_coachLabel.textProperty().bindBidirectional(_teamViewModel.getCoachName());
    	//TODO(san7985) add league name when league test data exists
    }

	@FXML
    void _onEditButtonClick(ActionEvent event) {
		
    }

    @FXML
    void _onCancelButtonClick(ActionEvent event) {
    	try {
			_panelCloseHandler.closeNext(new DepartmentViewFactory(_teamViewModel.getDepartment()));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
    }

    private IPanelCloseHandler _panelCloseHandler;
	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}

}
