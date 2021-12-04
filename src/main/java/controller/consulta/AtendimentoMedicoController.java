package controller.consulta;

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

	private Medico medicoAtendendo = ConsultaMedicoController.getMedicoLogado();
	private Paciente pacienteNoAtendimento = ConsultaMedicoController.getProximoPacienteAserAtendido();
	Consulta consultaDaVez = ConsultaMedicoController.getProximaConsultaAserRealizada();

	void closeScreen() {

		ScreenManager.closeScreen(btnVoltar);
	}

	void encerrarConsulta() {

		addPacienteAtendidoToPacientesMedico();
		addProntuarioPaciente();
		marcarConsultaComoRealizada();

		new MessageAlert().showMessage("Consulta realizada com Sucesso!", AlertType.INFORMATION);

		closeScreen();

	}

	private void addPacienteAtendidoToPacientesMedico() {

		medicoAtendendo.setCPF_Paciente(pacienteNoAtendimento.getCPF());

		MedicoDAO.updateDoctor(medicoAtendendo);

	}

	private void addProntuarioPaciente() {

		Prontuario newProntuario = new Prontuario(pacienteNoAtendimento.getCPF());

		newProntuario.setAnamnese(txtAnamnese.getText());
		newProntuario.setDiagnosticosDefinitivos(txtDiagnosticosDefenitivos.getText());
		newProntuario.setExameFisico(txtExameFisico.getText());
		newProntuario.setTratamentosEfetuados(txtTratamentosEfetuados.getText());
		newProntuario.setHipotesesDiagnosticas(txtHipotesesDiagnosticas.getText());

		ProntuarioDAO.insertMedicalRecord(newProntuario);

		pacienteNoAtendimento.setProntuario(newProntuario);

		PacienteDAO.updatePatient(pacienteNoAtendimento);

	}

	private void marcarConsultaComoRealizada() {

		Consulta newConsultaRealizada = new Consulta(consultaDaVez.getData(), consultaDaVez.getData(),
				consultaDaVez.getCPF_medico(), consultaDaVez.getCPF_paciente());

		newConsultaRealizada.setRealizada(true);

		ConsultaDAO.updateSpecialty(newConsultaRealizada, consultaDaVez);

	}

	void openScreenReceita(ActionEvent event) {

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
