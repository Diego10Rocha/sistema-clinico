package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import screenManager.ScreenManager;

public class RecepcionistaController {

	@FXML
	private Menu menuCadastro;

	@FXML
	private Menu menuPacientes;

	@FXML
	private MenuItem menuItemVerPacientes;

	@FXML
	private MenuItem menuItemCadastroRecepcionista;

	@FXML
	private MenuItem menuItemCadastroMedico;

	@FXML
	private MenuItem menuItemCadastroPaciente;

	@FXML
	private MenuItem menuItemAgendaConsultas;

	@FXML
	private MenuItem menuItemVerMedicos;
	@FXML
	private MenuItem menuItemVerEspecialidades;

	@FXML
	private Menu menuConsulta;

	@FXML
	private Menu menuMedicos;

	@FXML
	private Menu menuEspecialidades;

	@FXML
	private Menu menuSair;

	private ScreenManager screenManager = new ScreenManager();

	@FXML
	void openScreenAddConsulta(ActionEvent event) {

	}

	@FXML
	void openScreenCadastroMedico(ActionEvent event) throws IOException {

		screenManager.openNewScreen("FormularioCadastroMedico", "Cadastro Medico");
	}

	@FXML
	void openScreenCadastroPaciente(ActionEvent event) throws IOException {

		screenManager.openNewScreen("FormularioCadastroPaciente", "Cadastro Paciente");
	}

	@FXML
	void openScreenCadastroRecepcionista(ActionEvent event) throws IOException {

		screenManager.openNewScreen("FormularioCadastroRecepcionista", "Cadastro Recepcionista");
	}

	@FXML
	void openScreenConsultaRecepcionista(ActionEvent event) throws IOException {

		screenManager.openNewScreen("ConsultaRecepcionistaScreen", "Consulta");
	}

	@FXML
	void openScreenEspecialidades(ActionEvent event) throws IOException {

		screenManager.openNewScreen("EspecialidadeScreen", "Especialidade");
	}

	@FXML
	void openScreenMedicosCadastrados(ActionEvent event) throws IOException {

		screenManager.openNewScreen("MedicosRecepcionistaScreen", "Médicos");
	}

	@FXML
	void openScreenPacientesCadastrados(ActionEvent event) throws IOException {

		screenManager.openNewScreen("PacientesRecepcionistaScreen", "Pacientes");
	}

	@FXML
	void sair(ActionEvent event) {

		System.out.println("a");

	}

}
