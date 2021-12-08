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

/**
 * Controller de edição de especialidade
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
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

	/**
	 * Metodo que salva alterações na especialidade
	 */
	public void salvarEspecialidadeEditada() {

		if (txtNome.getText() != "") {

			if (EspecialidadeDAO.specialtyAlreadyRegistered(txtNome.getText())) {

				txtMessageEspecialidade_Cadastrada.setText("Especialidade Já Cadastrada.");
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

	/**
	 * Metodo que fecha a tela
	 */
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

	/**
	 * Metodo que inicializa o formulário de edição com os dados a serem alterados
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		txtNome.setText(especialidadeSelecionada.getNome());

	}

	/**
	 * Metodo que adiciona eventos aos botões: btnSalvar e btnCancelar
	 * @param listener
	 */
	public void addButtonsListener(EventHandler<ActionEvent> listener) {

		btnSalvar.setOnAction(listener);
		btnCancelar.setOnAction(listener);

	}

}
