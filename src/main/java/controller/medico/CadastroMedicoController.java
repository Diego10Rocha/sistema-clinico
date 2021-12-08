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

package controller.medico;

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
 * Controller do cadastro de medico
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class CadastroMedicoController {

	@FXML
	private TextField txtNome;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Text txtMessageCPF_Cadastrado;

	@FXML
	private TextField txtCPF;

	@FXML
	private TextField txtCRM;

	@FXML
	private TextField txtEspecialidade;

	@FXML
	private TextField txtSubEspecialidade;

	@FXML
	private TextField txtHoraConsulta;

	private MessageAlert msgAlert = new MessageAlert();

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
	 * Evento que cadastra um médico
	 * @param event
	 */
	@FXML
	void cadastrarMedico(ActionEvent event) {

		txtMessageCPF_Cadastrado.setText("");

		if (isAnyObrigatorioCampoEmBranco())

			msgAlert.showMessage("Por Favor preencha todos os campos obrigatórios!", AlertType.WARNING);

		else if (isCpfAlreadyRegistered())

			txtMessageCPF_Cadastrado.setText("CPF Já cadastrado.");

		else {

			boolean notSubEspecialidadeAdd = txtSubEspecialidade.getText().equals("");

			if (notSubEspecialidadeAdd)

				createMedicoSemSubEspecialidade();

			else

				createMedicoComSubEspecialidade();

			msgAlert.showMessage("Cadastro Realizado com sucesso", AlertType.INFORMATION);

			closeScreen();
		}
	}

	/**
	 * Metodo que verifica se um campo obrigatorio não foi preenchido
	 * @return boolean
	 */
	private boolean isAnyObrigatorioCampoEmBranco() {

		boolean anyObrigatorioCampoEmBranco = false;

		if (txtCPF.getText().equals("") || txtNome.getText().equals(" ") || txtSenha.getText().equals("")
				|| txtCRM.getText().equals("") || txtEspecialidade.getText().equals("")
				|| txtHoraConsulta.getText().equals("")) {

			anyObrigatorioCampoEmBranco = true;
		}

		return anyObrigatorioCampoEmBranco;
	}

	/**
	 * Metodo que verifica se o CPF já está registrado
	 * @return boolean
	 */
	private boolean isCpfAlreadyRegistered() {

		return MedicoDAO.cpfAlreadyRegistered(txtCPF.getText()) 
				|| RecepcionistaDAO.cpfAlreadyRegistered(txtCPF.getText());
	}

	/**
	 * Metodo que cadastra um medico que não possui subespecialidade
	 */
	private void createMedicoSemSubEspecialidade() {

		UserFactory.createMedico(txtNome.getText(), txtSenha.getText(), txtCPF.getText(), txtCRM.getText(),
				txtEspecialidade.getText(), txtHoraConsulta.getText());

	}

	/**
	 * Metodo que cadastra um medico que possui uma subespecialidade
	 */
	private void createMedicoComSubEspecialidade() {

		UserFactory.createMedico(txtNome.getText(), txtSenha.getText(), txtCPF.getText(), txtCRM.getText(),
				txtEspecialidade.getText(), txtSubEspecialidade.getText(), txtHoraConsulta.getText());

	}

	/**
	 * Metodo que fecha a tela
	 */
	private void closeScreen() {

		ScreenManager.closeScreen(btnVoltar);

	}

}
