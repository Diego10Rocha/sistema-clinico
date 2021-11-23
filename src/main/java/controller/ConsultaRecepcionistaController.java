package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.AgendaConsultaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.AgendaConsulta;
import screenManager.ScreenManager;

public class ConsultaRecepcionistaController implements Initializable, EventHandler<ActionEvent> {

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

	private ScreenManager screenManager = new ScreenManager();

	private FormularioAgendaConsultaController formularioAgendaConsulta;

	private ObservableList<AgendaConsulta> obsConsultas;

	@FXML
	void closeScreen(ActionEvent event) {

	}

	@FXML
	void marcarConsulta(ActionEvent event) {

	}

	@FXML
	void openScreenFormularioConsulta(ActionEvent event) throws IOException {

		screenManager.openNewScreen("FormularioAgendaConsulta", "Cadastro Agenda");

		setReferenciaFormularioAgendaConsulta();
	}

	private void setReferenciaFormularioAgendaConsulta() {

		Object currentController = screenManager.getCurrenController();

		formularioAgendaConsulta = (FormularioAgendaConsultaController) currentController;

		formularioAgendaConsulta.addButtonsListener(this);

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

	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == formularioAgendaConsulta.getBtnCadastrar()) {

			formularioAgendaConsulta.salvar();

			loadConsultas();;

		}

		else if (event.getSource() == formularioAgendaConsulta.getBtnVoltar()) {

			formularioAgendaConsulta.closeScreen();

		}

	}
}
