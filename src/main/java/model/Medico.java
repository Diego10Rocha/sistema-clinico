package model;

import java.util.ArrayList;
import java.util.List;

import dao.MedicoDAO;

public class Medico extends Usuario {

	private final String CRM;
	private List<Paciente> pacientes;
	private Especialidade[] especialidades;//indice 0 - Especialidade principal; indice 1 - subespecialidade;

	public Medico(String nome, String senha, String CPF, String CRM, Especialidade principal) {

		super(nome, senha, CPF);

		this.CRM = CRM;
		this.pacientes = new ArrayList<>();
		this.setEspecialidades(new Especialidade[2]);
		this.setEspecialidadePrincipal(principal);
		MedicoDAO.insertDoctor(this);
	}
	
	public Medico(String nome, String senha, String CPF, String CRM, 
			Especialidade principal, Especialidade subespecialidade) {

		super(nome, senha, CPF);

		this.CRM = CRM;
		this.pacientes = new ArrayList<>();
		this.setEspecialidades(new Especialidade[2]);
		this.setEspecialidadePrincipal(principal);
		this.setSubEspecialidade(subespecialidade);
		MedicoDAO.insertDoctor(this);
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public String getCRM() {
		return CRM;
	}
	
	public Especialidade[] getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Especialidade[] especialidades) {
		this.especialidades = especialidades;
	}

	public Especialidade getEspecialidadePrincipal() {
		return especialidades[0];
	}

	public void setEspecialidadePrincipal(Especialidade especialidades) {
		this.especialidades[0] = especialidades;
	}
	
	public Especialidade getSubEspecialidade() {
		return especialidades[1];
	}

	public void setSubEspecialidade(Especialidade especialidades) {
		this.especialidades[1] = especialidades;
	}
	
	
	@Override
	public boolean equals(Object obj) {

		boolean objIsEqual = false;

		if (obj instanceof Paciente) {

			Medico medicoASerComparado = (Medico) obj;

			String cpfMedicoASerComparado = medicoASerComparado.getCPF();
			;

			if (cpfMedicoASerComparado.equals(this.getCPF()))
				objIsEqual = true;

		}

		return objIsEqual;
	}

	@Override
	public Usuario login(String cpf, String senha) {
		// TODO Auto-generated method stub
		return MedicoDAO.login(cpf, senha);
	}

}
