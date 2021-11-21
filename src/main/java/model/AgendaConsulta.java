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
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@Override
	public boolean equals(Object obj) {

		boolean objIsEqual = false;

		if (obj instanceof AgendaConsulta) {

			AgendaConsulta agendaConsultaASerComparada = (AgendaConsulta) obj;

			if (agendaConsultaASerComparada.getData().equals(this.getData())
					&& agendaConsultaASerComparada.getHora().equals(this.getHora())
					&& agendaConsultaASerComparada.getMedico().equals(this.getMedico())) {

				objIsEqual = true;
			}

		}

		return objIsEqual;
	}

	@Override
	public String toString() {

		return "Data: " + this.getData() + "\n" + "Hora: " + this.getHora() + "\n" + "Médico: "
				+ this.getMedico().getNome() + "\n"
				+ "Especialidade: " + this.getMedico().getEspecialidadePrincipal().getNome() ;
	}

}
