/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programação
Concluido em: 07/12/2021
Declaro que este código foi elaborado por Diego Cerqueira e Joanderson Santos em dupla e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

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

/**
 * Controller da edição de medico
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
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

	/**
	 * Metodo que salva as alterações em um médico
	 */
	public void salvarMedicoEdit() {

		if (isAnyObrigatorioCampoEmBranco()) {

			msgAlert.showMessage("Por Favor preencha todos os campos obrigatórios!", AlertType.WARNING);
		}

		else {

			boolean notSubEspecialidadeAdd = txtSubEspecialidade.getText().equals("");

			if (notSubEspecialidadeAdd)

				updateSemSubEspecialidadeMedico();

			else

				updateMedicoComSubespecialidade();

			msgAlert.showMessage("Edição Realizada com sucesso", AlertType.INFORMATION);

			closeScreen();
		}
	}

	/**
	 * Verifica se possui um campo obrigatório em branco
	 * @return boolean
	 */
	private boolean isAnyObrigatorioCampoEmBranco() {

		boolean anyObrigatorioCampoEmBranco = false;

		if (txtNome.getText().equals(" ") || txtEspecialidade.getText().equals("")
				|| txtHoraConsulta.getText().equals("")) {

			anyObrigatorioCampoEmBranco = true;
		}

		return anyObrigatorioCampoEmBranco;

	}

	/**
	 * Metodo que atualiza um medico que não possui subespecialidade
	 */
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

	/**
	 * Metodo que atualiza um medico que possui subespecialidade
	 */
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

	/**
	 * Metodo que fecha a tela
	 */
	public void closeScreen() {

		ScreenManager.closeScreen(btnCancelar);
	}

	/**
	 * Adiciona eventos aos botões: btnSalvar e btnCancelar
	 * @param listener
	 */
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

	/**
	 * Metodo que inicializa a tela com as informações do médico a ser editado
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadInfoMedico();

	}

	/**
	 * Metodo que carrega as informações do medico a ser editado
	 */
	private void loadInfoMedico() {

		txtNome.setText(medicoSelecionado.getNome());
		txtEspecialidade.setText(medicoSelecionado.getEspecialidadePrincipal().getNome());
		txtHoraConsulta.setText(medicoSelecionado.getHoraDisponivelConsulta());

		if (hasSubEspecialidade())
			loadSubEspecialidade();

	}

	/**
	 * Verifica se um medico possui subespecialidade
	 * @return boolean
	 */
	private boolean hasSubEspecialidade() {

		return medicoSelecionado.getSubEspecialidade() != null;
	}

	/**
	 * Metodo que carrega a subespecialidade de um medico
	 */
	private void loadSubEspecialidade() {

		txtSubEspecialidade.setText(medicoSelecionado.getSubEspecialidade().getNome());

	}

}
