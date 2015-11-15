package at.fhv.itb5c.view.tournament;

import java.io.IOException;
import java.text.DecimalFormat;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.AppState;
import at.fhv.itb5c.view.department.DepartmentViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TournamentAddController implements IPanelClosable, ILogger {
	@FXML
	private TextField _tournamentName;
	@FXML
	private DatePicker _date;
	@FXML
	private TextField _fee;

	private TournamentModel _tournamentModel;

	@FXML
	public void initialize() {
		_tournamentName.textProperty().bindBidirectional(_tournamentModel.getTournamentName());
		_date.valueProperty().bindBidirectional(_tournamentModel.getDate());
		_fee.textProperty().bindBidirectional(_tournamentModel.getFee().asObject(), new DecimalFormat());
	}

	// Event Listener on Button.onMouseClicked
	@FXML
	public void _saveAndNextButtonClicked(MouseEvent event) {
		try {
			_tournamentModel.setITournamentRMI(RMIClient.getRMIClient().getApplicationFacade()
					.createTournament(AppState.getInstance().getSessionID()));
			log.debug("tournament name = " + _tournamentModel.getTournamentName().getValue() + ", " + _tournamentName.textProperty().getValue());
			ITournamentRMI tournament = RMIClient.getRMIClient().getApplicationFacade().saveTournament(AppState.getInstance().getSessionID(),
					_tournamentModel.getITournamentRMI());
			
			_panelCloseHandler.closeNext(new TournamentAddTeamsFactory(_department, tournament));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	public TournamentAddController(IDepartmentRMI department) {
		_department = department;
		_tournamentModel = new TournamentModel();
	}

	private IDepartmentRMI _department;

	// Event Listener on Button.onMouseClicked
	@FXML
	public void _cancelButtonClicked(MouseEvent event) {
		try {
			_panelCloseHandler.closeNext(new DepartmentViewFactory(_department));
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
