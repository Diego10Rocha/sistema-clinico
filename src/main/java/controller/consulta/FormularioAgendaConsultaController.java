package controller.consulta;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.AgendaConsultaDAO;
import dao.MedicoDAO;
import date.MyDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import message.MessageAlert;
import model.AgendaConsulta;
import model.Medico;
import screenManager.ScreenManager;

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

		if (isDataInvalida()) {

			msgAlert.showMessage("Por Favor informe uma data válida!", AlertType.WARNING);
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

		ScreenManager.closeScreen(btnVoltar);

	}

}
