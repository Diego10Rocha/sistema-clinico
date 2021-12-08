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

package message;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * A Classe <b>MessageAlert</b> representa a modelagem de <b>mensagens</b> de
 * <b>alerta</b> referente à GUI do <b>Gerenciador de Tarefas</b>.
 * 
 * @since 2021
 * @author Joanderson Santos e Diego Cerqueira
 */
public class MessageAlert {

	private Alert alert;

	/**
	 * Apresenta uma <b>mensagem</b> de aviso na tela.<br>
	 */
	public void showMessage(String message, AlertType alertTypeMessage) {

		alert = new Alert(alertTypeMessage);
		alert.setContentText(message);

		alert.show();

	}

}
