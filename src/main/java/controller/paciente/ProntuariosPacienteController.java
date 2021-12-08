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

package controller.paciente;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controller.medico.MedicoPacientesController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Paciente;
import model.Prontuario;
import screenManager.ScreenManager;

/**
 * Controller do prontuário de um paciente
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class ProntuariosPacienteController implements Initializable {

	@FXML
	private ListView<Prontuario> lvProntuarios;

	@FXML
	private Button btnVoltar;
	private ObservableList<Prontuario> obsProntuarios;

	private Paciente pacienteSelecionado = MedicoPacientesController.getPacienteSelecionado();

	/**
	 * Evento de fechar a tela
	 * @param event
	 */
	@FXML
	void closeScreen(ActionEvent event) {
		
		ScreenManager.closeScreen(btnVoltar);
	}

	/**
	 * Metodo que inicializa a tela com a lista de prontuários de um paciente
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadProntuarios();

	}

	/**
	 * Metodo que carrega os prontuários de um paciente
	 */
	private void loadProntuarios() {

		List<Prontuario> prontuarios = pacienteSelecionado.getProntuarios();

		obsProntuarios = FXCollections.observableArrayList(prontuarios);

		lvProntuarios.setItems(obsProntuarios);

	}

}
