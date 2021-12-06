package controller.consulta;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controller.paciente.PacienteRecepcionistaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.GerenciadorConsulta;
import model.HistoricoConsulta;
import model.Paciente;
import screenManager.ScreenManager;

public class HistoricoConsultaController implements Initializable {

	@FXML
	private ListView<HistoricoConsulta> lvHistoricoConsultas;

	@FXML
	private Button btnVoltar;

	private ObservableList<HistoricoConsulta> obsHistoricoConsulta;
	private Paciente pacienteSelecionado = PacienteRecepcionistaController.getPacienteSelecionado();

	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadHistoricoConsulta();

	}

	private void loadHistoricoConsulta() {

		List<HistoricoConsulta> historicoConsulta = GerenciadorConsulta
				.getHistoryConsultasPaciente(pacienteSelecionado.getCPF());

		obsHistoricoConsulta = FXCollections.observableArrayList(historicoConsulta);

		lvHistoricoConsultas.setItems(obsHistoricoConsulta);

	}
}
