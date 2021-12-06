package model;

import dao.MedicoDAO;
import date.MyDate;

public class AgendaConsulta implements Comparable<AgendaConsulta> {

	private String data;
	private String hora;
	private final String CPF_medico;

	public AgendaConsulta(String data, String hora, String CPF_medico) {

		this.data = data;
		this.hora = hora;
		this.CPF_medico = CPF_medico;
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

	public String getCPF_medico() {
		return CPF_medico;
	}

	@Override
	public boolean equals(Object obj) {

		boolean objIsEqual = false;

		if (obj instanceof AgendaConsulta) {

			AgendaConsulta agendaConsultaASerComparada = (AgendaConsulta) obj;

			if (agendaConsultaASerComparada.getData().equals(this.getData())
					&& agendaConsultaASerComparada.getHora().equals(this.getHora())
					&& agendaConsultaASerComparada.getCPF_medico().equals(this.getCPF_medico())) {
				objIsEqual = true;
			}

		}

		return objIsEqual;
	}

	@Override
	public String toString() {

		Medico medico = MedicoDAO.findByCPF(CPF_medico);

		return "Data: " + this.getData() + "\n" + "Hora: " + this.getHora() + "\n" + "Médico: " + medico.getNome()
				+ "\n" + "Especialidade: " + medico.getEspecialidadePrincipal().getNome();
	}

	@Override
	public int compareTo(AgendaConsulta compare) {

		MyDate myDate = new MyDate();

		int resultCompareToDate = myDate.compareTo(this.getData(), compare.getData());

		if (resultCompareToDate != 0)

			return resultCompareToDate;

		else {

			int resultCompareToTime = this.getHora().compareTo(compare.getHora());

			if (resultCompareToTime != 0)

				return resultCompareToTime;
		}

		return 0;

	}

}
