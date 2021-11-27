/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programa��o
Concluido em: 18/10/2021
Declaro que este c�digo foi elaborado por Diego Cerqueira e Joanderson Santos em dupla e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************************************************************************/

package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Classe controladora da tela de informa��es do projeto
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class SobreController {

	/**
	 * Evento que abre o reposit�rio do c�digo no github
	 * 
	 * @param event
	 */
	@FXML
	void openRepository(ActionEvent event) {

		try {

			URI uri = new URI("https://github.com/Diego10Rocha/sistema-clinico");

			Desktop.getDesktop().browse(uri);

		} catch (URISyntaxException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
