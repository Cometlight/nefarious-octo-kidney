package at.fhv.itb5c.view.tournament.addmatchresult;

import java.io.IOException;
import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.AppState;
import at.fhv.itb5c.view.tournament.TournamentViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MatchAddResultController implements IPanelClosable, ILogger {
	@FXML
	private Label _teamOneLabel;
	@FXML
	private Label _teamTwoLabel;
	@FXML
	private TextField _teamOneInput;
	@FXML
	private TextField _teamTwoInput;
	@FXML
	private Button _saveButton;
	@FXML
	private Button _cancelButton;
	
	private IMatchRMI _match;
	private IDepartmentRMI _department;
	private ITournamentRMI _tournament;

	public MatchAddResultController(IMatchRMI match, IDepartmentRMI department, ITournamentRMI tournament) {
		_match = match;
		_department = department;
		_tournament = tournament;
	}
	
	@FXML
	public void initialize(){
		try {
			if(_match.getTeamOne() instanceof String){
				_teamOneLabel.setText((String) _match.getTeamOne());
			} else {
				_teamOneLabel.setText(RMIClient.getRMIClient().getApplicationFacade().getTeamById(AppState.getInstance().getSessionID(), (Long) _match.getTeamOne()).getName());
			}
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
		
		try {
			if(_match.getTeamTwo() instanceof String){
				_teamTwoLabel.setText((String) _match.getTeamTwo());
			} else {
				_teamTwoLabel.setText(RMIClient.getRMIClient().getApplicationFacade().getTeamById(AppState.getInstance().getSessionID(), (Long) _match.getTeamTwo()).getName());
			}
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
	}
	
	// Event Listener on Button[#_saveButton].onAction
	@FXML
	public void saveButtonAction(ActionEvent event) {
		try {
			_match.setResultTeamOne(Integer.valueOf(_teamOneInput.getText()));
			_match.setResultTeamTwo(Integer.valueOf(_teamTwoInput.getText()));
			
			_match = RMIClient.getRMIClient().getApplicationFacade().saveMatch(AppState.getInstance().getSessionID(), _match, _department);
		
			_panelCloseHandler.closeNext(new TournamentViewFactory(_department, _tournament));
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			ErrorPopUp.generalError("Results", "Results need to be a number!");
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
	}
	
	// Event Listener on Button[#_cancelButton].onAction
	@FXML
	public void cancelButtonAction(ActionEvent event) {
		try {
			_panelCloseHandler.closeNext(new TournamentViewFactory(_department, _tournament));
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
