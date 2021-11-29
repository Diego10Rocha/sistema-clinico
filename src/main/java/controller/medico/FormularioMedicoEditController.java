package controller.medico;

import java.net.URL;
import java.util.ResourceBundle;

import dao.EspecialidadeDAO;
import dao.MedicoDAO;
import factory.EspecialidadeFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import message.MessageAlert;
import model.Medico;
import screenManager.ScreenManager;

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

		if (isAnyObrigatorioCampoEmBranco()) {

			msgAlert.showMessage("Por Favor preencha todos os campos obrigatÃ³rios!", AlertType.WARNING);
		}

		else {

			boolean notSubEspecialidadeAdd = txtSubEspecialidade.getText().equals("");

			if (notSubEspecialidadeAdd)

				updateSemSubEspecialidadeMedico();

			else

				updateMedicoComSubespecialidade();

			msgAlert.showMessage("EdiÃ§Ã£o Realizada com sucesso", AlertType.INFORMATION);

			closeScreen();
		}
	}

	private boolean isAnyObrigatorioCampoEmBranco() {

		boolean anyObrigatorioCampoEmBranco = false;

		if (txtNome.getText().equals(" ") || txtEspecialidade.getText().equals("")
				|| txtHoraConsulta.getText().equals("")) {

			anyObrigatorioCampoEmBranco = true;
		}

		return anyObrigatorioCampoEmBranco;

	}

	private void updateSemSubEspecialidadeMedico() {

		String name = txtNome.getText();
		String especialidadeTxt = txtEspecialidade.getText();
		String horaDisponivelConsulta = txtHoraConsulta.getText();

		EspecialidadeFactory.createEspecialidade(especialidadeTxt, true);

		int idEspecialidadePrincipal = EspecialidadeDAO.findByName(especialidadeTxt).getId();

		medicoSelecionado.setNome(name);
		medicoSelecionado.setID_EspecialidadePrincipal(idEspecialidadePrincipal);
		medicoSelecionado.setHoraDisponivelConsulta(horaDisponivelConsulta);

		MedicoDAO.updateDoctor(medicoSelecionado);
	}

	private void updateMedicoComSubespecialidade() {

		String name = txtNome.getText();
		String especialidadeTxt = txtEspecialidade.getText();
		String subEspecialidadeTxt = txtSubEspecialidade.getText();
		String horaDisponivelConsulta = txtHoraConsulta.getText();

		EspecialidadeFactory.createEspecialidade(especialidadeTxt, subEspecialidadeTxt);

		int idEspecialidadePrincipal = EspecialidadeDAO.findByName(especialidadeTxt).getId();
		int idSubEspecialidade = EspecialidadeDAO.findByName(subEspecialidadeTxt).getId();

		medicoSelecionado.setNome(name);
		medicoSelecionado.setID_EspecialidadePrincipal(idEspecialidadePrincipal);
		medicoSelecionado.setID_SubEspecialidade(idSubEspecialidade);
		medicoSelecionado.setHoraDisponivelConsulta(horaDisponivelConsulta);

		MedicoDAO.updateDoctor(medicoSelecionado);

	}

	public void closeScreen() {

		ScreenManager.closeScreen(btnCancelar);
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
