/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programação
Concluido em: 07/12/2021
Declaro que este código foi elaborado por Diego Cerqueira e Joanderson Santos em dupla e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package controller.login;

import java.io.IOException;

import instanceType.InstanceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import login.Login;
import resultLoginTry.ResultLoginTry;
import screenManager.ScreenManager;

/**
 * Controller do login
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class LoginController {

	@FXML
	private TextField txtCPF;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnEntrar;
	@FXML
	private Text txtMessageCPF;
	@FXML
	private Text txtMessageSenha;

	@FXML
	private Button btnVoltar;

	/**
	 * Evento que fecha a tela
	 * @param event
	 */
	@FXML
	void closeScreen(ActionEvent event) {
		
		ScreenManager.closeScreen(btnVoltar);
	}

	/**
	 * Metodo que faz a autenticação
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void makeLogin(ActionEvent event) throws IOException {

		txtMessageCPF.setText("");
		txtMessageSenha.setText("");

		String CPF_User = txtCPF.getText();
		String passwordUser = txtSenha.getText();

		ResultLoginTry resultLoginTryLogin = Login.makeLogin(CPF_User, passwordUser);

		if (resultLoginTryLogin.getValue().equals("SUCCESS")) {

			closeScreen();

			openSpecificScrenLoginUser();
		}

		else if (resultLoginTryLogin.getValue().equals("FAIL_CPF")) {

			txtMessageCPF.setText("CPF Inválido.");
		}

		else
			txtMessageSenha.setText("Senha incorreta.");

	}

	/**
	 * Metodo para fechar a tela
	 */
	private void closeScreen() {

		ScreenManager.closeScreen(btnVoltar);

	}

	/**
	 * Metodo que verifica qual o usuário que fez login e redireciona para a sua respectiva tela
	 * @throws IOException
	 */
	private void openSpecificScrenLoginUser() throws IOException {

		InstanceType instanceTypeRecepcionista = InstanceType.RECEPCIONISTA;

		ScreenManager screenManager = new ScreenManager();

		if (Login.getInstanceCpfWasRegistered().equals(instanceTypeRecepcionista)) {

			screenManager.openNewScreen("recepcionista/RecepcionistaScreen", "Recepcionista");
		}

		else
			screenManager.openNewScreen("medico/MedicoScreen", "Medico");

	}

}
