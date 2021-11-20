package controller;

import dao.MedicoDAO;
import dao.RecepcionistaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import message.MessageAlert;
import model.Recepcionista;

public class CadastroMedicoController {

	@FXML
	private TextField txtNome;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Text txtMessageCPF_Cadastrado;

	@FXML
	private TextField txtCPF;

	@FXML
	private TextField txtCRM;

	private MessageAlert msgAlert = new MessageAlert();

	@FXML
	void cadastrarmMedico(ActionEvent event) {

		txtMessageCPF_Cadastrado.setText("");

		boolean isAnyCampoEmBranco = isAnyCampoEmBranco();

		if (isAnyCampoEmBranco) {

			msgAlert.getMessageCadastroFailCampoEmBranco();
		}

		else {

			String CPF = txtCPF.getText();

			boolean isCpfAlreadyRegistered = MedicoDAO.cpfAlreadyRegistered(CPF);

			if (isCpfAlreadyRegistered) {

				txtMessageCPF_Cadastrado.setText("CPF Já cadastrado.");
			}

			else {

				String name = txtNome.getText();
				String password = txtSenha.getText();
				String CRM = txtCRM.getText();

				Medico newRecepcionista = new Recepcionista(name, CPF, password);

				msgAlert.getMessageCadastroSuccess();

				closeScreen();
			}
		}
	}

	private void closeScreen() {

		Stage stage = (Stage) btnCadastrar.getScene().getWindow();

		stage.close();

	}

	private boolean isAnyCampoEmBranco() {

		boolean anyCampoEmBranco = false;

		if (txtCPF.getText().equals("") || txtNome.getText().equals(" ") || txtSenha.getText().equals("")
				|| txtCRM.getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;
	}
}
