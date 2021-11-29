package model;

import dao.EspecialidadeDAO;

public class Especialidade {

	private int id;
	private String nome;
	private boolean principal;// true - principal; false - subespecialidade

	public Especialidade() {

	}

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

	@Override
	public String toString() {
		return "Nome: " + this.getNome();
	}

}
