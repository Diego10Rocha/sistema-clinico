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

package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import screenManager.ScreenManager;

/**
 * Controller da tela inicial
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class MainScreenController {

	@FXML
	private Button menuItemAutoAtendimento;

	@FXML
	private Button menuItemLogin;

	@FXML
	private Button menuItemCadastroRecepcionista;

	@FXML
	private Button menuItemSobre;

	private ScreenManager screenManager = new ScreenManager();

	@FXML
	private Button menuItemSair;

	/**
	 * Evento de fechar a tela
	 * @param event
	 */
	@FXML
	void closeScreen(ActionEvent event) {

		ScreenManager.closeScreen(menuItemSair);
	}

	/**
	 * Evento que abre a tela de cadastro de recepcionista
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openFormularioScreenCadastroRecepcionista(ActionEvent event) throws IOException {

		screenManager.openNewScreen("recepcionista/FormularioCadastroRecepcionista", "Cadastro recepcionista");

	}

	/**
	 * Evento que abre a tela de auto atendimento
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenAutoAtendimento(ActionEvent event) throws IOException {

		screenManager.openNewScreen("paciente/AutoAtendimentoScreen", "Autoatendimento");
	}

	/**
	 * Evento que abre a tela de login
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void openScreenLogin(ActionEvent event) throws IOException {

		screenManager.openNewScreen("login/LoginScreen", "Login", false);
	}

	/**
	 * Evento que abre a tela de informações do sistema
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void showSobreSistema(ActionEvent event) throws IOException {

		screenManager.openNewScreen("SobreScreen", "About");
	}

}
