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

import dao.EspecialidadeDAO;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class Especialidade {

	private int id;
	private String nome;
	private boolean principal;// true - principal; false - subespecialidade

	/**
	 * Construtor da classe
	 */
	public Especialidade() {

	}

	/**
	 * Construtor da classe
	 * @param nome
	 * @param principal
	 */
	public Especialidade(String nome, boolean principal) {
		this.nome = nome;
		this.principal = principal;
		EspecialidadeDAO.insertSpecialty(this);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sobrescrita do metodo equals da classe <b>Object</>
	 * @param obj objeto a ser comparado
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {

		boolean objIsEqual = false;

		if (obj instanceof Especialidade) {

			Especialidade especialidadeASerComparada = (Especialidade) obj;

			if (especialidadeASerComparada.getId() == this.getId())

				objIsEqual = true;

		}

		return objIsEqual;
	}

	/**
	 * Retorna dados de um objeto da classe em uma String
	 * @return String
	 */
	@Override
	public String toString() {
		return "Nome: " + this.getNome();
	}

}
