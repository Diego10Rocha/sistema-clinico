package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MedicoDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import message.MessageAlert;
import model.Especialidade;
import model.Medico;

public class FormularioMedicoEditController implements Initializable {

	@FXML
	private TextField txtNome;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	private TextField txtEspecialidade;

	@FXML
	private TextField txtSubEspecialidade;

	@FXML
	private TextField txtHoraConsulta;

	private Medico medicoSelecionado = MedicoRecepcionistaController.getMedicoSelecionado();

	private MessageAlert msgAlert = new MessageAlert();

	public void salvarMedicoEdit() {

		boolean isAnyCampoEmBranco = isAnyCampoEmBranco();

		if (isAnyCampoEmBranco) {

			msgAlert.showMessage("Por Favor preencha todos os campos!", AlertType.WARNING);
		}

		else {

			String especialidadeTxt = txtEspecialidade.getText();
			String subEspecialidadeTxt = txtSubEspecialidade.getText();
			
			Especialidade especialidadeOBJ = new Especialidade(especialidadeTxt, true);
			Especialidade subEspecialidadeOBJ = null;

			if (subEspecialidadeTxt != "") {

				subEspecialidadeOBJ = new Especialidade(subEspecialidadeTxt, false);
			}

			Medico newMedico = createNewMedicoEdit();

			updateMedico(newMedico);

			msgAlert.showMessage("Edição Realizada com sucesso", AlertType.INFORMATION);

			closeScreen();
		}
	}

	private void updateMedico(Medico newMedico) {

		MedicoDAO.updateDoctor(newMedico, medicoSelecionado);

	}

	private Medico createNewMedicoEdit() {

		String name = txtNome.getText();
		String especialidadeTxt = txtEspecialidade.getText();
		String subEspecialidadeTxt = txtSubEspecialidade.getText();
		String horaDisponivelConsulta = txtHoraConsulta.getText();

		Medico newMedico = new Medico(medicoSelecionado.getCRM());

		newMedico.setCPF(medicoSelecionado.getCPF());
		newMedico.setCPFs_pacientes(medicoSelecionado.getCPFs_pacientes());
		newMedico.setHoraDisponivelConsulta(horaDisponivelConsulta);
		newMedico.setNome(name);
		newMedico.setNomeEspecialidadePrincipal(especialidadeTxt);
		newMedico.setNomeSubEspecialidade(subEspecialidadeTxt);
		newMedico.setSenha(medicoSelecionado.getSenha());

		return newMedico;
	}

	public void closeScreen() {

		Stage stage = (Stage) btnSalvar.getScene().getWindow();

		stage.close();
	}

	private boolean isAnyCampoEmBranco() {

		boolean anyCampoEmBranco = false;

		if (txtNome.getText().equals(" ") || txtEspecialidade.getText().equals("")
				|| txtHoraConsulta.getText().equals("")) {

			anyCampoEmBranco = true;
		}

		return anyCampoEmBranco;

	}

	public void addButtonsListener(EventHandler<ActionEvent> listener) {

		btnSalvar.setOnAction(listener);
		btnCancelar.setOnAction(listener);

	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadInfoMedico();

	}

	private void loadInfoMedico() {

		txtNome.setText(medicoSelecionado.getSenha());
		txtEspecialidade.setText(medicoSelecionado.getEspecialidadePrincipal().getNome());
		txtHoraConsulta.setText(medicoSelecionado.getHoraDisponivelConsulta());

		if (hasSubEspecialidade())
			loadSubEspecialidade();

	}

	private boolean hasSubEspecialidade() {

		return medicoSelecionado.getSubEspecialidade() != null;
	}

	private void loadSubEspecialidade() {

		txtSubEspecialidade.setText(medicoSelecionado.getSubEspecialidade().getNome());

	}

}
