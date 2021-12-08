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

package controller.recepcionista;

import dao.MedicoDAO;
import dao.RecepcionistaDAO;
import factory.UserFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import message.MessageAlert;
import screenManager.ScreenManager;

/**
 * Controller do cadastro de recepcionista
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
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
	private Button btnVoltar;

	/**
	 * Metodo de fechar a tela
	 * @param event
	 */
	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	/**
	 * Metodo para cadastrar recepcionista
	 * @param event
	 */
	@FXML
	void cadastrarRecepcionista(ActionEvent event) {

		txtMessageCPF_Cadastrado.setText("");

		if (isAnyCampoEmBranco()) {

			msgAlert.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}

		else {

			if (isCpfAlreadyRegistered()) {

				txtMessageCPF_Cadastrado.setText("CPF Já cadastrado.");
			}

			else {

				UserFactory.createRecepcionista(txtNome.getText(), txtCPF.getText(), txtSenha.getText());

				msgAlert.showMessage("Cadastro Realizado com sucesso", AlertType.INFORMATION);

				closeScreen();
			}
		}

	}

	/**
	 * Metodo que verifica se possui algum campo em branco
	 * @return boolean
	 */
	private boolean isAnyCampoEmBranco() {

		boolean anyCampoEmBranco = false;

		if (txtCPF.getText().equals("") || txtNome.getText().equals(" ") || txtSenha.getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;
	}

	/**
	 * Metodo que verifica se um CPF já está registrado como recepcionista ou médico
	 * @return boolean
	 */
	private boolean isCpfAlreadyRegistered() {

		return RecepcionistaDAO.cpfAlreadyRegistered(txtCPF.getText()) 
				|| MedicoDAO.cpfAlreadyRegistered(txtCPF.getText());
	}

	private void closeScreen() {

		ScreenManager.closeScreen(btnVoltar);

	}

}
