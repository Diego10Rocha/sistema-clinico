package controller.consulta;

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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import login.Login;
import model.Consulta;
import model.GerenciadorConsulta;
import model.Medico;
import screenManager.ScreenManager;

public class ConsultaMedicoController implements Initializable {

	@FXML
	private ListView<Consulta> lvConsultas;

	@FXML
	private Button btnAtenderPaciente;

	@FXML
	private Button btnVoltar;

	private ObservableList<Consulta> obsConsultas;
	private static Medico medicoLogado;
	ScreenManager screenManager = new ScreenManager();

	@FXML
	void openScreenAtendimentoPaciente(ActionEvent event) throws IOException {

		screenManager.openNewScreen("medico/AtendimentoMedicoScreen", "Atendimento");

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

}
