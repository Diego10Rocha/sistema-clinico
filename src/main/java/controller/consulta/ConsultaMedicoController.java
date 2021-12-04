package controller.consulta;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.ConsultaDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class ConsultaMedicoController implements Initializable {

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

	ScreenManager screenManager = new ScreenManager();

	@FXML
	void openScreenAtendimentoPaciente(ActionEvent event) throws IOException {

		screenManager.openNewScreen("medico/AtendimentoMedico", "Atendimento", false, true);

		setProximoPacienteAserAtendido();

	}

	private static void setProximoPacienteAserAtendido() {

		List<Consulta> consultasMarcadas = GerenciadorConsulta
				.getAllConsultasMarcadasByCPF_Medico(medicoLogado.getCPF());

		String CPF_ProximoPacienteAserAtendido = consultasMarcadas.get(0).getCPF_paciente();

		proximoPacienteAserAtendido = PacienteDAO.findByCPF(CPF_ProximoPacienteAserAtendido);

		System.out.println(proximoPacienteAserAtendido.getNome());
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

		loadConsultas();

	}

	private void loadConsultas() {

		List<Consulta> consultasMarcadas = GerenciadorConsulta
				.getAllConsultasMarcadasByCPF_Medico(medicoLogado.getCPF());

		obsConsultas = FXCollections.observableArrayList(consultasMarcadas);

		lvConsultas.setItems(obsConsultas);

	}

	public static Paciente getProximoPacienteAserAtendido() {

		return proximoPacienteAserAtendido;
	}

}
