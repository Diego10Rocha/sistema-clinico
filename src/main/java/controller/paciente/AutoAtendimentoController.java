package controller.paciente;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import controller.consulta.RequestCPFController;
import dao.AgendaConsultaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import message.MessageAlert;
import model.AgendaConsulta;
import screenManager.ScreenManager;

public class AutoAtendimentoController implements Initializable {

	@FXML
	private ListView<AgendaConsulta> lvConsultas;

	@FXML
	private Button btnMarcarConsulta;

	private static AgendaConsulta consultaSelecionada;

	private ScreenManager screenManager = new ScreenManager();

	private ObservableList<AgendaConsulta> obsConsultas;
	private RequestCPFController requestCpfController;

	private MessageAlert msg = new MessageAlert();

	@FXML
	private Button btnVoltar;

	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	@FXML
	void openScreenRequestCPF(ActionEvent event) throws IOException {

		consultaSelecionada = lvConsultas.getSelectionModel().getSelectedItem();

		if (consultaSelecionada == null) {

			msg.showMessage("Por Favor selecione uma Consulta primeiro!", AlertType.WARNING);

		}

		else {

			screenManager.openNewScreen("consulta/RequestCPF", "Requisição CPF");

			setReferenciaRequestCpfController();

		}
	}

	private void setReferenciaRequestCpfController() {

		Object currentController = screenManager.getCurrenController();

		requestCpfController = (RequestCPFController) currentController;

		setConsultaSelecionadaToRequestCPFController();

	}

	private void setConsultaSelecionadaToRequestCPFController() {

		requestCpfController.setConsultaSelecionada(consultaSelecionada);
	}

	private void loadConsultas() {

		List<AgendaConsulta> agendasConsultaCadastradas = AgendaConsultaDAO.getAgendasConsulta();

		Collections.sort(agendasConsultaCadastradas);

		obsConsultas = FXCollections.observableArrayList(agendasConsultaCadastradas);

		lvConsultas.setItems(obsConsultas);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadConsultas();

	}

	public static AgendaConsulta getConsultaSelecionada() {

		return consultaSelecionada;
	}

}
