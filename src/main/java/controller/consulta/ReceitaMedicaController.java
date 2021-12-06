package controller.consulta;

import dao.MedicoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import login.Login;
import message.MessageAlert;
import model.Medico;
import screenManager.ScreenManager;

public class ReceitaMedicaController {

	@FXML
	private Button btnImprimir;

	@FXML
	private Button btnVoltar;
	@FXML
	private TextArea txtDescricao;

	private Medico medicoLogado = MedicoDAO.findByCPF(Login.getCPF_userLogged());

	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	@FXML
	void imprirmirReceita(ActionEvent event) {

		if (txtDescricao.getText().equals(""))

			new MessageAlert().showMessage("Por favor Preencha a descrição primeiro", AlertType.INFORMATION);

		else {

			txtDescricao.setText(txtDescricao.getText() + "Dr(a): " + medicoLogado.getNome());

			new MessageAlert().showMessage(txtDescricao.getText(), AlertType.INFORMATION);
		}

	}
}
