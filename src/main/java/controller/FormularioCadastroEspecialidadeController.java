package controller;

import dao.EspecialidadeDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import message.MessageAlert;
import model.Especialidade;

public class FormularioCadastroEspecialidadeController {

	@FXML
	private TextField txtNome;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Text txtMessageEspecialidade_Cadastrada;

	private MessageAlert msg = new MessageAlert();

	public void cadastrarEspecialidade() {

		txtMessageEspecialidade_Cadastrada.setText("");

		if (txtNome.getText() != "") {

			if (EspecialidadeDAO.specialtyAlreadyRegistered(txtNome.getText())) {

				txtMessageEspecialidade_Cadastrada.setText("Especialidade Já Cadastrada.");

			}

			else {

				Especialidade newEspecialidade = new Especialidade(txtNome.getText(), false);

				msg.showMessage("Cadastro Realizado com sucesso", AlertType.INFORMATION);

				closeScreen();

			}
		}

		else {

			msg.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}
	}

	public void closeScreen() {

		Stage stage = (Stage) btnCadastrar.getScene().getWindow();

		stage.close();
	}

	public void addButtonsListener(EventHandler<ActionEvent> listener) {

		btnCadastrar.setOnAction(listener);
		btnCancelar.setOnAction(listener);

	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public Button getBtnCadastrar() {
		return btnCadastrar;
	}

	public void setBtnCadastrar(Button btnCadastrar) {
		this.btnCadastrar = btnCadastrar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

}
