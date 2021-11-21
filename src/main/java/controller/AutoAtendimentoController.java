package controller;

import java.io.IOException;
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
	
	private MessageAlert msg = new MessageAlert();

	@FXML
	void openScreenRequestCPF(ActionEvent event) throws IOException {
		
		consultaSelecionada = lvConsultas.getSelectionModel().getSelectedItem();

		if (consultaSelecionada == null) {
			

			msg.getMessageConsultaNaoSelecionada();

		}

		else {

			screenManager.openNewScreen("RequestCPF", "Requisição CPF");


		}
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

	public static AgendaConsulta getConsultaSelecionada() {
		
		return consultaSelecionada;
	}

}
