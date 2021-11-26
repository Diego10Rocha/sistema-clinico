package controller.medico;

import java.net.URL;
import java.util.ResourceBundle;

import dao.EspecialidadeDAO;
import dao.MedicoDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

			createEspecialidadeIfNotExists();

			updateMedico();

			msgAlert.showMessage("Edição Realizada com sucesso", AlertType.INFORMATION);

			closeScreen();
		}
	}

	private void createEspecialidadeIfNotExists() {

		String especialidadeTxt = txtEspecialidade.getText();
		String subEspecialidadeTxt = txtSubEspecialidade.getText();

		boolean notEspecialidadeAlreadyRegistered = !EspecialidadeDAO.specialtyAlreadyRegistered(especialidadeTxt);
		boolean notSubEspecialidadeAlreadyRegistered = !EspecialidadeDAO
				.specialtyAlreadyRegistered(subEspecialidadeTxt);

		boolean notNameSubEspecialidadeEmpty = !subEspecialidadeTxt.equals("");

		if (notEspecialidadeAlreadyRegistered) {

			Especialidade especialidade = new Especialidade(especialidadeTxt, true);
		}

		if (notSubEspecialidadeAlreadyRegistered && notNameSubEspecialidadeEmpty) {

			Especialidade subEspecialidade = new Especialidade(subEspecialidadeTxt, false);
		}

	}

	private void updateMedico() {

		String name = txtNome.getText();
		String especialidadeTxt = txtEspecialidade.getText();
		String subEspecialidadeTxt = txtSubEspecialidade.getText();
		String horaDisponivelConsulta = txtHoraConsulta.getText();

		medicoSelecionado.setNome(name);
		medicoSelecionado.setNomeEspecialidadePrincipal(especialidadeTxt);
		medicoSelecionado.setNomeSubEspecialidade(subEspecialidadeTxt);
		medicoSelecionado.setHoraDisponivelConsulta(horaDisponivelConsulta);

		MedicoDAO.updateDoctor(medicoSelecionado);

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

		txtNome.setText(medicoSelecionado.getNome());
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
