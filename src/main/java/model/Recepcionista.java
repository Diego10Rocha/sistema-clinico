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

package model;

import dao.RecepcionistaDAO;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class Recepcionista extends Usuario{

	/**
	 * Construtor da classe
	 * @param nome
	 * @param CPF
	 */
	public Recepcionista(String nome, String CPF) {
		
		super(nome, CPF);
		
		RecepcionistaDAO.insertReceptionist(this);
	}
	
	/**
	 * Construtor da classe
	 * @param nome
	 * @param CPF
	 * @param senha
	 */
	public Recepcionista(String nome, String CPF, String senha) {
		
		super(nome, CPF);
		
		this.setSenha(senha);
		
		RecepcionistaDAO.insertReceptionist(this);
	}
}
