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

package controller.paciente;

import dao.PacienteDAO;
import factory.UserFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import message.MessageAlert;
import screenManager.ScreenManager;

/**
 * Controller do cadastro de pacientes
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class CadastroPacienteController {

	@FXML
	private TextField txtNome;

	@FXML
	private DatePicker txtDataNascimento;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Text txtMessageCPF_Cadastrado;

	@FXML
	private TextField txtCPF;

	private MessageAlert msgAlert = new MessageAlert();

	@FXML
	private Button btnVoltar;

	/**
	 * Evento que fecha a tela
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	/**
	 * Metodo de cadastro de paciente
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void cadastrarPaciente(ActionEvent event) {

		txtMessageCPF_Cadastrado.setText("");

		if (isAnyCampoEmBranco()) {

			msgAlert.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}

		else {

			if (isCpfAlreadyRegistered()) {

				txtMessageCPF_Cadastrado.setText("CPF Já cadastrado.");
			}

			else {

				UserFactory.createPaciente(
						txtNome.getText(), 
						txtCPF.getText(),
						txtDataNascimento.getEditor().getText());

				msgAlert.showMessage("Cadastro Realizado com sucesso", AlertType.INFORMATION);

				closeScreen();
			}
		}

	}

	/**
	 * Verifica se o paciente já está cadastrado
	 * @return boolean
	 */
	private boolean isCpfAlreadyRegistered() {

		return PacienteDAO.cpfAlreadyRegistered(txtCPF.getText());
	}

	/**
	 * Metodo que fecha a tela
	 * @param event
	 * @throws IOException
	 */
	private void closeScreen() {

		ScreenManager.closeScreen(btnVoltar);

	}

	/**
	 * Metodo que verifica se possui algum campo em branco
	 * @return boolean
	 */
	private boolean isAnyCampoEmBranco() {

		boolean anyCampoEmBranco = false;

		if (txtCPF.getText().equals("") || txtNome.getText().equals(" ")
				|| txtDataNascimento.getEditor().getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;
	}
}
