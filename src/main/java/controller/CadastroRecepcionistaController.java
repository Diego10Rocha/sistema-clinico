package controller;

import dao.RecepcionistaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import message.MessageAlert;
import model.Recepcionista;

public class CadastroRecepcionistaController {

	@FXML
	private TextField txtCPF;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnCadastrar;

	@FXML
	private TextField txtNome;
	
	@FXML
    private Text txtMessageCPF_Cadastrado;

	private MessageAlert msgAlert = new MessageAlert();

	@FXML
	void cadastrarRecepcionista(ActionEvent event) {
		
		txtMessageCPF_Cadastrado.setText("");
		
		boolean isAnyCampoEmBranco = isAnyCampoEmBranco();

		if (isAnyCampoEmBranco) {

			msgAlert.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}

		else {
			
			String CPF = txtCPF.getText();

			boolean isCpfAlreadyRegistered = RecepcionistaDAO.cpfAlreadyRegistered(CPF);

			if (isCpfAlreadyRegistered) {

				txtMessageCPF_Cadastrado.setText("CPF Já cadastrado.");
			}

			else {

				String name = txtNome.getText();
				String password = txtSenha.getText();

				Recepcionista newRecepcionista = new Recepcionista(name, CPF, password);
				
				msgAlert.showMessage("Cadastro Realizado com sucesso", AlertType.INFORMATION);
				
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

		if (txtCPF.getText().equals("") || txtNome.getText().equals(" ") || txtSenha.getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;
	}

}
