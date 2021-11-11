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

	public void getMessageCadastroRecepcionistaSucces() {

		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Cadastro Realizado com sucesso");

		alert.show();

	}
	
	public void getMessageCadastroRecepcionistaFailCampoEmBranco() {

		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Por Favor preencha todos os campos!");

		alert.show();

	}
	
	public void getMessageCadastroRecepcionistaFailCpfAlreadyRegistered() { 

		alert = new Alert(AlertType.WARNING);
		alert.setContentText("CPF já Cadastrado!");

		alert.show();

	}

	/**
	 * Apresenta uma <b>mensagem</b> de aviso na tela.<br>
	 * Conte�do da mensagem: "As tarefas devem estar conclu�das para que um projeto
	 * seja exclu�do".
	 */

	public void getMessageTarefasNaoConcluidas() {

		alert = new Alert(AlertType.WARNING);
		alert.setContentText("As tarefas devem estar conclu�das para que um projeto seja exclu�do");

		alert.show();

	}

	/**
	 * Apresenta uma <b>mensagem</b> de confirma��o na tela.<br>
	 * Conte�do da mensagem: "Projeto Salvo com Sucesso".
	 */

	public void getMessageProjetoSalvo() {

		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Projeto Salvo com Sucesso!");

		alert.show();
	}

	/**
	 * Apresenta uma <b>mensagem</b> de confirma��o na tela.<br>
	 * Conte�do da mensagem: "Projeto Editado com Sucesso".
	 */

	public void getMessageProjetoEditado() {

		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Projeto Editado com Sucesso!");

		alert.show();
	}

	/**
	 * Apresenta uma <b>mensagem</b> de aviso na tela.<br>
	 * Conte�do da mensagem: "Por favor selecione um projeto primeiro!".
	 */

	public void getMessageProjetoNaoSelecionada() {

		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Por favor selecione um projeto primeiro!");

		alert.show();
	}

	/**
	 * Apresenta uma <b>mensagem</b> de confirma��o na tela.<br>
	 * Conte�do da mensagem: "Projeto exclu�do com Sucesso!".
	 */

	public void getMessageProjetoExcluida() {

		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Projeto exclu�do com Sucesso!");

		alert.show();
	}

	/**
	 * Apresenta uma <b>mensagem</b> de confirma��o na tela.<br>
	 * Conte�do da mensagem: "Tarefa salva com Sucesso!".
	 */

	public void getMessageTarefaSalva() {

		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Tarefa salva com Sucesso!");

		alert.show();

	}

	/**
	 * Apresenta uma <b>mensagem</b> de confirma��o na tela.<br>
	 * Conte�do da mensagem: "Tarefa editada com Sucesso!".
	 */

	public void getMessageTarefaEditada() {

		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Tarefa editada com Sucesso!");

		alert.show();

	}

	/**
	 * Apresenta uma <b>mensagem</b> de aviso na tela.<br>
	 * Conte�do da mensagem: "Por favor selecione uma Tarefa!".
	 */

	public void getMessageTarefaNaoSelecionada() {

		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Por favor selecione uma Tarefa!");

		alert.show();

	}

	/**
	 * Apresenta uma <b>mensagem</b> de confirma��o na tela.<br>
	 * Conte�do da mensagem: "Tarefa exclu�da com Sucesso!".
	 */

	public void getMessageTarefaExcluida() {

		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Tarefa exclu�da com Sucesso!");

		alert.show();
	}

}
