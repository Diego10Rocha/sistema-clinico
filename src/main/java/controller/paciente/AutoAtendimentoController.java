/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programação
Concluido em: 07/12/2021
Declaro que este código foi elaborado por Diego Cerqueira e Joanderson Santos em dupla e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

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

/**
 * Controller do auto atendimento
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
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

	/**
	 * Evento que fecha a tela de auto atendimento
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(btnVoltar);
	}

	/**
	 * Evento que abre tela de marcação de consulta
	 * @param event
	 * @throws IOException
	 */
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

	/**
	 * metodo que guarda a referência do controller
	 */
	private void setReferenciaRequestCpfController() {

		Object currentController = screenManager.getCurrenController();

		requestCpfController = (RequestCPFController) currentController;

		setConsultaSelecionadaToRequestCPFController();

	}

	
	/**
	 * Metodo que guarda a referência da consulta que será marcada
	 */
	private void setConsultaSelecionadaToRequestCPFController() {

		requestCpfController.setConsultaSelecionada(consultaSelecionada);
	}

	/**
	 * Evento que carrega as consultas na tela
	 */
	private void loadConsultas() {

		List<AgendaConsulta> agendasConsultaCadastradas = AgendaConsultaDAO.getAgendasConsulta();

		Collections.sort(agendasConsultaCadastradas);

		obsConsultas = FXCollections.observableArrayList(agendasConsultaCadastradas);

		lvConsultas.setItems(obsConsultas);

	}

	/**
	 * Metodo que inicializa a tela com as consultas
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadConsultas();

	}

	/**
	 * Metodo que retorna a consulta selecionada para o agendamento
	 * @return AgendaConsulta
	 */
	public static AgendaConsulta getConsultaSelecionada() {

		return consultaSelecionada;
	}

}
