package controller.recepcionista;

import Factory.UserFactory;
import dao.RecepcionistaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import message.MessageAlert;
import screenManager.ScreenManager;

public class CadastroRecepcionistaController {

	@FXML
	private TextField txtCPF;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnCadastrar;

	@FXML
	private TextField txtNome;

	@FXML
	private Text txtMessageCPF_Cadastrado;

	private MessageAlert msgAlert = new MessageAlert();

	@FXML
	private Button btnVoltar;

	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	@FXML
	void cadastrarRecepcionista(ActionEvent event) {

		txtMessageCPF_Cadastrado.setText("");

		boolean isAnyCampoEmBranco = isAnyCampoEmBranco();

		if (isAnyCampoEmBranco) {

			msgAlert.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}

		else {

			String CPF = txtCPF.getText();

			boolean isCpfAlreadyRegistered = RecepcionistaDAO.cpfAlreadyRegistered(CPF);

			if (isCpfAlreadyRegistered) {

				txtMessageCPF_Cadastrado.setText("CPF Já cadastrado.");
			}

			else {

				UserFactory.createRecepcionista(txtNome.getText(), CPF, txtSenha.getText());

				msgAlert.showMessage("Cadastro Realizado com sucesso", AlertType.INFORMATION);

				closeScreen();
			}
		}

	}

	private void closeScreen() {

		ScreenManager.closeScreen(btnVoltar);

	}

	private boolean isAnyCampoEmBranco() {

		boolean anyCampoEmBranco = false;

		if (txtCPF.getText().equals("") || txtNome.getText().equals(" ") || txtSenha.getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;
	}

}
