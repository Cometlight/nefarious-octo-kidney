package at.fhv.itb5c.view.team.view;

import java.io.IOException;
import java.rmi.RemoteException;

import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.ApplicationFacadeRMIStub;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.department.DepartmentViewFactory;
import at.fhv.itb5c.view.team.addplayer.TeamAddPlayerViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.listcell.UserListCell;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class TeamViewController implements IPanelClosable, ILogger{
    @FXML private Label _leagueLabel;
    @FXML private Button _editButton;
    @FXML private Label _teamNameLabel;
    @FXML private Button _cancelButton;
    @FXML private Label _coachLabel;
    @FXML private ListView<IUserRMI> _userList;
    
    private TeamViewModel _teamViewModel;
    
    public TeamViewController(IDepartmentRMI department, ITeamRMI team) {
		_teamViewModel = new TeamViewModel(department, team);
	}
    
    @FXML
    public void initialize() {
    	_teamNameLabel.textProperty().bindBidirectional(_teamViewModel.getTeamName());
    	_coachLabel.textProperty().bindBidirectional(_teamViewModel.getCoachName());
    	_leagueLabel.textProperty().bindBidirectional(_teamViewModel.getLeagueName());
    	_userList.setCellFactory(new Callback<ListView<IUserRMI>, ListCell<IUserRMI>>() {
			@Override
			public ListCell<IUserRMI> call(ListView<IUserRMI> param) {
				return new UserListCell();
			}
		});
    	_userList.setItems(_teamViewModel.getMembers());
    	
    	// Only the admin or the coach of this particular team is allowed to add members -> deactive edit button otherwise
    	String sessionId = AppState.getInstance().getSessionID();
    	ApplicationFacadeRMIStub afRMI = RMIClient.getRMIClient().getApplicationFacade();
    	try {
			if(!afRMI.hasRole(sessionId, UserRole.Admin) && !afRMI.isCoach(sessionId, _teamViewModel.getTeam())) {
				_editButton.setDisable(true);
			}
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
    }

	@FXML
    void _onEditButtonClick(ActionEvent event) {
		try {
			_panelCloseHandler.closeNext(new TeamAddPlayerViewFactory(_teamViewModel.getTeam()));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
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
