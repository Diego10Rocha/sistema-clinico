package model;

public class Paciente extends Usuario {

	private String DATA_NASCIMENTO;
	private Prontuario prontuario;

	public Paciente(String nome, String senha, String CPF, String DATA_NASCIMENTO) {

		super(nome, senha, CPF);

		this.DATA_NASCIMENTO = DATA_NASCIMENTO;
		this.prontuario = new Prontuario(CPF);
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
	public boolean equals(Object obj) {

		boolean objIsEqual = false;

		if (obj instanceof Paciente) {

			Paciente pacienteASerComparado = (Paciente) obj;

			String cpfPacienteASerComparado = pacienteASerComparado.getCPF();
			;

			if (cpfPacienteASerComparado.equals(this.getCPF()))
				objIsEqual = true;

		}

		return objIsEqual;
	}
}
