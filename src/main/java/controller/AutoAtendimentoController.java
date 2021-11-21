package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.AgendaConsultaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.AgendaConsulta;

public class AutoAtendimentoController implements Initializable {

	@FXML
	private ListView<AgendaConsulta> lvConsultas;

	@FXML
	private Button btnMarcarConsulta;

	private ObservableList<AgendaConsulta> obsConsultas;

	@FXML
	void openScreenRequestCPF(ActionEvent event) {

	}

	private void loadConsultas() {

		List<AgendaConsulta> agendasConsultaCadastradas = AgendaConsultaDAO.getAgendasConsulta();
		obsConsultas = FXCollections.observableArrayList(agendasConsultaCadastradas);

		lvConsultas.setItems(obsConsultas);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadConsultas();

	}

}
