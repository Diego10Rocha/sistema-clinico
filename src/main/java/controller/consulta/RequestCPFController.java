package controller.consulta;

import controller.paciente.AutoAtendimentoController;
import dao.ConsultaDAO;
import dao.PacienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import message.MessageAlert;
import model.AgendaConsulta;
import model.Consulta;
import screenManager.ScreenManager;

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

			Consulta newConsulta = createNewConsulta();

			boolean ConsultaAlreadyMarcada = ConsultaDAO.consultaAlreadyRegistered(newConsulta);

			if (ConsultaAlreadyMarcada)

				msg.showMessage("A consulta já foi marcada.", AlertType.WARNING);

			else

				msg.showMessage("Consulta marcada com Sucesso", AlertType.INFORMATION);

		}

		else {

			msg.showMessage("CPF não cadastrado!", AlertType.WARNING);

		}

		closeScreen();

	}

	private Consulta createNewConsulta() {

		String CPF_Medico = consultaSelecionada.getCPF_medico();
		String horaConsulta = consultaSelecionada.getHora();
		String dataConsulta = consultaSelecionada.getData();
		String CPF_Paciente = txtCPF.getText();

		return new Consulta(dataConsulta, horaConsulta, CPF_Medico, CPF_Paciente);

	}

	private void closeScreen() {

		ScreenManager.closeScreen(btnMarcarConsulta);

	}
}
