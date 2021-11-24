package model;

import java.util.ArrayList;
import java.util.List;

import dao.EspecialidadeDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;

public class Medico extends Usuario {

	private final String CRM;
	private String horaDisponivelConsulta;
	private List<Paciente> pacientes;
	private List<String> CPFs_pacientes;
	private final int MAX_QUANTIDADE_NOMES_ESPECIALIDADE = 2;
	private String[] nomesEspecialidade;// indice 0 - Especialidade principal; indice 1 - subespecialidade;;

	public Medico(String CRM) {
		this.CRM = CRM;
		this.pacientes = new ArrayList<>();
		this.CPFs_pacientes = new ArrayList<>();
		this.nomesEspecialidade = new String[MAX_QUANTIDADE_NOMES_ESPECIALIDADE];

	};

	public Medico(String nome, String senha, String CPF, String CRM, String principal) {

		super(nome, senha, CPF);

		this.CRM = CRM;
		this.pacientes = new ArrayList<>();
		this.CPFs_pacientes = new ArrayList<>();
		this.nomesEspecialidade = new String[MAX_QUANTIDADE_NOMES_ESPECIALIDADE];
		this.setNomeEspecialidadePrincipal(principal);

		MedicoDAO.insertDoctor(this);
	}

	public Medico(String nome, String senha, String CPF, String CRM, String principal, String subespecialidade,
			String horaDisponivelConsulta) {

		super(nome, senha, CPF);

		this.CRM = CRM;
		this.pacientes = new ArrayList<>();
		this.CPFs_pacientes = new ArrayList<>();
		this.nomesEspecialidade = new String[MAX_QUANTIDADE_NOMES_ESPECIALIDADE];
		this.setNomeEspecialidadePrincipal(principal);
		this.setNomeSubEspecialidade(subespecialidade);
		this.horaDisponivelConsulta = horaDisponivelConsulta;

		MedicoDAO.insertDoctor(this);
	}

	public List<Paciente> getPacientes() {

		this.CPFs_pacientes.forEach(CPF_Paciente -> PacienteDAO.findByCPF(CPF_Paciente));

		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<String> getCPFs_pacientes() {
		return CPFs_pacientes;
	}

	public void setCPFs_pacientes(List<String> CPFs_pacientes) {
		this.CPFs_pacientes = CPFs_pacientes;
	}

	public void setCPF_Paciente(String CPF_paciente) {
		this.CPFs_pacientes.add(CPF_paciente);
	}

	public String getCRM() {
		return CRM;
	}

	public void setNomeEspecialidadePrincipal(String nomeEspecialidadePrincipal) {
		this.nomesEspecialidade[0] = nomeEspecialidadePrincipal;
	}

	public void setNomeSubEspecialidade(String nomeSubEspecialidade) {
		this.nomesEspecialidade[1] = nomeSubEspecialidade;
	}

	public Especialidade getEspecialidadePrincipal() {

		return EspecialidadeDAO.findByName(nomesEspecialidade[0]);
	}

	public Especialidade getSubEspecialidade() {
		return EspecialidadeDAO.findByName(nomesEspecialidade[1]);
	}

	public String getHoraDisponivelConsulta() {
		return horaDisponivelConsulta;
	}

	public void setHoraDisponivelConsulta(String horaDisponivelConsulta) {
		this.horaDisponivelConsulta = horaDisponivelConsulta;
	}

	@Override
	public Usuario login(String cpf, String senha) {
		// TODO Auto-generated method stub
		return MedicoDAO.login(cpf, senha);
	}

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
