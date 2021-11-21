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

public class ConsultaRecepcionistaController implements Initializable {

	@FXML
	private ListView<AgendaConsulta> lvConsultas;

	@FXML
	private Button btnMarcarConsulta;

	@FXML
	private Button btnAdicionarConsulta;

	@FXML
	private Button btnRemoverConsulta;

	@FXML
	private Button btnEditarConsulta;

	@FXML
	private Button btnVoltar;

	private ObservableList<AgendaConsulta> obsConsultas;

	@FXML
	void closeScreen(ActionEvent event) {

	}

	@FXML
	void marcarConsulta(ActionEvent event) {

	}

	@FXML
	void openScreenFormularioConsulta(ActionEvent event) {

	}

	@FXML
	void openScreenFormularioEditConsulta(ActionEvent event) {

	}

	@FXML
	void removerConsulta(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadConsultas();

	}

	private void loadConsultas() {

		List<AgendaConsulta> agendasConsultaCadastradas = AgendaConsultaDAO.getAgendasConsulta();
		obsConsultas = FXCollections.observableArrayList(agendasConsultaCadastradas);

		lvConsultas.setItems(obsConsultas);

	}
}
