package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.Login;
import message.MessageAlert;
import resultLoginTry.ResultLoginTry;

public class LoginController {

	@FXML
	private TextField txtCPF;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnEntrar;

	private MessageAlert msgAlert = new MessageAlert();

	@FXML
	void makeLogin(ActionEvent event) {

		String CPF_User = txtCPF.getText();
		String passwordUser = txtSenha.getText();

		ResultLoginTry resultLoginTryLogin = Login.makeLogin(CPF_User, passwordUser);

		if (resultLoginTryLogin.getValue().equals("SUCCESS")) {

			closeScreen();

			openSpecificScrenLoginUser();
		}

		else if (resultLoginTryLogin.getValue().equals("FAIL_CPF")) {

			msgAlert.getMessageFailLoginCPF();
		}

		else
			msgAlert.getMessageFailLoginPassword();

	}

	private void closeScreen() {

		Stage stage = (Stage) btnEntrar.getScene().getWindow();

		stage.close();

	}

	private void openSpecificScrenLoginUser() {
		// TODO Auto-generated method stub

	}

}
