package controller;

import dao.ConsultaDAO;
import dao.PacienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import message.MessageAlert;
import model.AgendaConsulta;
import model.Consulta;
import model.Medico;
import model.Paciente;

public class RequestCPFController {

	@FXML
	private TextField txtCPF;

	@FXML
	private Button btnMarcarConsulta;

	private AgendaConsulta consultaSelecionada = AutoAtendimentoController.getConsultaSelecionada();

	private MessageAlert msg = new MessageAlert();

	@FXML
	void marcar(ActionEvent event) {

		String CPF = txtCPF.getText();

		boolean isPacienteCadastrado = PacienteDAO.cpfAlreadyRegistered(CPF);

		if (isPacienteCadastrado) {

			Paciente paciente = PacienteDAO.findByCPF(CPF);
			Medico medico = consultaSelecionada.getMedico();
			String horaConsulta = consultaSelecionada.getHora();
			String dataConsulta = consultaSelecionada.getData();

			Consulta newConsulta = new Consulta(dataConsulta, horaConsulta, medico, paciente);

			ConsultaDAO.insertConsulta(newConsulta);

			msg.showMessage("Consulta marcada com Sucesso", AlertType.INFORMATION);

		}

		else {

			msg.showMessage("CPF não cadastrado!", AlertType.WARNING);;
		}

		closeScreen();

	}

	private void closeScreen() {

		Stage stage = (Stage) btnMarcarConsulta.getScene().getWindow();

		stage.close();

	}
}
