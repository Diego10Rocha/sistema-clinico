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

package message;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * A Classe <b>MessageAlert</b> representa a modelagem de <b>mensagens</b> de
 * <b>alerta</b> referente � GUI do <b>Gerenciador de Tarefas</b>.
 * 
 * @since 2021
 * @author Joanderson Santos e Dicego Cerqueira
 */
public class MessageAlert {

	private Alert alert;

	/**
	 * Apresenta uma <b>mensagem</b> de aviso na tela.<br>
	 * Conte�do da mensagem: "Por favor preencha os campos primeiro".
	 */

	public void getMessageCadastroSuccess() {

		alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Cadastro Realizado com sucesso");

		alert.show();

	}

	public void getMessageCadastroFailCampoEmBranco() {

		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Por Favor preencha todos os campos!");

		alert.show();

	}

	public void getMessageCadastroRecepcionistaFailCpfAlreadyRegistered() {

		alert = new Alert(AlertType.WARNING);
		alert.setContentText("CPF já Cadastrado!");

		alert.show();

	}

	public void getMessageFailLoginPassword() {

		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Senha incorreta!");

		alert.show();

	}

	public void getMessageFailLoginCPF() {
		
		alert = new Alert(AlertType.WARNING);
		alert.setContentText("CPF inválido!");

		alert.show();
		
	}

	public void getMessageEspecialidadeNaoSelecionada() {
		
		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Por Favor selecione uma Especialidade primeiro!");

		alert.show();
		
	}

	public void getMessageEspecialidadeFailExcluir() {
		
		
		alert = new Alert(AlertType.WARNING);
		alert.setContentText("A especialidade possui médico(s) asociada!");

		alert.show();
	}

}
