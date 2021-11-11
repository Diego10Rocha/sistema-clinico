/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programação
Concluido em: 18/10/2021
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
 * A Classe <b>MessageAlert</b> representa a modelagem de <b>mensagens</b> de <b>alerta</b> 
 * referente à GUI do <b>Gerenciador de Tarefas</b>.
 * @since 2021
 * @author Joanderson Santos e Dicego Cerqueira
 */
public class MessageAlert {
	
	private Alert alert;
	
	/**
	 * Apresenta uma <b>mensagem</b> de aviso na tela.<br>
	 * Conteúdo da mensagem: "Por favor preencha os campos primeiro".
	 */
	
	public void getMessageCampoEmBranco() {
		
		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Por favor preencha os campos primeiro");
		
		alert.show();
		
	}
	
	/**
	 * Apresenta uma <b>mensagem</b> de aviso na tela.<br>
	 * Conteúdo da mensagem: "As tarefas devem estar concluídas para que um projeto seja excluído".
	 */
	
	public void getMessageTarefasNaoConcluidas() {
		
		alert = new Alert(AlertType.WARNING);
		alert.setContentText("As tarefas devem estar concluídas para que um projeto seja excluído");
		
		alert.show();
		
	}
	
	/**
	 * Apresenta uma <b>mensagem</b> de confirmação na tela.<br>
	 * Conteúdo da mensagem: "Projeto Salvo com Sucesso".
	 */
	
	public void getMessageProjetoSalvo() {
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Projeto Salvo com Sucesso!");
		
		alert.show();
	}
	
	/**
	 * Apresenta uma <b>mensagem</b> de confirmação na tela.<br>
	 * Conteúdo da mensagem: "Projeto Editado com Sucesso".
	 */
	
	public void getMessageProjetoEditado() {
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Projeto Editado com Sucesso!");
		
		alert.show();
	}
	
	/**
	 * Apresenta uma <b>mensagem</b> de aviso na tela.<br>
	 * Conteúdo da mensagem: "Por favor selecione um projeto primeiro!".
	 */
	
	public void getMessageProjetoNaoSelecionada() {
		
		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Por favor selecione um projeto primeiro!");
		
		alert.show();
	}
	
	/**
	 * Apresenta uma <b>mensagem</b> de confirmação na tela.<br>
	 * Conteúdo da mensagem: "Projeto excluído com Sucesso!".
	 */
	
	public void getMessageProjetoExcluida() {
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Projeto excluído com Sucesso!");
		
		alert.show();
	}
	
	/**
	 * Apresenta uma <b>mensagem</b> de confirmação na tela.<br>
	 * Conteúdo da mensagem: "Tarefa salva com Sucesso!".
	 */

	public void getMessageTarefaSalva() {
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Tarefa salva com Sucesso!");
		
		alert.show();
		
	}
	
	/**
	 * Apresenta uma <b>mensagem</b> de confirmação na tela.<br>
	 * Conteúdo da mensagem: "Tarefa editada com Sucesso!".
	 */

	public void getMessageTarefaEditada() {
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Tarefa editada com Sucesso!");
		
		alert.show();
		
	}
	
	/**
	 * Apresenta uma <b>mensagem</b> de aviso na tela.<br>
	 * Conteúdo da mensagem: "Por favor selecione uma Tarefa!".
	 */

	public void getMessageTarefaNaoSelecionada() {
		
		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Por favor selecione uma Tarefa!");
		
		alert.show();
		
	}
	
	/**
	 * Apresenta uma <b>mensagem</b> de confirmação na tela.<br>
	 * Conteúdo da mensagem: "Tarefa excluída com Sucesso!".
	 */
	
	public void getMessageTarefaExcluida() {
	
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Tarefa excluída com Sucesso!");
		
		alert.show();
	}
		
}
