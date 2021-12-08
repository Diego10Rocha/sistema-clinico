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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.EspecialidadeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import message.MessageAlert;
import model.Especialidade;
import screenManager.ScreenManager;

/**
 * Controller da tela de especialidade
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class EspecialidadeController implements Initializable, EventHandler<ActionEvent> {

	@FXML
	private ListView<Especialidade> lvEspecialidades;

	@FXML
	private Button btnAdicionarEspecialidade;

	@FXML
	private Button btnRemoverEspecialidade;

	@FXML
	private Button btnEditarEspecialidade;

	private ScreenManager screenManager = new ScreenManager();

	private FormularioCadastroEspecialidadeController formularioEspecialidade;
	private FormularioEspecialidadeEditController formularioEspecialidadeEdit;

	private static Especialidade especialidadeSelecionada;

	private MessageAlert msg = new MessageAlert();

	@FXML
	private Button btnVoltar;

	private ObservableList<Especialidade> obsEspecialidades;

	/**
	 * Metodo que carrega as especialidades
	 */
	public void loadEspecialidades() {

		List<Especialidade> especialidadesCadastradas = EspecialidadeDAO.getSpecialties();

		obsEspecialidades = FXCollections.observableArrayList(especialidadesCadastradas);

		lvEspecialidades.setItems(obsEspecialidades);
	}

	/**
	 * Evento de fechar a tela
	 * @param event
	 */
	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	/**
	 * Evento que abre tela de edição de especialidade
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenFormularioEditEspecialidade(ActionEvent event) throws IOException {

		especialidadeSelecionada = lvEspecialidades.getSelectionModel().getSelectedItem();

		if (especialidadeSelecionada == null) {

			msg.showMessage("Por Favor selecione uma Especialidade primeiro!", AlertType.WARNING);

		}

		else {

			screenManager.openNewScreen("especialidade/FormularioEspecialidadeEdit", "Edição especialidades");

			setReferenciaFormularioEspecialidadeEdit();

		}
	}

	/**
	 * Metodo que pega a referência da especialidade a ser editada
	 */
	private void setReferenciaFormularioEspecialidadeEdit() {

		Object currentController = screenManager.getCurrenController();

		formularioEspecialidadeEdit = (FormularioEspecialidadeEditController) currentController;

		formularioEspecialidadeEdit.addButtonsListener(this);

	}

	/**
	 * Evento que abre formulário de cadastro de especialidade
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenFormularioEspecialidade(ActionEvent event) throws IOException {

		screenManager.openNewScreen("especialidade/FormularioCadastroEspecialidade", "Cadastro Especialidade");

		setReferenciaFormularioCadastroEspecialidadeController();

	}

	/**
	 * Pega a referência do controller do formulário de cadastro
	 */
	private void setReferenciaFormularioCadastroEspecialidadeController() {

		Object currentController = screenManager.getCurrenController();

		formularioEspecialidade = (FormularioCadastroEspecialidadeController) currentController;

		formularioEspecialidade.addButtonsListener(this);

	}

	/**
	 * Evento que remove uma especialidade
	 * @param event
	 */
	@FXML
	void removerEspecialidade(ActionEvent event) {

		Especialidade EspecialidadeAlvo = lvEspecialidades.getSelectionModel().getSelectedItem();

		if (EspecialidadeAlvo != null) {

			try {

				EspecialidadeDAO.deleteSpecialty(EspecialidadeAlvo);

				loadEspecialidades();

			} catch (Exception e) {

				msg.showMessage("A especialidade possui médico(s) asociada!", AlertType.WARNING);

			}
		}

		else {

			msg.showMessage("Por Favor selecione uma Especialidade primeiro!", AlertType.WARNING);
		}

	}

	/**
	 * Metodo que inicializa a tela com as especialidades
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		formularioEspecialidade = new FormularioCadastroEspecialidadeController();
		formularioEspecialidadeEdit = new FormularioEspecialidadeEditController();

		loadEspecialidades();

	}

	
	/**
	 * Metodo que "ouve" os eventos dos botões do formulário de cadastro: btnCadastrar, btnCancelar;
	 * e do formulário de edição: btnSalvar e btnCancelar;
	 */
	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == formularioEspecialidade.getBtnCadastrar()) {

			formularioEspecialidade.cadastrarEspecialidade();

			loadEspecialidades();

		}

		else if (event.getSource() == formularioEspecialidade.getBtnCancelar()) {

			formularioEspecialidade.closeScreen();

		}

		else if (event.getSource() == formularioEspecialidadeEdit.getBtnSalvar()) {

			formularioEspecialidadeEdit.salvarEspecialidadeEditada();

			loadEspecialidades();

		}

		else if (event.getSource() == formularioEspecialidadeEdit.getBtnCancelar()) {

			formularioEspecialidadeEdit.closeScreen();

		}

	}

	/**
	 * Metodo que retorna a especialidade selecionada
	 * @return Especialidade
	 */
	public static Especialidade getEspecialidadeSelecionada() {

		return especialidadeSelecionada;
	}
}
