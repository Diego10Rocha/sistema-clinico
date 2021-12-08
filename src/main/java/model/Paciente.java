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

import java.util.ArrayList;
import java.util.List;

import dao.PacienteDAO;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class Paciente extends Usuario {

	private String DATA_NASCIMENTO;
	private List<Prontuario> prontuarios;

	/**
	 * Construtor da classe
	 */
	public Paciente() {

	}

	/**
	 * Contrutor da classe
	 * @param nome
	 * @param CPF
	 * @param DATA_NASCIMENTO
	 */
	public Paciente(String nome, String CPF, String DATA_NASCIMENTO) {

		super(nome, CPF);

		this.DATA_NASCIMENTO = DATA_NASCIMENTO;
		this.prontuarios = new ArrayList<>();

		PacienteDAO.insertPatient(this);
	}

	/**
	 * Construtor da classe
	 * @param nome
	 * @param CPF
	 */
	public Paciente(String nome, String CPF) {

		super(nome, CPF);

	}

	public String getDATA_NASCIMENTO() {
		return DATA_NASCIMENTO;
	}

	public void setDATA_NASCIMENTO(String dATA_NASCIMENTO) {
		DATA_NASCIMENTO = dATA_NASCIMENTO;
	}

	public List<Prontuario> getProntuarios() {
		return this.prontuarios;
	}

	/**
	 * Metodo de cadastrar o prontuário de um paciente
	 * @param prontuario
	 */
	public void addProntuario(Prontuario prontuario) {

		if (this.prontuarios.contains(prontuario))
			return;

		this.prontuarios.add(prontuario);
	}

	/**
	 * Retorna dados de um objeto da classe em uma String
	 * @return String
	 */
	@Override
	public String toString() {
		return "Nome: " + this.getNome() + "\n" + "Data de Nascimento: " + this.getDATA_NASCIMENTO() + "\n" + "CPF: "
				+ this.getCPF();
	}

}
