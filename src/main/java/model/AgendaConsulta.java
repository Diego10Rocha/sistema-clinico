package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dao.MedicoDAO;

public class AgendaConsulta implements Comparable<AgendaConsulta>{

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		if (LocalDate.parse(this.data, formatter).isBefore(LocalDate.parse(compare.getData(), formatter))) { 
			return -1;
		} else if (LocalDate.parse(this.data, formatter).isAfter(LocalDate.parse(compare.getData(), formatter))) { 
			return 1; 
		} else {
			if (Integer.parseInt(this.hora.substring(0, 2)) < Integer.parseInt(compare.getHora().substring(0, 2))) { 
				return -1;
			} else if (Integer.parseInt(this.hora.substring(0, 2)) > Integer.parseInt(compare.getHora().substring(0, 2))) { 
				return 1; 
			} else {
				if (Integer.parseInt(this.hora.substring(3, 5)) < Integer.parseInt(compare.getHora().substring(3, 5))) { 
					return -1;
				} else if (Integer.parseInt(this.hora.substring(0, 2)) > Integer.parseInt(compare.getHora().substring(3, 5))) { 
					return 1; 
				}
			}
			return 0; 
		}
		
	}

}
