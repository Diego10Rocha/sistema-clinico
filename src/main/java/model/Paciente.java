package model;

import dao.PacienteDAO;

public class Paciente extends Usuario {

	private String DATA_NASCIMENTO;
	private Prontuario prontuario;

	public Paciente(String nome, String senha, String CPF, String DATA_NASCIMENTO) {

		super(nome, senha, CPF);

		this.DATA_NASCIMENTO = DATA_NASCIMENTO;
		this.prontuario = new Prontuario(CPF);
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

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}


	@Override
	public Usuario login(String cpf, String senha) {
		// TODO Auto-generated method stub
		return PacienteDAO.login(cpf, senha);
	}
}
