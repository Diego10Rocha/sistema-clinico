package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import screenManager.ScreenManager;

public class MainScreenController {

	@FXML
	private Button menuItemAutoAtendimento;

	@FXML
	private Button menuItemLogin;

	@FXML
	private Button menuItemCadastroRecepcionista;

	@FXML
	private Button menuItemSobre;

	private ScreenManager screenManager = new ScreenManager();

	@FXML
	private Button menuItemSair;

	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(menuItemSair);
	}

	@FXML
	void openFormularioScreenCadastroRecepcionista(ActionEvent event) throws IOException {

		screenManager.openNewScreen("recepcionista/FormularioCadastroRecepcionista", "Cadastro recepcionista");

	}

	@FXML
	void openScreenAutoAtendimento(ActionEvent event) throws IOException {

		screenManager.openNewScreen("paciente/AutoAtendimentoScreen", "Autoatendimento");
	}

	@FXML
	void openScreenLogin(ActionEvent event) throws IOException {

		screenManager.openNewScreen("login/LoginScreen", "Login", false);
	}

	@FXML
	void showSobreSistema(ActionEvent event) throws IOException {

		screenManager.openNewScreen("SobreScreen", "About");
	}

}
