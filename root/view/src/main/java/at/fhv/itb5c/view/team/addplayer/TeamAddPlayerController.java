package at.fhv.itb5c.view.team.addplayer;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;
import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.communication.CommunicationErrorException;
import at.fhv.itb5c.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.team.view.TeamViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.listcell.UserListCell;
import at.fhv.itb5c.view.util.popup.DataModificationPopUp;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class TeamAddPlayerController implements IPanelClosable, ILogger {
	@FXML
	private ListView<UserDTO> _userSearchResultList;
	@FXML
	private Button _cancelButton;
	@FXML
	private TextField _userSearchInput;
	@FXML
	private Button _addPlayerButton;

	private TeamAddPlayerModel _teamAddPlayerModel;
	private IPanelCloseHandler _panelCloseHandler;

	public TeamAddPlayerController(TeamDTO team) {
		_teamAddPlayerModel = new TeamAddPlayerModel(team);
	}

	@FXML
	public void initialize() {
		_userSearchResultList.setItems(_teamAddPlayerModel.getSearchResults());
		_userSearchInput.textProperty().bindBidirectional(_teamAddPlayerModel.getSearchInput());

		_userSearchResultList.setCellFactory(new Callback<ListView<UserDTO>, ListCell<UserDTO>>() {
			@Override
			public ListCell<UserDTO> call(ListView<UserDTO> param) {
				return new UserListCell();
			}
		});
	}

	@FXML
	void _userSearchResultMouseClick(MouseEvent mouseEvent) {
		_teamAddPlayerModel.getPlayer().set(_userSearchResultList.getSelectionModel().getSelectedItem());
	}

	@FXML
	void _onSearchInputChange(ActionEvent event) {
		_teamAddPlayerModel.getSearchResults().clear();
		_userSearchResultList.getItems().clear();
		try {
			Collection<UserDTO> usersFound = CommunicationFacadeProvider.getInstance().getCurrentFacade().findUsersSimple(
					AppState.getInstance().getSessionID(), _teamAddPlayerModel.getSearchInput().getValue());
			
			// filter out users that are already a member of this team
			usersFound = usersFound.stream().filter(user -> {
				try {
					return !_teamAddPlayerModel.getTeam().getMemberIds().contains(user.getId());
				} catch (Exception e) {
					log.error(e.getMessage());
					return false;
				}
			}).collect(Collectors.toList());
			
			_teamAddPlayerModel.getSearchResults().addAll(usersFound);
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	@FXML
	void _onAddPlayerButtonClick(ActionEvent event) {

		TeamDTO updatedTeam = null;

		if ((updatedTeam = addPlayerToTeam()) != null) {
			DataModificationPopUp.dataSavedPopUp("Player was added!");

			try {
				_panelCloseHandler.closeNext(new TeamViewFactory(getDepartment(), updatedTeam));
			} catch (IOException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			 }
		} else {
			ErrorPopUp.generalError("Player not added",
					"Failed to add player to team. Please try again or contact the system administrator.");
		}
	}

	/**
	 * @return the updated team; or null, if something went wrong
	 */
	private TeamDTO addPlayerToTeam() {
		try {
			if (_teamAddPlayerModel.getPlayer().getValue() != null) {
				return CommunicationFacadeProvider.getInstance().getCurrentFacade().addPlayerToTeam(
						AppState.getInstance().getSessionID(), _teamAddPlayerModel.getTeam(),
						_teamAddPlayerModel.getPlayer().getValue());
			}
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
		return null;
	}

	@FXML
	void _onCancelButtonClick(ActionEvent event) {
		try {
			_panelCloseHandler.closeNext(new TeamViewFactory(getDepartment(), _teamAddPlayerModel.getTeam()));
		} catch (IOException e) {
			ErrorPopUp.criticalSystemError();
			log.error(e.getMessage());
		}
	}

	private DepartmentDTO getDepartment() {
		TeamDTO team = _teamAddPlayerModel.getTeam();
		try {
			return CommunicationFacadeProvider.getInstance().getCurrentFacade()
					.getDepartmentById(AppState.getInstance().getSessionID(), team.getDepartmentId());
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}
}
