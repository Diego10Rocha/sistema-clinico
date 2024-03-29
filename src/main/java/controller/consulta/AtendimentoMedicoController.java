package controller.consulta;

import java.io.IOException;

import dao.ConsultaDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import dao.ProntuarioDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import message.MessageAlert;
import model.Consulta;
import model.Medico;
import model.Paciente;
import model.Prontuario;
import screenManager.ScreenManager;

public class AtendimentoMedicoController {

	@FXML
	private TextArea txtAnamnese;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnReceita;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextArea txtExameFisico;

	@FXML
	private TextArea txtDiagnosticosDefenitivos;

	@FXML
	private TextArea txtHipotesesDiagnosticas;

	@FXML
	private TextArea txtTratamentosEfetuados;

	private Medico medicoQueAtendeu;
	private Paciente pacienteAtendido;
	private Consulta consultaDaVez;

	private ScreenManager screenManager = new ScreenManager();

	void closeScreen() {

		ScreenManager.closeScreen(btnVoltar);
	}

	void encerrarConsulta() {

		addProntuarioPaciente();
		addPacienteAtendidoComoPacienteMedicoQueAtendeu();
		marcarConsultaComoRealizada();

		new MessageAlert().showMessage("Consulta realizada com Sucesso!", AlertType.INFORMATION);

		closeScreen();

	}

	private void addProntuarioPaciente() {

		pacienteAtendido = ConsultaMedicoController.getProximoPacienteAserAtendido();

		Prontuario newProntuario = new Prontuario(pacienteAtendido.getCPF());

		newProntuario.setAnamnese(txtAnamnese.getText());
		newProntuario.setDiagnosticosDefinitivos(txtDiagnosticosDefenitivos.getText());
		newProntuario.setExameFisico(txtExameFisico.getText());
		newProntuario.setTratamentosEfetuados(txtTratamentosEfetuados.getText());
		newProntuario.setHipotesesDiagnosticas(txtHipotesesDiagnosticas.getText());

		ProntuarioDAO.insertMedicalRecord(newProntuario);

		pacienteAtendido.addProntuario(newProntuario);

		PacienteDAO.updatePatient(pacienteAtendido);

	}

	private void addPacienteAtendidoComoPacienteMedicoQueAtendeu() {

		medicoQueAtendeu = ConsultaMedicoController.getMedicoLogado();

		medicoQueAtendeu.setCPF_Paciente(pacienteAtendido.getCPF());

		MedicoDAO.updateDoctor(medicoQueAtendeu);

	}

	private void marcarConsultaComoRealizada() {

		consultaDaVez = ConsultaMedicoController.getProximaConsultaAserRealizada();

		Consulta newConsultaRealizada = new Consulta(consultaDaVez.getData(), consultaDaVez.getHora(),
				consultaDaVez.getCPF_medico(), consultaDaVez.getCPF_paciente());

		newConsultaRealizada.setRealizada(true);

		ConsultaDAO.updateSpecialty(newConsultaRealizada, consultaDaVez);

	}

	void openScreenReceita() throws IOException {

		screenManager.openNewScreen("consulta/ReceitaMedica", "Receita");
	}

	public void addButtonsListener(EventHandler<ActionEvent> listener) {

		btnSalvar.setOnAction(listener);
		btnVoltar.setOnAction(listener);
		btnReceita.setOnAction(listener);

	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public Button getBtnReceita() {
		return btnReceita;
	}

	public void setBtnReceita(Button btnReceita) {
		this.btnReceita = btnReceita;
	}

	public Button getBtnVoltar() {
		return btnVoltar;
	}

	public void setBtnVoltar(Button btnVoltar) {
		this.btnVoltar = btnVoltar;
	}

}
