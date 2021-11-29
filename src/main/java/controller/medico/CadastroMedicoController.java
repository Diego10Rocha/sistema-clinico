package controller.medico;

import dao.MedicoDAO;
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

	@FXML
	private TextField txtEspecialidade;

	@FXML
	private TextField txtSubEspecialidade;

	@FXML
	private TextField txtHoraConsulta;

	private MessageAlert msgAlert = new MessageAlert();

	@FXML
	private Button btnVoltar;

	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	@FXML
	void cadastrarMedico(ActionEvent event) {

		txtMessageCPF_Cadastrado.setText("");

		if (isAnyObrigatorioCampoEmBranco())

			msgAlert.showMessage("Por Favor preencha todos os campos obrigatórios!", AlertType.WARNING);

		else if (isCpfAlreadyRegistered())

			txtMessageCPF_Cadastrado.setText("CPF Já cadastrado.");

		else {

			boolean notSubEspecialidadeAdd = txtSubEspecialidade.getText().equals("");

			if (notSubEspecialidadeAdd)

				createMedicoSemSubEspecialidade();

			else

				createMedicoComSubEspecialidade();

			msgAlert.showMessage("Cadastro Realizado com sucesso", AlertType.INFORMATION);

			closeScreen();
		}
	}

	private boolean isAnyObrigatorioCampoEmBranco() {

		boolean anyObrigatorioCampoEmBranco = false;

		if (txtCPF.getText().equals("") || txtNome.getText().equals(" ") || txtSenha.getText().equals("")
				|| txtCRM.getText().equals("") || txtEspecialidade.getText().equals("")
				|| txtHoraConsulta.getText().equals("")) {

			anyObrigatorioCampoEmBranco = true;
		}

		return anyObrigatorioCampoEmBranco;
	}

	private boolean isCpfAlreadyRegistered() {

		return MedicoDAO.cpfAlreadyRegistered(txtCPF.getText());
	}

	private void createMedicoSemSubEspecialidade() {

		UserFactory.createMedico(txtNome.getText(), txtSenha.getText(), txtCPF.getText(), txtCRM.getText(),
				txtEspecialidade.getText(), txtHoraConsulta.getText());

	}

	private void createMedicoComSubEspecialidade() {

		UserFactory.createMedico(txtNome.getText(), txtSenha.getText(), txtCPF.getText(), txtCRM.getText(),
				txtEspecialidade.getText(), txtSubEspecialidade.getText(), txtHoraConsulta.getText());

	}

	private void closeScreen() {

		ScreenManager.closeScreen(btnVoltar);

	}

}
