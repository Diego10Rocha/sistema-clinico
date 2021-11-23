package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.MedicoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import message.MessageAlert;
import model.Medico;
import screenManager.ScreenManager;

public class MedicoRecepcionistaController implements Initializable, EventHandler<ActionEvent> {

	@FXML
	private ListView<Medico> lvMedicos;

	@FXML
	private Button btnRemoverMedico;

	@FXML
	private Button btnEditarMedico;

	@FXML
	private Button btnVoltar;

	private static Medico medicoSelecionado;

	private FormularioMedicoEditController formularioMedicoEdit;

	private MessageAlert msg = new MessageAlert();

	private ObservableList<Medico> obsMedicos;

	private ScreenManager screenManager = new ScreenManager();

	public void loadMedicos() {

		List<Medico> medicoCadastrados = MedicoDAO.getDoctors();

		obsMedicos = FXCollections.observableArrayList(medicoCadastrados);

		lvMedicos.setItems(obsMedicos);
	}

	@FXML
	void closeScreen(ActionEvent event) {

	}

	@FXML
	void openScreenFormularioEditMedico(ActionEvent event) throws IOException {

		medicoSelecionado = lvMedicos.getSelectionModel().getSelectedItem();

		if (medicoSelecionado == null) {

			msg.showMessage("Por Favor selecione uma Médico primeiro!", AlertType.WARNING);

		}

		else {

			screenManager.openNewScreen("FormularioMedicoEdit", "Edição médicos");

			setReferenciaFormularioMedicoEdit();

		}
	}

	private void setReferenciaFormularioMedicoEdit() {

		Object currentController = screenManager.getCurrenController();

		formularioMedicoEdit = (FormularioMedicoEditController) currentController;

		formularioMedicoEdit.addButtonsListener(this);

	}

	@FXML
	void removerMedico(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		formularioMedicoEdit = new FormularioMedicoEditController();

		loadMedicos();

	}

	public static Medico getMedicoSelecionado() {

		return medicoSelecionado;
	}

	@Override
	public void handle(ActionEvent event) {
		
		if (event.getSource() == formularioMedicoEdit.getBtnSalvar()) {

			formularioMedicoEdit.salvarMedicoEdit();;

			loadMedicos();;

		}

		else if (event.getSource() == formularioMedicoEdit.getBtnCancelar()) {

			formularioMedicoEdit.closeScreen();

		}

	}

}
