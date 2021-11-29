package controller.paciente;

import dao.PacienteDAO;
import factory.UserFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import message.MessageAlert;
import screenManager.ScreenManager;

public class CadastroPacienteController {

	@FXML
	private TextField txtNome;

	@FXML
	private DatePicker txtDataNascimento;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Text txtMessageCPF_Cadastrado;

	@FXML
	private TextField txtCPF;

	private MessageAlert msgAlert = new MessageAlert();

	@FXML
	private Button btnVoltar;

	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	@FXML
	void cadastrarPaciente(ActionEvent event) {

		txtMessageCPF_Cadastrado.setText("");

		boolean isAnyCampoEmBranco = isAnyCampoEmBranco();

		if (isAnyCampoEmBranco) {

			msgAlert.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}

		else {

			String CPF = txtCPF.getText();

			boolean isCpfAlreadyRegistered = PacienteDAO.cpfAlreadyRegistered(CPF);

			if (isCpfAlreadyRegistered) {

				txtMessageCPF_Cadastrado.setText("CPF Já cadastrado.");
			}

			else {

				UserFactory.createPaciente(txtNome.getText(), CPF, txtDataNascimento.getEditor().getText());

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

		if (txtCPF.getText().equals("") || txtNome.getText().equals(" ")
				|| txtDataNascimento.getEditor().getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;
	}
}
