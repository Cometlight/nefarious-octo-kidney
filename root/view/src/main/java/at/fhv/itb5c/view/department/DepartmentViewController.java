package at.fhv.itb5c.view.department;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import java.io.IOException;
import java.rmi.RemoteException;

import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.ApplicationFacadeRMIStub;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.team.add.TeamAddViewFactory;
import at.fhv.itb5c.view.team.view.TeamViewFactory;
import at.fhv.itb5c.view.tournament.TournamentViewFactory;
import at.fhv.itb5c.view.tournament.add.TournamentAddFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.listcell.TeamListCell;
import at.fhv.itb5c.view.util.listcell.TournamentListCell;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;

public class DepartmentViewController implements IPanelClosable, ILogger {
	@FXML
	private Label _departmentNameLabel;
	@FXML
	private Label _headLabel;
	@FXML
	private ListView<ITeamRMI> _teamList;
	@FXML
	private Button _addTeamButton;
	@FXML
	private ListView<ITournamentRMI> _tournamentList;
	@FXML
	private Label _typeOfSportLabel;
	@FXML
	private Button _addTournamentButton;

	private DepartmentViewModel _departmentViewModel;

	public DepartmentViewController(DepartmentViewModel departmentViewModel) {
		_departmentViewModel = departmentViewModel;
	}

	@FXML
	public void initialize() {
		_departmentNameLabel.textProperty().bind(_departmentViewModel.getDepartmentName());
		_headLabel.textProperty().bind(_departmentViewModel.getNameHeadOfDepartment());
		_typeOfSportLabel.textProperty().bind(_departmentViewModel.getTypeOfSport());
		_teamList.setItems(_departmentViewModel.getTeams());
		_teamList.setCellFactory(new Callback<ListView<ITeamRMI>, ListCell<ITeamRMI>>() {
			@Override
			public ListCell<ITeamRMI> call(ListView<ITeamRMI> param) {
				return new TeamListCell();
			}
		});
		try {
			_departmentViewModel.getTeams()
					.setAll(RMIClient.getRMIClient().getApplicationFacade().findTeams(
							AppState.getInstance().getSessionID(), null, null,
							_departmentViewModel.getDepartment().getId(), null, null));
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
		_tournamentList.setItems(_departmentViewModel.getTournaments());
		_tournamentList.setCellFactory(new Callback<ListView<ITournamentRMI>, ListCell<ITournamentRMI>>() {
			@Override
			public ListCell<ITournamentRMI> call(ListView<ITournamentRMI> param) {
				return new TournamentListCell();
			}
		});
		try {
			_departmentViewModel.getTournaments()
					.setAll(RMIClient.getRMIClient().getApplicationFacade().findTournaments(
							AppState.getInstance().getSessionID(), null, _departmentViewModel.getDepartment().getId()));
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
		
		// deactivte buttons if user is not ADMIN or head of department
		String sessionId = AppState.getInstance().getSessionID();
    	ApplicationFacadeRMIStub afRMI = RMIClient.getRMIClient().getApplicationFacade();
    	try {
			if(!afRMI.hasRole(sessionId, UserRole.Admin) && !afRMI.isDepartmentHead(sessionId, _departmentViewModel.getDepartment())) {
				_addTeamButton.setDisable(true);
				_addTournamentButton.setDisable(true);
			}
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
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
	void _teamListOnMouseClick(MouseEvent mouseEvent) {
		ITeamRMI team = _teamList.getSelectionModel().getSelectedItem();
		if (team != null) {
			try {
				_panelCloseHandler.closeNext(new TeamViewFactory(_departmentViewModel.getDepartment(), team));
			} catch (IOException e) {
				log.error(e.getMessage());
				ErrorPopUp.criticalSystemError();
			}
		}
	}
	
	@FXML
	void _tournamentListOnMouseClick(MouseEvent mouseEvent) {
		ITournamentRMI tournament = _tournamentList.getSelectionModel().getSelectedItem();
		if (tournament != null) {
			try {
				_panelCloseHandler.closeNext(new TournamentViewFactory(_departmentViewModel.getDepartment(), tournament));
			} catch (IOException e) {
				log.error(e.getMessage());
				ErrorPopUp.criticalSystemError();
			}
		}
	}

	@FXML
	void _onAddTournamentButtonClick(ActionEvent event) {
		try {
			_panelCloseHandler.closeNext(new TournamentAddFactory(_departmentViewModel.getDepartment()));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
	}

	private IPanelCloseHandler _panelCloseHandler;

	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}

}
