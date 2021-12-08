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

import dao.EspecialidadeDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class Medico extends Usuario {

	private final String CRM;
	private String horaDisponivelConsulta;
	private List<String> CPFs_pacientes;
	private final int MAX_QUANTIDADE_ID_ESPECIALIDADE = 2;
	private int[] IDs_Especialidade;// indice 0 - Especialidade principal; indice 1 - subespecialidade;

	/**
	 * Construtor da classe
	 * @param nome
	 * @param senha
	 * @param CPF
	 * @param CRM
	 * @param ID_EspecialidadePrincipal
	 * @param horaDisponivelConsulta
	 */
	public Medico(String nome, String senha, String CPF, String CRM, int ID_EspecialidadePrincipal,
			String horaDisponivelConsulta) {

		super(nome, senha, CPF);

		this.CRM = CRM;
		this.horaDisponivelConsulta = horaDisponivelConsulta;
		this.CPFs_pacientes = new ArrayList<>();
		this.IDs_Especialidade = new int[MAX_QUANTIDADE_ID_ESPECIALIDADE];
		this.setID_EspecialidadePrincipal(ID_EspecialidadePrincipal);

		MedicoDAO.insertDoctor(this);
	}

	/**
	 * Construtor da classe
	 * @param nome
	 * @param senha
	 * @param CPF
	 * @param CRM
	 * @param ID_EspecialidadePrincipal
	 * @param ID_SubEspecialidade
	 * @param horaDisponivelConsulta
	 */
	public Medico(String nome, String senha, String CPF, String CRM, int ID_EspecialidadePrincipal,
			int ID_SubEspecialidade, String horaDisponivelConsulta) {

		super(nome, senha, CPF);

		this.CRM = CRM;
		this.CPFs_pacientes = new ArrayList<>();
		this.IDs_Especialidade = new int[MAX_QUANTIDADE_ID_ESPECIALIDADE];
		this.setID_EspecialidadePrincipal(ID_EspecialidadePrincipal);
		this.setID_SubEspecialidade(ID_SubEspecialidade);
		this.horaDisponivelConsulta = horaDisponivelConsulta;

		MedicoDAO.insertDoctor(this);
	}

	/**
	 * Retorna a lista de pacientes do médico
	 * @return List<Paciente>
	 */
	public List<Paciente> getPacientes() {

		List<Paciente> pacientes = new ArrayList<>();

		this.CPFs_pacientes.forEach(CPF_Paciente -> pacientes.add(PacienteDAO.findByCPF(CPF_Paciente)));

		return pacientes;
	}

	public List<String> getCPFs_pacientes() {
		return CPFs_pacientes;
	}

	public void setCPFs_pacientes(List<String> CPFs_pacientes) {
		this.CPFs_pacientes = CPFs_pacientes;
	}

	public void setCPF_Paciente(String CPF_paciente) {

		if (this.CPFs_pacientes.contains(CPF_paciente))
			return;

		this.CPFs_pacientes.add(CPF_paciente);
	}

	public String getCRM() {
		return CRM;
	}

	public void setID_EspecialidadePrincipal(int ID_EspecialidadePrincipal) {
		this.IDs_Especialidade[0] = ID_EspecialidadePrincipal;
	}

	public void setID_SubEspecialidade(int ID_SubEspecialidade) {
		this.IDs_Especialidade[1] = ID_SubEspecialidade;
	}

	public Especialidade getEspecialidadePrincipal() {

		return EspecialidadeDAO.findById(IDs_Especialidade[0]);
	}

	public Especialidade getSubEspecialidade() {
		return EspecialidadeDAO.findById(IDs_Especialidade[1]);
	}

	public String getHoraDisponivelConsulta() {
		return horaDisponivelConsulta;
	}

	public void setHoraDisponivelConsulta(String horaDisponivelConsulta) {
		this.horaDisponivelConsulta = horaDisponivelConsulta;
	}

	/**
	 * Retorna dados de um objeto da classe em uma String
	 * @return String
	 */
	@Override
	public String toString() {

		String subEspecialidade = "Não detém.";

		if (this.getSubEspecialidade() != null)
			subEspecialidade = this.getSubEspecialidade().getNome();

		return "Nome: " + this.getNome() + "\n" + "CRM:  " + this.getCRM() + "\n" + "Especialidade: "
				+ this.getEspecialidadePrincipal().getNome() + "\n" + "SubEspecialidade: " + subEspecialidade + "\n"
				+ "Hora disponível para consultas: " + this.getHoraDisponivelConsulta();
	}

}
