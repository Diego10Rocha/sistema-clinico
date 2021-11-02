package model;

import dao.RecepcionistaDAO;

public class Recepcionista extends Usuario{

	public Recepcionista(String nome, String CPF) {
		
		super(nome, CPF);
		
	}
	
	public Recepcionista(String nome, String CPF, String senha) {
		
		super(nome, CPF);
		
		this.setSenha(senha);
		
	}

	@Override
	public Usuario login(String cpf, String senha) {
		// TODO Auto-generated method stub
		return RecepcionistaDAO.login(cpf, senha);
	}

}
