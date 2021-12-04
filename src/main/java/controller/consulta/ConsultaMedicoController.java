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

		screenManager.openNewScreen("medico/AtendimentoMedico", "Atendimento", false, true);

		setReferenciaAtendimentoMedicoController();
	}

	private void setReferenciaAtendimentoMedicoController() {

		Object currentController = screenManager.getCurrenController();

		atendimentoMedicoController = (AtendimentoMedicoController) currentController;

		atendimentoMedicoController.addButtonsListener(this);

	}

	private static void setProximoPacienteAserAtendido() {

		List<Consulta> consultasMarcadas = GerenciadorConsulta
				.getAllConsultasMarcadasByCPF_Medico(medicoLogado.getCPF());

		Collections.sort(consultasMarcadas);

		String CPF_ProximoPacienteAserAtendido = consultasMarcadas.get(0).getCPF_paciente();

		proximoPacienteAserAtendido = PacienteDAO.findByCPF(CPF_ProximoPacienteAserAtendido);

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

		atendimentoMedicoController = new AtendimentoMedicoController();

		medicoLogado = MedicoDAO.findByCPF(Login.getCPF_userLogged());

		loadConsultas();

	}

	private void loadConsultas() {

		List<Consulta> consultasMarcadas = GerenciadorConsulta
				.getAllConsultasMarcadasByCPF_Medico(medicoLogado.getCPF());

		Collections.sort(consultasMarcadas);

		proximaConsultaAserRealizada = consultasMarcadas.get(0);

		obsConsultas = FXCollections.observableArrayList(consultasMarcadas);

		lvConsultas.setItems(obsConsultas);

	}

	public static Consulta getProximaConsultaAserRealizada() {

		return proximaConsultaAserRealizada;
	}

	public static Paciente getProximoPacienteAserAtendido() {

		setProximoPacienteAserAtendido();
		System.out.println(proximoPacienteAserAtendido);
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

				screenManager.openNewScreen("", "Receita");

			} catch (IOException e) {

				e.printStackTrace();
			}

	}

}
