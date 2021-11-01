package model;

public class Usuario {

	private String nome;
	private String senha;
	private String CPF;

	public Usuario(String nome, String senha, String CPF) {

		this.nome = nome;
		this.senha = senha;
		this.CPF = CPF;
	}

	
	public Usuario(String nome, String CPF) {
		
		this.nome = nome;
		this.CPF = CPF;
		
	}
	
	public boolean login(String nome, String senha) {return true;}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCPF() {
		return CPF;
	}
	
	
	public void setCPF(String cPF) {
		CPF = cPF;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", senha=" + senha + ", CPF=" + CPF + "]";
	}
	
	

}
