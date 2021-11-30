package controller.recepcionista;

import dao.RecepcionistaDAO;
import factory.UserFactory;
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

		if (isAnyCampoEmBranco()) {

			msgAlert.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}

		else {

			if (isCpfAlreadyRegistered()) {

				txtMessageCPF_Cadastrado.setText("CPF JÃ¡ cadastrado.");
			}

			else {

				UserFactory.createRecepcionista(txtNome.getText(), txtCPF.getText(), txtSenha.getText());

				msgAlert.showMessage("Cadastro Realizado com sucesso", AlertType.INFORMATION);

				closeScreen();
			}
		}

	}

	private boolean isAnyCampoEmBranco() {

		boolean anyCampoEmBranco = false;

		if (txtCPF.getText().equals("") || txtNome.getText().equals(" ") || txtSenha.getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;
	}

	private boolean isCpfAlreadyRegistered() {

		return RecepcionistaDAO.cpfAlreadyRegistered(txtCPF.getText());
	}

	private void closeScreen() {

		ScreenManager.closeScreen(btnVoltar);

	}

}
