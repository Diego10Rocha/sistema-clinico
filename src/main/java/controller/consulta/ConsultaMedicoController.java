package controller.consulta;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import dao.ConsultaDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import login.Login;
import message.MessageAlert;
import model.Consulta;
import model.GerenciadorConsulta;
import model.Medico;
import model.Paciente;
import screenManager.ScreenManager;

public class ConsultaMedicoController implements Initializable, EventHandler<ActionEvent> {

	@FXML
	private ListView<Consulta> lvConsultas;

	@FXML
	private Button btnAtenderPaciente;
	@FXML
	private Button btnCancelarConsulta;

	@FXML
	private Button btnVoltar;

	private ObservableList<Consulta> obsConsultas;

	private MessageAlert msg = new MessageAlert();

	private static Medico medicoLogado;
	private static Paciente proximoPacienteAserAtendido;
	private static Consulta proximaConsultaAserRealizada;
	private static AtendimentoMedicoController atendimentoMedicoController;

	ScreenManager screenManager = new ScreenManager();

	@FXML
	void openScreenAtendimentoPaciente(ActionEvent event) throws IOException {

		if (hasConsultaMarcadaHoje()) {

			screenManager.openNewScreen("medico/AtendimentoMedico", "Atendimento", false, true);

			setReferenciaAtendimentoMedicoController();

		}

		else

			msg.showMessage("Sem consultas maracadas até o momento", AlertType.INFORMATION);

	}

	private static boolean hasConsultaMarcadaHoje() {

		return !getConsultasMarcadasHoje().isEmpty();
	}

	private void setReferenciaAtendimentoMedicoController() {

		Object currentController = screenManager.getCurrenController();

		atendimentoMedicoController = (AtendimentoMedicoController) currentController;

		atendimentoMedicoController.addButtonsListener(this);

	}

	@FXML
	void cancelarConsulta(ActionEvent event) throws Exception {

		Consulta consultaSelecionada = lvConsultas.getSelectionModel().getSelectedItem();

		if (consultaSelecionada == null) {

			msg.showMessage("Por Favor selecione uma consulta primeiro!", AlertType.WARNING);
		}

		else {

			ConsultaDAO.deleteConsulta(consultaSelecionada);

			loadConsultas();

			msg.showMessage("Consulta cancelada com Sucesso", AlertType.INFORMATION);

		}
	}

	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		medicoLogado = MedicoDAO.findByCPF(Login.getCPF_userLogged());

		atendimentoMedicoController = new AtendimentoMedicoController();

		loadConsultas();

	}

	private void loadConsultas() {

		List<Consulta> consultasMarcadasHoje = getConsultasMarcadasHoje();

		obsConsultas = FXCollections.observableArrayList(consultasMarcadasHoje);

		lvConsultas.setItems(obsConsultas);

	}

	private static List<Consulta> getConsultasMarcadasHoje() {

		List<Consulta> consultasMarcadas = GerenciadorConsulta
				.getConsultasMarcadasHojeByCPF_Medico(medicoLogado.getCPF());

		Collections.sort(consultasMarcadas);

		return consultasMarcadas;
	}

	public static Consulta getProximaConsultaAserRealizada() {

		if (hasConsultaMarcadaHoje()) {

			proximaConsultaAserRealizada = getConsultasMarcadasHoje().get(0);
		}

		return proximaConsultaAserRealizada;
	}

	public static Paciente getProximoPacienteAserAtendido() {

		if (hasConsultaMarcadaHoje()) {

			String CPF_ProximoPacienteAserAtendido = getProximaConsultaAserRealizada().getCPF_paciente();

			proximoPacienteAserAtendido = PacienteDAO.findByCPF(CPF_ProximoPacienteAserAtendido);
		}

		return proximoPacienteAserAtendido;
	}

	public static Medico getMedicoLogado() {

		return medicoLogado;
	}

	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == atendimentoMedicoController.getBtnSalvar()) {

			atendimentoMedicoController.encerrarConsulta();

			loadConsultas();

		}

		else if (event.getSource() == atendimentoMedicoController.getBtnVoltar()) {

			atendimentoMedicoController.closeScreen();

		} else

			try {

				atendimentoMedicoController.openScreenReceita();

			} catch (IOException e) {

				e.printStackTrace();
			}

	}

}
