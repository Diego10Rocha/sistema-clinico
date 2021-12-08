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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import login.Login;
import message.MessageAlert;
import model.Medico;
import model.Paciente;
import screenManager.ScreenManager;

/**
 * Controller da tela de pacientes de um medico
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class MedicoPacientesController implements Initializable {

	@FXML
	private ListView<Paciente> lvPacientes;

	@FXML
	private Button btnProntuarios;

	@FXML
	private Button btnVoltar;

	private Medico medicoQueDetemPacientes;
	private ObservableList<Paciente> obsPacientes;
	private ScreenManager screenManager = new ScreenManager();
	private static Paciente pacienteSelecionado;

	/**
	 * Evento que fecha a tela
	 * @param event
	 */
	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	/**
	 * Evento que abre a tela de prontuários de um paciente
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenProntuariosPacientes(ActionEvent event) throws IOException {

		pacienteSelecionado = lvPacientes.getSelectionModel().getSelectedItem();

		if (pacienteSelecionado == null) {

			new MessageAlert().showMessage("Por Favor selecione um Paciente primeiro!", AlertType.WARNING);

		}

		else {

			screenManager.openNewScreen("paciente/ProntuariosPaciente", "Prontuarios");

		}
	}

	/**
	 * Metodo que inicializa a tela com os pacientes de um médico
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		medicoQueDetemPacientes = MedicoDAO.findByCPF(Login.getCPF_userLogged());

		loadPacientes();

	}

	/**
	 * Metodo que carrega a lista de pacientes de um medico
	 */
	private void loadPacientes() {

		List<Paciente> medicoPacientes = medicoQueDetemPacientes.getPacientes();

		obsPacientes = FXCollections.observableArrayList(medicoPacientes);

		lvPacientes.setItems(obsPacientes);
	}

	public static Paciente getPacienteSelecionado() {

		return pacienteSelecionado;
	}

}
