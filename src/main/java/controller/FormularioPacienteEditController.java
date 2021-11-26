package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.PacienteDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import message.MessageAlert;
import model.Paciente;

public class FormularioPacienteEditController implements Initializable {

	@FXML
	private TextField txtNome;

	@FXML
	private DatePicker txtDataNascimento;

	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnCancelar;

	private MessageAlert msgAlert = new MessageAlert();

	private Paciente pacienteSelecionado = PacienteRecepcionistaController.getPacienteSelecionado();

	public void salvarPacienteEdit() {

		boolean isAnyCampoEmBranco = isAnyCampoEmBranco();

		if (isAnyCampoEmBranco) {

			msgAlert.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}

		else {

			updatePaciente();

			msgAlert.showMessage("Edição Realizado com sucesso", AlertType.INFORMATION);

			closeScreen();
		}
	}

	private void updatePaciente() {

		String name = txtNome.getText();
		String dataNascimento = txtDataNascimento.getEditor().getText();

		pacienteSelecionado.setNome(name);
		pacienteSelecionado.setDATA_NASCIMENTO(dataNascimento);

		PacienteDAO.updatePatient(pacienteSelecionado);

	}

	public void closeScreen() {

		Stage stage = (Stage) btnSalvar.getScene().getWindow();

		stage.close();
	}

	private boolean isAnyCampoEmBranco() {

		boolean anyCampoEmBranco = false;

		if (txtNome.getText().equals(" ") || txtDataNascimento.getEditor().getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;
	}

	public void addButtonsListener(EventHandler<ActionEvent> listener) {

		btnSalvar.setOnAction(listener);
		btnCancelar.setOnAction(listener);

	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadInfoPaciente();

	}

	private void loadInfoPaciente() {

		txtNome.setText(pacienteSelecionado.getNome());

	}

}
