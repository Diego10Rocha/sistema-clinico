package controller;

import dao.RecepcionistaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import message.MessageAlert;
import model.Recepcionista;

public class CadastroRecepcionistaController {

	@FXML
	private TextField txtCPF;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnEntrar;

	@FXML
	private TextField txtNome;

	private MessageAlert msgAlert = new MessageAlert();

	@FXML
	void cadastrarRecepcionista(ActionEvent event) {

		boolean isAnyCampoEmBranco = isAnyCampoEmBranco();

		if (isAnyCampoEmBranco) {

			msgAlert.getMessageCadastroRecepcionistaFailCampoEmBranco();
		}

		else {
			
			String CPF = txtCPF.getText();

			boolean isCpfAlreadyRegistered = RecepcionistaDAO.cpfAlreadyRegistered(CPF);

			if (isCpfAlreadyRegistered) {

				msgAlert.getMessageCadastroRecepcionistaFailCpfAlreadyRegistered();
			}

			else {

				String name = txtNome.getText();
				String password = txtSenha.getText();

				Recepcionista newRecepcionista = new Recepcionista(name, CPF, password);
				
				msgAlert.getMessageCadastroRecepcionistaSucces();
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

}
