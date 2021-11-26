package controller.medico;

import dao.EspecialidadeDAO;
import dao.MedicoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import message.MessageAlert;
import model.Especialidade;
import model.Medico;
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

		if (isAnyCampoEmBranco()) {

			msgAlert.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}

		else {

			String CPF = txtCPF.getText();

			boolean isCpfAlreadyRegistered = MedicoDAO.cpfAlreadyRegistered(CPF);

			if (isCpfAlreadyRegistered) {

				txtMessageCPF_Cadastrado.setText("CPF Já cadastrado.");
			}

			else {

				createNewMedico();

				msgAlert.showMessage("Cadastro Realizado com sucesso", AlertType.INFORMATION);

				closeScreen();
			}
		}
	}

	private void createNewMedico() {

		String CPF = txtCPF.getText();
		String name = txtNome.getText();
		String password = txtSenha.getText();
		String CRM = txtCRM.getText();
		String especialidadeTxt = txtEspecialidade.getText();
		String subEspecialidadeTxt = txtSubEspecialidade.getText();
		String horaDisponivelConsulta = txtHoraConsulta.getText();

		createEspecialidadeIfNotExists();

		Medico newMedico = new Medico(name, password, CPF, CRM, especialidadeTxt, subEspecialidadeTxt,
				horaDisponivelConsulta);

	}

	private void createEspecialidadeIfNotExists() {

		String especialidadeTxt = txtEspecialidade.getText();
		String subEspecialidadeTxt = txtSubEspecialidade.getText();

		boolean notEspecialidadeAlreadyRegistered = !EspecialidadeDAO.specialtyAlreadyRegistered(especialidadeTxt);
		boolean notSubEspecialidadeAlreadyRegistered = !EspecialidadeDAO
				.specialtyAlreadyRegistered(subEspecialidadeTxt);

		boolean notNameSubEspecialidadeEmpty = !subEspecialidadeTxt.equals("");

		if (notEspecialidadeAlreadyRegistered) {

			Especialidade especialidade = new Especialidade(especialidadeTxt, true);
		}

		if (notSubEspecialidadeAlreadyRegistered && notNameSubEspecialidadeEmpty) {

			Especialidade subEspecialidade = new Especialidade(subEspecialidadeTxt, false);
		}

	}

	private void closeScreen() {

		ScreenManager.closeScreen(btnVoltar);

	}

	private boolean isAnyCampoEmBranco() {

		boolean anyCampoEmBranco = false;

		if (txtCPF.getText().equals("") || txtNome.getText().equals(" ") || txtSenha.getText().equals("")
				|| txtCRM.getText().equals("") || txtEspecialidade.getText().equals("")
				|| txtHoraConsulta.getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;
	}
}
