package model;

import dao.MedicoDAO;

public class HistoricoConsulta {

	private Consulta consulta;

	public HistoricoConsulta(Consulta consulta) {

		this.consulta = consulta;
	}

	public Consulta getConsulta() {

		return consulta;
	}

	public void setConsulta(Consulta consulta) {

		this.consulta = consulta;
	}

	@Override
	public String toString() {

		Medico medicoQueRealizouConsulta = MedicoDAO.findByCPF(consulta.getCPF_medico());

		String nomeMedicoQueRealizouConsulta = medicoQueRealizouConsulta.getNome();
		String dataConsulta = consulta.getData();
		String horaConsulta = consulta.getHora();

		return "Data: " + dataConsulta + "/n" + "Hora: " + horaConsulta + "/n" + "Médico(a): "
				+ nomeMedicoQueRealizouConsulta;
	}

}
