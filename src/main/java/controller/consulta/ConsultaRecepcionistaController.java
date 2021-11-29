package controller.consulta;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import dao.AgendaConsultaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import message.MessageAlert;
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
	private FormularioAgendaConsultaEditController formularioAgendaConsultaEdit;

	private ObservableList<AgendaConsulta> obsConsultas;

	private static AgendaConsulta agendaSelecionada;

	private MessageAlert msg = new MessageAlert();

	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	@FXML
	void openScreenRequestCPF(ActionEvent event) throws IOException {

		screenManager.openNewScreen("consulta/RequestCPF", "Marcação de Consulta");
	}

	@FXML
	void openScreenFormularioConsulta(ActionEvent event) throws IOException {

		screenManager.openNewScreen("consulta/FormularioAgendaConsulta", "Cadastro Agenda");

		setReferenciaFormularioAgendaConsulta();
	}

	private void setReferenciaFormularioAgendaConsulta() {

		Object currentController = screenManager.getCurrenController();

		formularioAgendaConsulta = (FormularioAgendaConsultaController) currentController;

		formularioAgendaConsulta.addButtonsListener(this);

	}

	@FXML
	void openScreenFormularioEditConsulta(ActionEvent event) throws IOException {

		agendaSelecionada = lvConsultas.getSelectionModel().getSelectedItem();

		if (agendaSelecionada == null) {

			msg.showMessage("Por Favor selecione uma Agenda primeiro!", AlertType.WARNING);
		}

		else {

			screenManager.openNewScreen("consulta/FormularioAgendaConsultaEdit", "Edição Agenda");

			setReferenciaFormularioAgendaConsultaEdit();
		}

	}

	private void setReferenciaFormularioAgendaConsultaEdit() {

		Object currentController = screenManager.getCurrenController();

		formularioAgendaConsultaEdit = (FormularioAgendaConsultaEditController) currentController;

		formularioAgendaConsultaEdit.addButtonsListener(this);

	}

	@FXML
	void removerConsulta(ActionEvent event) throws Exception {

		agendaSelecionada = lvConsultas.getSelectionModel().getSelectedItem();

		if (agendaSelecionada == null) {

			msg.showMessage("Por Favor selecione uma Agenda primeiro!", AlertType.WARNING);

		}

		else {

			AgendaConsultaDAO.deleteAgendaConsulta(agendaSelecionada);

			loadConsultas();

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		formularioAgendaConsulta = new FormularioAgendaConsultaController();
		formularioAgendaConsultaEdit = new FormularioAgendaConsultaEditController();

		loadConsultas();

	}

	private void loadConsultas() {

		List<AgendaConsulta> agendasConsultaCadastradas = AgendaConsultaDAO.getAgendasConsulta();
		Collections.sort(agendasConsultaCadastradas);
		obsConsultas = FXCollections.observableArrayList(agendasConsultaCadastradas);
		

		lvConsultas.setItems(obsConsultas);

	}

	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == formularioAgendaConsulta.getBtnCadastrar()) {

			formularioAgendaConsulta.salvar();

			loadConsultas();

		}

		else if (event.getSource() == formularioAgendaConsulta.getBtnVoltar()) {

			formularioAgendaConsulta.closeScreen();

		}

		if (event.getSource() == formularioAgendaConsultaEdit.getBtnSalvar()) {

			formularioAgendaConsultaEdit.salvarAgendaEdit();

			loadConsultas();

		}

		else if (event.getSource() == formularioAgendaConsultaEdit.getBtnVoltar()) {

			formularioAgendaConsultaEdit.closeScreen();

		}

	}

	public static AgendaConsulta getAgendaSelecionada() {

		return agendaSelecionada;
	}
}
