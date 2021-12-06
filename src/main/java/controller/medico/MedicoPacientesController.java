package controller.medico;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.MedicoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import login.Login;
import message.MessageAlert;
import model.Medico;
import model.Paciente;
import screenManager.ScreenManager;

public class MedicoPacientesController implements Initializable {

	@FXML
	private ListView<Paciente> lvPacientes;

	@FXML
	private Button btnProntuarios;

	@FXML
	private Button btnVoltar;

	private Medico medicoQueDetemPacientes;
	private ObservableList<Paciente> obsPacientes;
	private ScreenManager screenManager = new ScreenManager();
	private static Paciente pacienteSelecionado;

	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	@FXML
	void openScreenProntuariosPacientes(ActionEvent event) throws IOException {

		pacienteSelecionado = lvPacientes.getSelectionModel().getSelectedItem();

		if (pacienteSelecionado == null) {

			new MessageAlert().showMessage("Por Favor selecione um Paciente primeiro!", AlertType.WARNING);

		}

		else {

			screenManager.openNewScreen("paciente/ProntuariosPaciente", "Prontuarios");

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		medicoQueDetemPacientes = MedicoDAO.findByCPF(Login.getCPF_userLogged());

		loadPacientes();

	}

	private void loadPacientes() {

		List<Paciente> medicoPacientes = medicoQueDetemPacientes.getPacientes();

		obsPacientes = FXCollections.observableArrayList(medicoPacientes);

		lvPacientes.setItems(obsPacientes);
	}

	public static Paciente getPacienteSelecionado() {

		return pacienteSelecionado;
	}

}
