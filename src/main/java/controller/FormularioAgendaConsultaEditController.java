package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.AgendaConsultaDAO;
import date.MyDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import message.MessageAlert;
import model.AgendaConsulta;
import model.Especialidade;
import model.Medico;

public class FormularioAgendaConsultaEditController implements Initializable {

	@FXML
	private DatePicker txtDataConsulta;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField txtHoraConsulta;

	private AgendaConsulta agendaSelecionada = ConsultaRecepcionistaController.getAgendaSelecionada();

	private MessageAlert msgAlert = new MessageAlert();

	public void salvarAgendaEdit() {

		boolean isAnyCampoEmBranco = isAnyCampoEmBranco();

		if (isAnyCampoEmBranco) {

			msgAlert.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);

		}

		else if (isDataInvalida()) {

			msgAlert.showMessage("Por Favor informe uma data válida!", AlertType.WARNING);
		}

		else {

			AgendaConsulta newAgenda = createNewAgendaEdit();

			updateAgenda(newAgenda);

			msgAlert.showMessage("Edição Realizada com sucesso", AlertType.INFORMATION);

			closeScreen();
		}
	}

	public boolean isDataInvalida() {

		return isDataMenorDoQueDataAtual() || isDataEmBranco();
	}

	private boolean isDataMenorDoQueDataAtual() {

		MyDate myDate = new MyDate();

		String dataAtual = myDate.getCurrentDate();
		String dataSelecionada = txtDataConsulta.getEditor().getText();

		int valComparate = 0;

		try {

			valComparate = myDate.compareTo(dataAtual, dataSelecionada);

		} catch (IllegalArgumentException e) {

			return true;
		}

		return valComparate >= 1;
	}

	private boolean isDataEmBranco() {

		return txtDataConsulta.getEditor().getText().equals("");
	}

	private AgendaConsulta createNewAgendaEdit() {
		
		String data = txtDataConsulta.getEditor().getText();
		String hora = txtHoraConsulta.getText();
		String CPF_Medico = agendaSelecionada.getCPF_medico();
		
		AgendaConsulta newAgenda = new AgendaConsulta(data, hora, CPF_Medico);
				
		return newAgenda;
	}

	private void updateAgenda(AgendaConsulta newAgenda) {
		
		AgendaConsultaDAO.updateAgenda(newAgenda, agendaSelecionada);

	}

	private boolean isAnyCampoEmBranco() {

		boolean anyCampoEmBranco = false;

		if (txtHoraConsulta.getText().equals(" ") || txtDataConsulta.getEditor().getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;

	}

	public void closeScreen() {

		Stage stage = (Stage) btnVoltar.getScene().getWindow();

		stage.close();

	}

	public void addButtonsListener(EventHandler<ActionEvent> listener) {

		btnSalvar.setOnAction(listener);
		btnVoltar.setOnAction(listener);

	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public Button getBtnVoltar() {
		return btnVoltar;
	}

	public void setBtnVoltar(Button btnVoltar) {
		this.btnVoltar = btnVoltar;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadInfoMedico();

	}

	private void loadInfoMedico() {

		txtHoraConsulta.setText(agendaSelecionada.getHora());

	}

}
