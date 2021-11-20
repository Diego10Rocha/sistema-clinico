package controller;

import dao.PacienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import message.MessageAlert;
import model.Paciente;

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
	void cadastrarPaciente(ActionEvent event) {

		txtMessageCPF_Cadastrado.setText("");

		boolean isAnyCampoEmBranco = isAnyCampoEmBranco();

		if (isAnyCampoEmBranco) {

			msgAlert.getMessageCadastroFailCampoEmBranco();
		}

		else {

			String CPF = txtCPF.getText();

			boolean isCpfAlreadyRegistered = PacienteDAO.cpfAlreadyRegistered(CPF);

			if (isCpfAlreadyRegistered) {

				txtMessageCPF_Cadastrado.setText("CPF Já cadastrado.");
			}

			else {

				String name = txtNome.getText();
				String dataNascimento = txtDataNascimento.getEditor().getText();

				Paciente newPaciente = new Paciente(name, CPF, dataNascimento);

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

		if (txtCPF.getText().equals("") || txtNome.getText().equals(" ")
				|| txtDataNascimento.getEditor().getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;
	}
}