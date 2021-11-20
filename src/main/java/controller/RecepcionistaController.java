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
	private MenuItem menuItemCadastroRecepcionista;

	@FXML
	private MenuItem menuItemCadastroMedico;

	@FXML
	private MenuItem menuItemCadastroPaciente;

	@FXML
	private Menu menuConsulta;

	@FXML
	private MenuItem menuItemAddConsulta;

	@FXML
	private MenuItem menuItemVerConsultas;

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
	void openScreenCadastroMedico(ActionEvent event) {

	}

	@FXML
	void openScreenCadastroPaciente(ActionEvent event) {

	}

	@FXML
	void openScreenCadastroRecepcionista(ActionEvent event) throws IOException {
		
		screenManager.openNewScreen("FormularioCadastroRecepcionista", "Cadastro Recepcionista");
	}

	@FXML
	void openScreenConsultas(ActionEvent event) {

	}

	@FXML
	void openScreenEspecialidades(ActionEvent event) {

	}

	@FXML
	void openScreenMedicosCadastrados(ActionEvent event) {

	}

	@FXML
	void sair(ActionEvent event) {

	}

}
