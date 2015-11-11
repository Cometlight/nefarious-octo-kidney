package at.fhv.itb5c.view.team.add;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.io.IOException;
import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ILeagueRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.department.DepartmentViewFactory;
import at.fhv.itb5c.view.team.view.TeamViewFactory;
import at.fhv.itb5c.view.util.cellfactory.UserListCell;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

public class TeamAddController implements IPanelClosable, ILogger {
	@FXML
	private ListView<IUserRMI> _userSearchResultList;
	@FXML
	private Button _cancelButton;
	@FXML
	private ComboBox<ILeagueRMI> _leagueChoice;
	@FXML
	private TextField _teamNameInput;
	@FXML
	private TextField _userSearchInput;
	@FXML
	private Button _saveButton;

	private TeamAddModel _teamAddModel;

	public TeamAddController(IDepartmentRMI department) {
		_teamAddModel = new TeamAddModel(department);
	}

	@FXML
	public void initialize() {
		_userSearchResultList.setItems(_teamAddModel.getSearchResults());

		try {
			_leagueChoice.setItems(
					FXCollections.observableArrayList(RMIClient.getRMIClient().getApplicationFacade().getAllLeagues()));
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
		_leagueChoice.valueProperty().bindBidirectional(_teamAddModel.getLeague());
		_userSearchInput.textProperty().bindBidirectional(_teamAddModel.getSearchInput());
	
		_userSearchResultList.setCellFactory(new Callback<ListView<IUserRMI>, ListCell<IUserRMI>>() {		
			@Override
			public ListCell<IUserRMI> call(ListView<IUserRMI> param) {
				return new UserListCell();
			}
		});
	}

	@FXML
	void _onSearchInputChange(ActionEvent event) {
		_teamAddModel.getSearchResults().clear();
		try {
			_teamAddModel.getSearchResults().addAll(RMIClient.getRMIClient().getApplicationFacade()
					.findUsersSimple(_teamAddModel.getSearchInput().getValue()));
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	@FXML
	void _onSaveButtonClick(ActionEvent event) {
		
		
		
		try {
			_panelCloseHandler.closeNext(new TeamViewFactory());
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}
	
	private boolean saveTeam() {
		ITeamRMI team = RMIClient.getRMIClient().getApplicationFacade().createTeam();
	}

	@FXML
	void _onCancelButtonClick(ActionEvent event) {
		try {
			_panelCloseHandler.closeNext(new DepartmentViewFactory(_teamAddModel.getDepartment()));
		} catch (IOException e) {
			ErrorPopUp.criticalSystemError();
			log.error(e.getMessage());
		}
	}

	private IPanelCloseHandler _panelCloseHandler;

	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}

}
