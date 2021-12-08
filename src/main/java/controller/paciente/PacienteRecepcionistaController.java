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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.PacienteDAO;
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
import model.GerenciadorConsulta;
import model.Paciente;
import screenManager.ScreenManager;

/**
 * Controller do cadastro de recepcionista
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class PacienteRecepcionistaController implements Initializable, EventHandler<ActionEvent> {

	@FXML
	private ListView<Paciente> lvPacientes;

	@FXML
	private Button btnRemoverPaciente;
	@FXML
	private Button btnHistoricoConsulta;

	@FXML
	private Button btnEditarPaciente;

	@FXML
	private Button btnVoltar;

	private ObservableList<Paciente> obsPacientes;

	private static Paciente pacienteSelecionado;

	private ScreenManager screenManager = new ScreenManager();

	private MessageAlert msg = new MessageAlert();

	private FormularioPacienteEditController formularioPacienteEdit;

	/**
	 * Metodo que carrega a lista de pacientes
	 */
	public void loadPacientes() {

		List<Paciente> pacientesCadastrados = PacienteDAO.getPatients();

		obsPacientes = FXCollections.observableArrayList(pacientesCadastrados);

		lvPacientes.setItems(obsPacientes);
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
	 * Evento que abre a tela de edição de pacientes
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenFormularioEditPaciente(ActionEvent event) throws IOException {

		if (isPacienteNotSelecionado()) {

			msg.showMessage("Por Favor selecione um Paciente primeiro!", AlertType.WARNING);

		}

		else {

			screenManager.openNewScreen("paciente/FormularioPacienteEdit", "Edição paciente");

			setReferenciaFormularioPacienteEdit();

		}
	}

	/**
	 * Metodo que verifica se foi selecionado um paciente antes de tentar fazer alguma alteração(Editar, Excluir)
	 * @return boolean
	 */
	private boolean isPacienteNotSelecionado() {

		pacienteSelecionado = lvPacientes.getSelectionModel().getSelectedItem();

		return pacienteSelecionado == null;
	}

	/**
	 * Metodo que guarda a referência do controlador do formulário de edição
	 */
	private void setReferenciaFormularioPacienteEdit() {

		Object currentController = screenManager.getCurrenController();

		formularioPacienteEdit = (FormularioPacienteEditController) currentController;

		formularioPacienteEdit.addButtonsListener(this);

	}

	/**
	 * Evento que deleta um paciente
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void removerPaciente(ActionEvent event) throws Exception {

		if (isPacienteNotSelecionado()) {

			msg.showMessage("Por Favor selecione um Paciente primeiro!", AlertType.WARNING);

		}

		else {

			if (GerenciadorConsulta.hasConsultaRealizada(pacienteSelecionado.getCPF())) {

				msg.showMessage("Falha! Este Paciente possui consultas realizadas no sistema.", AlertType.WARNING);

				return;
			}

			else if (GerenciadorConsulta.hasConsultaMarcada(pacienteSelecionado.getCPF())) {

				GerenciadorConsulta.removeAllConsultaContainsCPF(pacienteSelecionado.getCPF());
			}

			PacienteDAO.deletePatient(pacienteSelecionado);

			loadPacientes();
		}
	}

	/**
	 * Evento que abre a tela com o histórico de consultas de um paciente
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenHistoricoConsulta(ActionEvent event) throws IOException {

		if (isPacienteNotSelecionado()) {

			msg.showMessage("Por Favor selecione um Paciente primeiro!", AlertType.WARNING);
		}

		else

			screenManager.openNewScreen("consulta/HistoricoConsulta", "Historico Consulta");

	}

	/**
	 * Metodo que inicializa a tela com os pacientes cadastrados
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadPacientes();

	}

	/**
	 * Metodo que retorna o paciente selecionado para fazer alguma operação
	 * @return Paciente
	 */
	public static Paciente getPacienteSelecionado() {

		return pacienteSelecionado;
	}

	/**
	 * Metodo que "ouve" os eventos dos botões: btnSalvar e btnCancelar
	 */
	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == formularioPacienteEdit.getBtnSalvar()) {

			formularioPacienteEdit.salvarPacienteEdit();

			loadPacientes();

		}

		else if (event.getSource() == formularioPacienteEdit.getBtnCancelar()) {

			formularioPacienteEdit.closeScreen();

		}

	}
}
