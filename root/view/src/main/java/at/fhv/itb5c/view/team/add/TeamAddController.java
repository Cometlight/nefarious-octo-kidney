package at.fhv.itb5c.view.team.add;

import javafx.fxml.FXML;
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
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.department.DepartmentViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

public class TeamAddController implements IPanelClosable, ILogger{
    @FXML private ListView<IUserRMI> _userSearchResultList;
    @FXML private ComboBox<ILeagueRMI> _leagueChoice;
    @FXML private TextField _teamNameInput;
    @FXML private TextField _userSearchInput;

    private TeamAddModel _teamAddModel;    
    private IPanelCloseHandler _panelCloseHandler;

    public TeamAddController(IDepartmentRMI department) {
		_teamAddModel = new TeamAddModel(department);
	}
    
    @FXML
    public void initialize() {
    	_teamNameInput.textProperty().bindBidirectional(_teamAddModel.getName());
    	
    	//TODO(san7985) add all available leagues to the _leagueChoice when the methode is available
    	_leagueChoice.valueProperty().bindBidirectional(_teamAddModel.getLeague());
    	
    	_userSearchResultList.setItems(_teamAddModel.getSearchResult());
    	_userSearchResultList.setCellFactory(new Callback<ListView<IUserRMI>, ListCell<IUserRMI>>() {
			
			@Override
			public ListCell<IUserRMI> call(ListView<IUserRMI> param) {
				return new TeamListView();
			}
		});
    	_userSearchInput.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				_teamAddModel.getSearchResult().clear();
				try {
					_teamAddModel.getSearchResult().addAll(RMIClient.getRMIClient().getUserFactory().findUsersSimple(newValue));
				} catch (RemoteException e) {
					log.error(e.getMessage());
					ErrorPopUp.connectionError();
				}
			}
		});	
    }
    
    public class TeamListView extends ListCell<IUserRMI> {
    	@Override
    	protected void updateItem(IUserRMI item, boolean empty) {
    		super.updateItem(item, empty);	
    		if((item != null) && (!empty)) {
				try {
					setText(item.getFirstName() + " " + item.getLastName());
				} catch (RemoteException e) {
					log.error(e.getMessage());
					ErrorPopUp.connectionError();
				}
			}
    	}
    }
    
	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}
	
	@FXML
	void _onSearchResultClick(MouseEvent event) {
		_teamAddModel.getCoach().set(_userSearchResultList.getSelectionModel().getSelectedItem());
	}
   
    @FXML
    void _onSaveButtonClick(ActionEvent event) {
    	try {
    		//TODO: save team
			_panelCloseHandler.closeNext(new DepartmentViewFactory(_teamAddModel.getDepartment()));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
    }

    @FXML
    void _onCancelButtonClick(ActionEvent event) {
    	try {
			_panelCloseHandler.closeNext(new DepartmentViewFactory(_teamAddModel.getDepartment()));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
    }
}
