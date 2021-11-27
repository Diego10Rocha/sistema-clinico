package controller.recepcionista;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import screenManager.ScreenManager;

public class RecepcionistaController {

	@FXML
    private MenuButton menuCadastro;

    @FXML
    private MenuItem menuItemCadastroMedico;

    @FXML
    private MenuItem menuItemCadastroPaciente;

    @FXML
    private MenuItem menuItemCadastroRecepcionista;

    @FXML
    private Button menuItemAgendaConsultas;

    @FXML
    private Button menuItemVerMedicos;

    @FXML
    private Button menuItemVerPacientes;

    @FXML
    private Button menuItemVerEspecialidades;

    @FXML
    private Button btnSair;

	private ScreenManager screenManager = new ScreenManager();

	@FXML
	void openScreenAddConsulta(ActionEvent event) {

	}

	@FXML
	void openScreenCadastroMedico(ActionEvent event) throws IOException {

		screenManager.openNewScreen("medico/FormularioCadastroMedico", "Cadastro Medico");
	}

	@FXML
	void openScreenCadastroPaciente(ActionEvent event) throws IOException {

		screenManager.openNewScreen("paciente/FormularioCadastroPaciente", "Cadastro Paciente");
	}

	@FXML
	void openScreenCadastroRecepcionista(ActionEvent event) throws IOException {

		screenManager.openNewScreen("recepcionista/FormularioCadastroRecepcionista", "Cadastro Recepcionista");
	}

	@FXML
	void openScreenConsultaRecepcionista(ActionEvent event) throws IOException {

		screenManager.openNewScreen("consulta/ConsultaRecepcionistaScreen", "Consulta");
	}

	@FXML
	void openScreenEspecialidades(ActionEvent event) throws IOException {

		screenManager.openNewScreen("especialidade/EspecialidadeScreen", "Especialidade");
	}

	@FXML
	void openScreenMedicosCadastrados(ActionEvent event) throws IOException {

		screenManager.openNewScreen("medico/MedicosRecepcionistaScreen", "Médicos");
	}

	@FXML
	void openScreenPacientesCadastrados(ActionEvent event) throws IOException {

		screenManager.openNewScreen("paciente/PacientesRecepcionistaScreen", "Pacientes");
	}

	@FXML
	void sair(ActionEvent event) {

		ScreenManager.closeScreen(btnSair);

	}

}
