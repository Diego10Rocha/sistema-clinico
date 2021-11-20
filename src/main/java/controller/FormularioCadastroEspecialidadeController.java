package controller;

import dao.EspecialidadeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

	@FXML
	void cadastrarEspecialidade(ActionEvent event) {

		txtMessageEspecialidade_Cadastrada.setText("");

		if (txtNome.getText() != "") {

			if (EspecialidadeDAO.insertSpecialty(new Especialidade(txtNome.getText(), false))) {

				msg.getMessageCadastroSuccess();

			}

			else {

				txtMessageEspecialidade_Cadastrada.setText("Especialidade Já Cadastrada.");
			}
		}

		else {

			msg.getMessageCadastroFailCampoEmBranco();
		}
	}

	@FXML
	void closeScreen(ActionEvent event) {

		Stage stage = (Stage) btnCadastrar.getScene().getWindow();

		stage.close();
	}
}
