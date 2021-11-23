package model;

import java.util.List;

import dao.ConsultaDAO;

public class GerenciadorConsulta {

	public static boolean hasConsultaMarcada(String CPF_Target) {

		List<Consulta> consultasCadastradas = ConsultaDAO.getConsultas();

		boolean foundAssociation = consultasCadastradas.stream()
				.anyMatch(consulta -> consulta.getCPF_medico().equals(CPF_Target)
						|| consulta.getCPF_paciente().equals(CPF_Target));

		return foundAssociation;

	}
	
	public static boolean hasConsultaRealizada(String CPF_Target) {

		List<Consulta> consultasCadastradas = ConsultaDAO.getConsultas();

		boolean foundAssociation = consultasCadastradas.stream()
				.anyMatch(consulta -> (consulta.getCPF_medico().equals(CPF_Target)
						|| consulta.getCPF_paciente().equals(CPF_Target)) && consulta.isRealizada());

		return foundAssociation;

	}

	public static void removeAllConsultaContainsCPF(String CPF_Target) throws Exception {

		List<Consulta> consultasCadastradas = ConsultaDAO.getConsultas();

		for(Consulta consultaCadastrada: consultasCadastradas) {
			
			if( consultaCadastrada.getCPF_medico().equals(CPF_Target)
				|| consultaCadastrada.getCPF_paciente().equals(CPF_Target)) {
				
				ConsultaDAO.deleteConsulta(consultaCadastrada);
			}
		}
		


	}
}
