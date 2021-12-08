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

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public abstract class Usuario {

	private String nome;
	private String senha;
	private String CPF;

	public Usuario() {
	};

	public Usuario(String nome, String senha, String CPF) {

		this.nome = nome;
		this.senha = senha;
		this.CPF = CPF;
	}

	public Usuario(String nome, String CPF) {

		this.nome = nome;
		this.CPF = CPF;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	/**
	 * Sobrescrita do metodo equals da classe <b>Object</>
	 * @param obj objeto a ser comparado
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {

		boolean objIsEqual = false;

		if (obj instanceof Usuario) {

			Usuario userASerComparado = (Usuario) obj;

			String cpfUserASerComparado = userASerComparado.getCPF();
			;

			if (cpfUserASerComparado.equals(this.getCPF()))
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
		return "Usuario [nome=" + nome + ", senha=" + senha + ", CPF=" + CPF + "]";
	}

}
