package at.fhv.itb5c.view.tournament.add;

import java.io.IOException;
import java.text.DecimalFormat;
import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.communication.CommunicationErrorException;
import at.fhv.itb5c.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.department.DepartmentViewFactory;
import at.fhv.itb5c.view.tournament.TournamentModel;
import at.fhv.itb5c.view.tournament.addteams.TournamentAddTeamsFactory;
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
	private DepartmentDTO _department;

	@FXML
	public void initialize() {
		_tournamentName.textProperty().bindBidirectional(_tournamentModel.getTournamentName());
		_date.valueProperty().bindBidirectional(_tournamentModel.getDate());
		_fee.textProperty().bindBidirectional(_tournamentModel.getFee().asObject(), new DecimalFormat());
		try {
			TournamentDTO tournamentRMI = CommunicationFacadeProvider.getInstance().getCurrentFacade()
					.createTournament(AppState.getInstance().getSessionID(), _department);
			tournamentRMI.setDepartmentId(_department.getId());
			_tournamentModel.setTournamentDTO(tournamentRMI);
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	// Event Listener on Button.onMouseClicked
	@FXML
	public void _saveAndNextButtonClicked(MouseEvent event) {
		if (_tournamentName.getText() != null && _tournamentName.getText() != "" && _date.getValue() != null) {
			try {
				TournamentDTO tournament = CommunicationFacadeProvider.getInstance().getCurrentFacade().saveTournament(
						AppState.getInstance().getSessionID(), _tournamentModel.getTournamentDTO(), _department);

				_panelCloseHandler.closeNext(new TournamentAddTeamsFactory(_department, tournament));
			} catch (IOException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			} catch (CommunicationErrorException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		} else {
			ErrorPopUp.generalError("Mandatory Fields", "Please set the mandatory field: Tournament Name, Date");
		}
	}

	public TournamentAddController(DepartmentDTO department) {
		_department = department;
		_tournamentModel = new TournamentModel();
	}

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
