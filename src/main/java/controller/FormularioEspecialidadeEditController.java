package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.EspecialidadeDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import message.MessageAlert;
import model.Especialidade;

public class FormularioEspecialidadeEditController implements Initializable {

	@FXML
	private TextField txtNome;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	private MessageAlert msg = new MessageAlert();
	
	@FXML
	private Text txtMessageEspecialidade_Cadastrada;

	private Especialidade especialidadeSelecionada = EspecialidadeController.getEspecialidadeSelecionada();

	public void salvarEspecialidadeEditada() {
		
		if (txtNome.getText() != "") {

			if (EspecialidadeDAO.specialtyAlreadyRegistered(txtNome.getText())) {

				txtMessageEspecialidade_Cadastrada.setText("Especialidade Já Cadastrada.");
			}

			else {
				

				Especialidade newEspecialidade = new Especialidade();
				
				newEspecialidade.setNome(txtNome.getText());
				newEspecialidade.setPrincipal(false);
				
				EspecialidadeDAO.updateSpecialty(newEspecialidade, especialidadeSelecionada);

				closeScreen();

			}
		}

		else {

			msg.getMessageCadastroFailCampoEmBranco();
		}
	}

	public void closeScreen() {

		Stage stage = (Stage) btnCancelar.getScene().getWindow();

		stage.close();
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

		txtNome.setText(especialidadeSelecionada.getNome());

	}

	public void addButtonsListener(EventHandler<ActionEvent> listener) {

		btnSalvar.setOnAction(listener);
		btnCancelar.setOnAction(listener);

	}

}
