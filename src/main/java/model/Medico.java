package model;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Usuario {

	private final String CRM;
	private List<Paciente> pacientes;

	public Medico(String nome, String senha, String CPF, String CRM) {

		super(nome, senha, CPF);

		this.CRM = CRM;
		this.pacientes = new ArrayList<>();
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

}
