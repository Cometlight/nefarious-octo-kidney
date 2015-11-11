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
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.team.add.TeamAddViewFactory;
import at.fhv.itb5c.view.team.view.TeamViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.listcell.TeamListCell;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;

public class DepartmentViewController implements IPanelClosable, ILogger {	
    @FXML private Label _departmentNameLabel;
    @FXML private Label _headLabel;
    @FXML private ListView<ITeamRMI> _teamList;
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
			_departmentViewModel.getTeams().setAll(RMIClient.getRMIClient().getApplicationFacade().findTeams(null, null, _departmentViewModel.getDepartment().getId(), null));
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
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
    	try {
			_panelCloseHandler.closeNext(new TeamViewFactory(_departmentViewModel.getDepartment(), _teamList.getSelectionModel().getSelectedItem()));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
    }

    @FXML
    void _onAddTournamentButtonClick(ActionEvent event) {

    }

    private IPanelCloseHandler _panelCloseHandler;
	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}

}
