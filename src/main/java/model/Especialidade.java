package model;

import dao.EspecialidadeDAO;

public class Especialidade {
	
	private String nome;
	private boolean principal;//true - principal; false - subespecialidade
	
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

}
