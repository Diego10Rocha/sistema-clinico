package model;

public class Consulta {

	private String data;
	private String hora;
	private Medico medico;
	private Paciente paciente;
	private boolean isMarcada;
	private boolean isRealizada;

	public Consulta() {
	};

	public Consulta(String data, String hora, Medico medico) {

		this.data = data;
		this.hora = hora;
		this.medico = medico;

	}

	public Consulta(String data, String hora, Medico medico, Paciente paciente) {

		this.data = data;
		this.hora = hora;
		this.medico = medico;
		this.paciente = paciente;

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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public boolean isMarcada() {
		return isMarcada;
	}

	public void setMarcada(boolean isMarcada) {
		this.isMarcada = isMarcada;
	}

	public boolean isRealizada() {
		return isRealizada;
	}

	public void setRealizada(boolean isRealizada) {
		this.isRealizada = isRealizada;
	}

	@Override
	public String toString() {
		
		return "Data: " + this.getData() + "\n" +
			   "Hora: " + this.getHora() + "\n" + 
			   "Médico: " + this.getMedico().toString();
	}
	
	

}
