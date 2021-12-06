package controller.medico;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import screenManager.ScreenManager;

public class MedicoController {

	@FXML
	private Button btnConsulta;

	@FXML
	private Button btnPacientes;

	@FXML
	private Button btnSair;

	ScreenManager screenManager = new ScreenManager();

	@FXML
	void exit(ActionEvent event) {

		ScreenManager.closeScreen(btnSair);
	}

	@FXML
	void openScreenConsultas(ActionEvent event) throws IOException {

		screenManager.openNewScreen("consulta/ConsultaMedicoScreen", "Atendimento");

	}

	@FXML
	void openScreenMedicoPacientes(ActionEvent event) throws IOException {

		screenManager.openNewScreen("medico/MedicoPacientes", "Pacientes");

	}

}
