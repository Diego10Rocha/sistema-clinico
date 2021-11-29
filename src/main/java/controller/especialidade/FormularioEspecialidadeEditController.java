package controller.especialidade;

import java.net.URL;
import java.util.ResourceBundle;

import dao.EspecialidadeDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import message.MessageAlert;
import model.Especialidade;
import screenManager.ScreenManager;

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

				txtMessageEspecialidade_Cadastrada.setText("Especialidade JÃ¡ Cadastrada.");
			}

			else {

				especialidadeSelecionada.setNome(txtNome.getText());

				EspecialidadeDAO.updateSpecialty(especialidadeSelecionada);

				closeScreen();

			}
		}

		else {

			msg.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}
	}

	public void closeScreen() {

		ScreenManager.closeScreen(btnCancelar);
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
