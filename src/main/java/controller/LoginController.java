package controller;

import java.io.IOException;

import instanceType.InstanceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.Login;
import message.MessageAlert;
import resultLoginTry.ResultLoginTry;
import screenManager.ScreenManager;

public class LoginController {

	@FXML
	private TextField txtCPF;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnEntrar;
	@FXML
	private Text txtMessageCPF;
	@FXML
	private Text txtMessageSenha;
	
	

	@FXML
	void makeLogin(ActionEvent event) throws IOException {

		txtMessageCPF.setText("");
		txtMessageSenha.setText("");

		String CPF_User = txtCPF.getText();
		String passwordUser = txtSenha.getText();

		ResultLoginTry resultLoginTryLogin = Login.makeLogin(CPF_User, passwordUser);

		if (resultLoginTryLogin.getValue().equals("SUCCESS")) {

			closeScreen();

			openSpecificScrenLoginUser();
		}

		else if (resultLoginTryLogin.getValue().equals("FAIL_CPF")) {
			
			txtMessageCPF.setText("CPF Inválido.");
		}

		else
			txtMessageSenha.setText("Senha incorreta.");;

	}

	private void closeScreen() {

		Stage stage = (Stage) btnEntrar.getScene().getWindow();

		stage.close();

	}

	private void openSpecificScrenLoginUser() throws IOException {
		
		InstanceType instanceTypeRecepcionista = InstanceType.RECEPCIONISTA;
		
		ScreenManager screenManager = new ScreenManager();
		
		if(Login.getInstanceCpfWasRegistered().equals(instanceTypeRecepcionista)) {
			
			screenManager.openNewScreen("RecepcionistaScreen", "Recepcionista");
		}
		
		else 	
			screenManager.openNewScreen("MedicoScreen", "Recepcionista");

	}

}
