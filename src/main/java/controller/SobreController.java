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

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Classe controladora da tela de informações do projeto
 * 
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class SobreController {

	/**
	 * Evento que abre o repositório do código no github
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
