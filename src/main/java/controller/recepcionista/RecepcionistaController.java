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

package controller.recepcionista;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import screenManager.ScreenManager;

/**
 * Controller da tela de recepcionista
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class RecepcionistaController {

	@FXML
    private MenuButton menuCadastro;

    @FXML
    private MenuItem menuItemCadastroMedico;

    @FXML
    private MenuItem menuItemCadastroPaciente;

    @FXML
    private MenuItem menuItemCadastroRecepcionista;

    @FXML
    private Button menuItemAgendaConsultas;

    @FXML
    private Button menuItemVerMedicos;

    @FXML
    private Button menuItemVerPacientes;

    @FXML
    private Button menuItemVerEspecialidades;

    @FXML
    private Button btnSair;

	private ScreenManager screenManager = new ScreenManager();

	/**
	 * Evento que abre tela de cadastro de médico
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenCadastroMedico(ActionEvent event) throws IOException {

		screenManager.openNewScreen("medico/FormularioCadastroMedico", "Cadastro Medico");
	}

	/**
	 * Evento que abre tela de cadastro de Paciente
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenCadastroPaciente(ActionEvent event) throws IOException {

		screenManager.openNewScreen("paciente/FormularioCadastroPaciente", "Cadastro Paciente");
	}

	/**
	 * Evento que abre tela de cadastro de recepcionista
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenCadastroRecepcionista(ActionEvent event) throws IOException {

		screenManager.openNewScreen("recepcionista/FormularioCadastroRecepcionista", "Cadastro Recepcionista");
	}

	/**
	 * Evento que abre tela de consultas
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenConsultaRecepcionista(ActionEvent event) throws IOException {

		screenManager.openNewScreen("consulta/ConsultaRecepcionistaScreen", "Consulta");
	}

	/**
	 * Evento que abre tela de especialidades
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenEspecialidades(ActionEvent event) throws IOException {

		screenManager.openNewScreen("especialidade/EspecialidadeScreen", "Especialidade");
	}

	/**
	 * Evento que abre tela de medicos cadastrados
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenMedicosCadastrados(ActionEvent event) throws IOException {

		screenManager.openNewScreen("medico/MedicosRecepcionistaScreen", "Médicos");
	}

	/**
	 * Evento que abre tela de pacientes cadastrados
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenPacientesCadastrados(ActionEvent event) throws IOException {

		screenManager.openNewScreen("paciente/PacientesRecepcionistaScreen", "Pacientes");
	}

	/**
	 * Evento que fecha a tela de um recepcionista
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void sair(ActionEvent event) {

		ScreenManager.closeScreen(btnSair);

	}

}
