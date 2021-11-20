package model;

import dao.EspecialidadeDAO;

public class Especialidade {

	private String nome;
	private boolean principal;// true - principal; false - subespecialidade

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

	@Override
	public boolean equals(Object obj) {

		boolean objIsEqual = false;

		if (obj instanceof Especialidade) {

			Especialidade especialidadeASerComparada = (Especialidade) obj;

			String nomeEspecialidadeASerComparada = especialidadeASerComparada.getNome();
			boolean principalEspecialidadeASerComparada = especialidadeASerComparada.isPrincipal();

			if (nomeEspecialidadeASerComparada.equals(this.getNome())
					&& principalEspecialidadeASerComparada == this.isPrincipal())
				
				objIsEqual = true;

		}

		return objIsEqual;
	}

	@Override
	public String toString() {
		return "Nome: " + this.getNome();
	}
	
	

}
