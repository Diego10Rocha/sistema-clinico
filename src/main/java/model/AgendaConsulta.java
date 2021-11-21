package model;

public class AgendaConsulta {

	private String data;
	private String hora;
	private Medico medico;

	public AgendaConsulta() {

	}

	public AgendaConsulta(String data, String hora, Medico medico) {
		this.data = data;
		this.hora = hora;
		this.medico = medico;
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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@Override
	public String toString() {

		return "Data: " + this.getData() + "\n" + "Hora: " + this.getHora() + "\n" + "Médico: "
				+ this.getMedico().toString();
	}

}
