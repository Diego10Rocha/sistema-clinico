package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.AgendaConsultaDAO;
import dao.MedicoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import message.MessageAlert;
import model.AgendaConsulta;
import model.Medico;

public class FormularioAgendaConsultaController implements Initializable {

	@FXML
	private ListView<Medico> lvMedicos;

	@FXML
	private DatePicker txtDataConsulta;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnVoltar;

	private ObservableList<Medico> obsMedicos;

	private MessageAlert msgAlert = new MessageAlert();

	public void salvar() {

		if (isDataEmBranco()) {

			msgAlert.showMessage("Por Favor informe a data da consulta!", AlertType.WARNING);
		}

		else if (isMedicoNaoSelecionado()) {

			msgAlert.showMessage("Por Favor selecione um médico para a consulta!", AlertType.WARNING);
		}

		else {

			Medico medicoSelecionado = lvMedicos.getSelectionModel().getSelectedItem();

			String dataConsulta = txtDataConsulta.getEditor().getText();
			String horaConsulta = medicoSelecionado.getHoraDisponivelConsulta();
			String CPF_MedicoConsulta = medicoSelecionado.getCPF();

			AgendaConsulta newAgenda = new AgendaConsulta(dataConsulta, horaConsulta, CPF_MedicoConsulta);

			if (AgendaConsultaDAO.insertAgendaConsulta(newAgenda)) {

				msgAlert.showMessage("Agenda cadastrada com Sucesso!", AlertType.INFORMATION);
			}

			else {

				msgAlert.showMessage("Agenda já cadastrada!", AlertType.INFORMATION);
			}
			
			closeScreen();
		}
	}

	private boolean isDataEmBranco() {

		return txtDataConsulta.getEditor().getText().equals("");
	}

	private boolean isMedicoNaoSelecionado() {

		return lvMedicos.getSelectionModel().getSelectedItem() == null;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadMedicos();

	}

	private void loadMedicos() {

		List<Medico> medicosCadastrados = MedicoDAO.getDoctors();

		obsMedicos = FXCollections.observableArrayList(medicosCadastrados);

		lvMedicos.setItems(obsMedicos);

	}

	public void addButtonsListener(EventHandler<ActionEvent> listener) {

		btnCadastrar.setOnAction(listener);
		btnVoltar.setOnAction(listener);

	}

	public Button getBtnCadastrar() {
		return btnCadastrar;
	}

	public void setBtnCadastrar(Button btnCadastrar) {
		this.btnCadastrar = btnCadastrar;
	}

	public Button getBtnVoltar() {
		return btnVoltar;
	}

	public void setBtnVoltar(Button btnVoltar) {
		this.btnVoltar = btnVoltar;
	}

	public void closeScreen() {

		Stage stage = (Stage) btnVoltar.getScene().getWindow();

		stage.close();

	}

}
