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

import java.net.URL;
import java.util.ResourceBundle;

import dao.PacienteDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import message.MessageAlert;
import model.Paciente;
import screenManager.ScreenManager;

/**
 * Controller da edição de paciente
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
/**
 * @author diego
 *
 */
public class FormularioPacienteEditController implements Initializable {

	@FXML
	private TextField txtNome;

	@FXML
	private DatePicker txtDataNascimento;

	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnCancelar;

	private MessageAlert msgAlert = new MessageAlert();

	private Paciente pacienteSelecionado = PacienteRecepcionistaController.getPacienteSelecionado();

	/**
	 * Metodo de salvar as alterações nos dados de um paciente
	 */
	public void salvarPacienteEdit() {

		if (isAnyCampoEmBranco()) {

			msgAlert.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}

		else {

			updatePaciente();

			msgAlert.showMessage("Edição Realizado com sucesso", AlertType.INFORMATION);

			closeScreen();
		}
	}

	/**
	 * Metodo que verifica se possui algum campo em branco
	 * @return boolean
	 */
	private boolean isAnyCampoEmBranco() {

		boolean anyCampoEmBranco = false;

		if (txtNome.getText().equals(" ") || txtDataNascimento.getEditor().getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;
	}

	/**
	 * Metodo de atualizar os dados do paciente
	 */
	private void updatePaciente() {

		String name = txtNome.getText();
		String dataNascimento = txtDataNascimento.getEditor().getText();

		pacienteSelecionado.setNome(name);
		pacienteSelecionado.setDATA_NASCIMENTO(dataNascimento);

		PacienteDAO.updatePatient(pacienteSelecionado);

	}

	/**
	 * Metodo de fechar a tela
	 */
	public void closeScreen() {

		ScreenManager.closeScreen(btnCancelar);
	}

	/**
	 * Metodo que adiciona um evento para os botões: btnSalvar e btnCancelar;
	 * @param listener
	 */
	public void addButtonsListener(EventHandler<ActionEvent> listener) {

		btnSalvar.setOnAction(listener);
		btnCancelar.setOnAction(listener);

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

	/**
	 * Metodo que inicializa o formulário com os dados a serem alterados de um paciente
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadInfoPaciente();

	}

	
	/**
	 * Metodo que carrega os dados do paciente
	 */
	private void loadInfoPaciente() {

		txtNome.setText(pacienteSelecionado.getNome());

	}

}
