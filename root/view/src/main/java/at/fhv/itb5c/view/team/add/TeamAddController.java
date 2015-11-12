package at.fhv.itb5c.view.team.add;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.listcell.LeagueListCell;
import at.fhv.itb5c.view.util.listcell.UserListCell;
import at.fhv.itb5c.view.util.popup.DataModificationPopUp;
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

		_leagueChoice.setCellFactory(new Callback<ListView<ILeagueRMI>, ListCell<ILeagueRMI>>() {
			@Override
			public ListCell<ILeagueRMI> call(ListView<ILeagueRMI> param) {
				return new LeagueListCell();
			}
		});

		_leagueChoice.setButtonCell(new LeagueListCell());

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

		_teamNameInput.textProperty().bindBidirectional(_teamAddModel.getTeamName());
	}

	@FXML
	void _userSearchResultMouseClick(MouseEvent mouseEvent) {
		_teamAddModel.getCoach().set(_userSearchResultList.getSelectionModel().getSelectedItem());
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

		ITeamRMI savedTeam = null;
		if ((savedTeam = saveTeam()) != null) {
			DataModificationPopUp.dataSavedPopUp("Team was added!");

			try {
				_panelCloseHandler.closeNext(new TeamViewFactory(_teamAddModel.getDepartment(), savedTeam));
			} catch (IOException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		}
	}

	private ITeamRMI saveTeam() {
		try {
			if ((_teamAddModel.getTeamName().getValue() != "" && _teamAddModel.getTeamName().getValue() != null) &&
					(_teamAddModel.getCoach().getValue() != null)) {
				ITeamRMI team = RMIClient.getRMIClient().getApplicationFacade().createTeam();
				team.setCoachId(_teamAddModel.getCoach().getValue().getId());
				
				if (_teamAddModel.getLeague().getValue() != null) {
					team.setLeagueId(_teamAddModel.getLeague().getValue().getId());
				}
				
				team.setName(_teamAddModel.getTeamName().getValue());
				team.setDepartmentId(_teamAddModel.getDepartment().getId());
				ITeamRMI saveTeam = RMIClient.getRMIClient().getApplicationFacade().saveTeam(team);
				return saveTeam;
			} else {
				ErrorPopUp.generalError("Mandatory Fields", "Please set the mandatory field: Team Name, Coach");
				return null;
			}
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
			return null;
		}
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
