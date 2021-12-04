package model;

import dao.PacienteDAO;

public class Consulta implements Comparable<Consulta>{

	private String data;
	private String hora;
	private final String CPF_medico;
	private final String CPF_paciente;
	private boolean isRealizada;

	public Consulta(String data, String hora, String CPF_medico, String CPF_paciente) {

		this.data = data;
		this.hora = hora;
		this.CPF_medico = CPF_medico;
		this.CPF_paciente = CPF_paciente;

	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public boolean isRealizada() {
		return isRealizada;
	}

	public void setRealizada(boolean isRealizada) {
		this.isRealizada = isRealizada;
	}

	public String getCPF_medico() {
		return CPF_medico;
	}

	public String getCPF_paciente() {
		return CPF_paciente;
	}

	@Override
	public String toString() {

		String nomePaciente = PacienteDAO.findByCPF(CPF_paciente).getNome();

		return "Data: " + this.getData() + "\n" + "Hora: " + this.getHora() + "\n" + "Paciente: " + nomePaciente;
	}

	@Override
	public boolean equals(Object obj) {

		boolean objIsEqual = false;

		if (obj instanceof Consulta) {

			Consulta consultaASerComparada = (Consulta) obj;

			if (consultaASerComparada.getData().equals(this.getData())
					&& consultaASerComparada.getHora().equals(this.getHora())
					&& consultaASerComparada.getCPF_medico().equals(this.getCPF_medico())
					&& consultaASerComparada.getCPF_paciente().equals(this.getCPF_paciente())) {

				objIsEqual = true;
			}

		}

		return objIsEqual;
	}

	@Override
	public int compareTo(Consulta compare) {

		return this.getHora().compareTo(compare.getHora());
	}

}
