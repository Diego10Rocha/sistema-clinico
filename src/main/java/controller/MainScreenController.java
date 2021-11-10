package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MainScreenController implements Initializable {

	@FXML
	private Menu menuArquivo;

	@FXML
	private MenuItem menuItemAutoAtendimento;

	@FXML
	private MenuItem menuItemLogin;

	@FXML
	private MenuItem menuItemCadastroRecepcionista;

	@FXML
	private Menu menuSobre;

	@FXML
	private MenuItem menuItemSobre;

	@FXML
	void openFormularioScreenCadastroRecepcionista(ActionEvent event) {

	}

	@FXML
	void openScreenAutoAtendimento(ActionEvent event) {

	}

	@FXML
	void openScreenLogin(ActionEvent event) {

	}

	@FXML
	void showSobreSistema(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
