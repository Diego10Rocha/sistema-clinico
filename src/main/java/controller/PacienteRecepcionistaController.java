package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.MedicoDAO;
import dao.PacienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import message.MessageAlert;
import model.GerenciadorConsulta;
import model.Paciente;
import screenManager.ScreenManager;

public class PacienteRecepcionistaController implements Initializable, EventHandler<ActionEvent> {

	@FXML
	private ListView<Paciente> lvPacientes;

	@FXML
	private Button btnRemoverPaciente;

	@FXML
	private Button btnEditarPaciente;

	@FXML
	private Button btnVoltar;

	private ObservableList<Paciente> obsPacientes;

	private static Paciente pacienteSelecionado;

	private ScreenManager screenManager = new ScreenManager();

	private MessageAlert msg = new MessageAlert();

	private FormularioPacienteEditController formularioPacienteEdit;

	public void loadPacientes() {

		List<Paciente> pacientesCadastrados = PacienteDAO.getPatients();

		obsPacientes = FXCollections.observableArrayList(pacientesCadastrados);

		lvPacientes.setItems(obsPacientes);
	}

	@FXML
	void closeScreen(ActionEvent event) {

		Stage stage = (Stage) btnVoltar.getScene().getWindow();

		stage.close();
	}

	@FXML
	void openScreenFormularioEditPaciente(ActionEvent event) throws IOException {

		pacienteSelecionado = lvPacientes.getSelectionModel().getSelectedItem();

		if (pacienteSelecionado == null) {

			msg.showMessage("Por Favor selecione um Paciente primeiro!", AlertType.WARNING);

		}

		else {

			screenManager.openNewScreen("FormularioPacienteEdit", "Edição paciente");

			setReferenciaFormularioPacienteEdit();

		}
	}

	private void setReferenciaFormularioPacienteEdit() {

		Object currentController = screenManager.getCurrenController();

		formularioPacienteEdit = (FormularioPacienteEditController) currentController;

		formularioPacienteEdit.addButtonsListener(this);

	}

	@FXML
	void removerPaciente(ActionEvent event) throws Exception {

		pacienteSelecionado = lvPacientes.getSelectionModel().getSelectedItem();

		if (pacienteSelecionado == null) {

			msg.showMessage("Por Favor selecione um Paciente primeiro!", AlertType.WARNING);

		}

		else {

			if (GerenciadorConsulta.hasConsultaRealizada(pacienteSelecionado.getCPF())) {

				msg.showMessage("Falha! Este Paciente possui consultas realizadas no sistema.", AlertType.WARNING);
				
				return;
			}

			else if (GerenciadorConsulta.hasConsultaMarcada(pacienteSelecionado.getCPF())) {

				GerenciadorConsulta.removeAllConsultaContainsCPF(pacienteSelecionado.getCPF());
			}

			PacienteDAO.deletePatient(pacienteSelecionado);
			
			loadPacientes();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadPacientes();

	}

	public static Paciente getPacienteSelecionado() {

		return pacienteSelecionado;
	}

	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == formularioPacienteEdit.getBtnSalvar()) {

			formularioPacienteEdit.salvarPacienteEdit();

			loadPacientes();

		}

		else if (event.getSource() == formularioPacienteEdit.getBtnCancelar()) {

			formularioPacienteEdit.closeScreen();

		}

	}
}
