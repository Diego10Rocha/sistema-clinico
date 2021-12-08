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

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import screenManager.ScreenManager;

/**
 * Controller da tela de médico
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class MedicoController {

	@FXML
	private Button btnConsulta;

	@FXML
	private Button btnPacientes;

	@FXML
	private Button btnSair;

	private ScreenManager screenManager = new ScreenManager();

	/**
	 * Evento que fecha a tela
	 * @param event
	 */
	@FXML
	void exit(ActionEvent event) {

		ScreenManager.closeScreen(btnSair);
	}

	/**
	 * Evento que abre a tela de consultas do dia de um médico
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenConsultas(ActionEvent event) throws IOException {

		screenManager.openNewScreen("consulta/ConsultaMedicoScreen", "Atendimento");

	}

	/**
	 * Evento que abre a tela com a lista de pacientes que ele atendeu
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenMedicoPacientes(ActionEvent event) throws IOException {

		screenManager.openNewScreen("medico/MedicoPacientes", "Pacientes");

	}

}
