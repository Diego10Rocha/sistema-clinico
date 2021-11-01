package model;

public class Recepcionista extends Usuario{

	public Recepcionista(String nome, String CPF) {
		
		super(nome, CPF);
		
	}
	
	public Recepcionista(String nome, String CPF, String senha) {
		
		super(nome, CPF);
		
		this.setSenha(senha);
		
	}

}
