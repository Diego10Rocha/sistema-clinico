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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.MedicoDAO;
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
import model.Medico;
import screenManager.ScreenManager;

/**
 * Controller da tela de gerenciamento de medico
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class MedicoRecepcionistaController implements Initializable, EventHandler<ActionEvent> {

	@FXML
	private ListView<Medico> lvMedicos;

	@FXML
	private Button btnRemoverMedico;

	@FXML
	private Button btnEditarMedico;

	@FXML
	private Button btnVoltar;

	private static Medico medicoSelecionado;

	private FormularioMedicoEditController formularioMedicoEdit;

	private MessageAlert msg = new MessageAlert();

	private ObservableList<Medico> obsMedicos;

	private ScreenManager screenManager = new ScreenManager();

	/**
	 * Metodo que carrega a lista de médicos
	 */
	public void loadMedicos() {

		List<Medico> medicoCadastrados = MedicoDAO.getDoctors();

		obsMedicos = FXCollections.observableArrayList(medicoCadastrados);

		lvMedicos.setItems(obsMedicos);
	}

	/**
	 * Evento que fecha a tela
	 * @param event
	 */
	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	/**
	 * Evento que abre o formulário de edição de médico
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenFormularioEditMedico(ActionEvent event) throws IOException {

		medicoSelecionado = lvMedicos.getSelectionModel().getSelectedItem();

		if (medicoSelecionado == null) {

			msg.showMessage("Por Favor selecione uma Médico primeiro!", AlertType.WARNING);

		}

		else {

			screenManager.openNewScreen("medico/FormularioMedicoEdit", "Edição médicos");

			setReferenciaFormularioMedicoEdit();

		}
	}

	/**
	 * Metodo que pega a referência do médico para a edição
	 */
	private void setReferenciaFormularioMedicoEdit() {

		Object currentController = screenManager.getCurrenController();

		formularioMedicoEdit = (FormularioMedicoEditController) currentController;

		formularioMedicoEdit.addButtonsListener(this);

	}

	/**
	 * Evento que remove um médico
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void removerMedico(ActionEvent event) throws Exception {

		medicoSelecionado = lvMedicos.getSelectionModel().getSelectedItem();

		if (medicoSelecionado == null) {

			msg.showMessage("Por Favor selecione uma Médico primeiro!", AlertType.WARNING);

		}

		else {

			if (GerenciadorConsulta.hasConsultaRealizada(medicoSelecionado.getCPF())) {

				msg.showMessage("Falha! Este médico possui consultas realizadas no sistema.", AlertType.WARNING);

				return;
			}

			else if (GerenciadorConsulta.hasConsultaMarcada(medicoSelecionado.getCPF())) {

				GerenciadorConsulta.removeAllConsultaContainsCPF(medicoSelecionado.getCPF());
				GerenciadorConsulta.removeAllConsultaAgendaContainsCPF(medicoSelecionado.getCPF());
			}

			MedicoDAO.deleteMedico(medicoSelecionado);

			loadMedicos();
		}
	}

	/**
	 * Metodo que inicializa a tela com os medicos cadastrados
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		formularioMedicoEdit = new FormularioMedicoEditController();

		loadMedicos();

	}

	/**
	 * Metodo que retorna o medico selecionado
	 * @return Medico
	 */
	public static Medico getMedicoSelecionado() {

		return medicoSelecionado;
	}

	
	/**
	 * Metodo que "ouve" os eventos dos botões: btnSalvar e btnCancelar
	 */
	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == formularioMedicoEdit.getBtnSalvar()) {

			formularioMedicoEdit.salvarMedicoEdit();

			loadMedicos();

		}

		else if (event.getSource() == formularioMedicoEdit.getBtnCancelar()) {

			formularioMedicoEdit.closeScreen();

		}

	}

}
