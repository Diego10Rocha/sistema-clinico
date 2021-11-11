package controller;

import dao.RecepcionistaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Recepcionista;

public class CadastroRecepcionistaController {

	@FXML
	private TextField txtCPF;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnEntrar;

	@FXML
	private TextField txtNome;

	@FXML
	void cadastrarRecepcionista(ActionEvent event) {
		
		String CPF = txtCPF.getText();
		
		boolean isCpfAlreadyRegistered = RecepcionistaDAO.cpfAlreadyRegistered(CPF);
		
		if(isCpfAlreadyRegistered) {
			
			System.out.println("CPF já cadastrado");
		}
		
		else {
			
			String name = txtNome.getText();
			String password = txtSenha.getText();
			
			Recepcionista newRecepcionista = new Recepcionista(name, CPF, password);
		}
	}

}
