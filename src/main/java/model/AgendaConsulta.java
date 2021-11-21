package model;

public class AgendaConsulta {

	private String data;
	private String hora;
	private String nomeMedico;

	public AgendaConsulta() {

	}

	public AgendaConsulta(String data, String hora, String nomeMedico) {
		this.data = data;
		this.hora = hora;
		this.nomeMedico = nomeMedico;
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

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	@Override
	public boolean equals(Object obj) {

		boolean objIsEqual = false;

		if (obj instanceof AgendaConsulta) {

			AgendaConsulta agendaConsultaASerComparada = (AgendaConsulta) obj;

			if (agendaConsultaASerComparada.getData().equals(this.getData())
					&& agendaConsultaASerComparada.getHora().equals(this.getHora())
					&& agendaConsultaASerComparada.getNomeMedico().equals(this.getNomeMedico())) {

				objIsEqual = true;
			}

		}

		return objIsEqual;
	}

	@Override
	public String toString() {

		return "Data: " + this.getData() + "\n" + "Hora: " + this.getHora() + "\n" + "Médico: "
				+ this.getNomeMedico();
	}

}
