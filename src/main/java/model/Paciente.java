package model;

import java.util.ArrayList;
import java.util.List;

import dao.PacienteDAO;

public class Paciente extends Usuario {

	private String DATA_NASCIMENTO;
	private List<Prontuario> prontuarios;

	public Paciente() {

	}

	public Paciente(String nome, String CPF, String DATA_NASCIMENTO) {

		super(nome, CPF);

		this.DATA_NASCIMENTO = DATA_NASCIMENTO;
		this.prontuarios = new ArrayList<>();

		PacienteDAO.insertPatient(this);
	}

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

	public void setProntuario(Prontuario prontuario) {
		
		this.prontuarios.add(prontuario);
	}

	@Override
	public Usuario login(String cpf, String senha) {
		// TODO Auto-generated method stub
		return PacienteDAO.login(cpf, senha);
	}

	@Override
	public String toString() {
		return "Nome: " + this.getNome() + "\n" + "Data de Nascimento: " + this.getDATA_NASCIMENTO() + "\n" + "CPF: "
				+ this.getCPF();
	}

}
