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

package controller.especialidade;

import dao.EspecialidadeDAO;
import factory.EspecialidadeFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import message.MessageAlert;
import screenManager.ScreenManager;

/**
 * Controller do cadastro de especialidade
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class FormularioCadastroEspecialidadeController {

	@FXML
	private TextField txtNome;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Text txtMessageEspecialidade_Cadastrada;

	private MessageAlert msg = new MessageAlert();

	/**
	 * Metodo que cadastra a especialidade
	 */
	public void cadastrarEspecialidade() {

		txtMessageEspecialidade_Cadastrada.setText("");

		if (txtNome.getText() != "") {

			if (EspecialidadeDAO.specialtyAlreadyRegistered(txtNome.getText())) {

				txtMessageEspecialidade_Cadastrada.setText("Especialidade Já Cadastrada.");

			}

			else {

				EspecialidadeFactory.createEspecialidade(txtNome.getText(), false);

				msg.showMessage("Cadastro Realizado com sucesso", AlertType.INFORMATION);

				closeScreen();

			}
		}

		else {

			msg.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}
	}

	/**
	 * Metodo de fechar a tela
	 */
	public void closeScreen() {

		ScreenManager.closeScreen(btnCancelar);
	}

	/**
	 * Metodo que "ouve" os eventos dos botões: btnCadastrar e btnCancelar
	 * @param listener
	 */
	public void addButtonsListener(EventHandler<ActionEvent> listener) {

		btnCadastrar.setOnAction(listener);
		btnCancelar.setOnAction(listener);

	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public Button getBtnCadastrar() {
		return btnCadastrar;
	}

	public void setBtnCadastrar(Button btnCadastrar) {
		this.btnCadastrar = btnCadastrar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

}
