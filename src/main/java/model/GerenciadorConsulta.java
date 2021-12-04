package model;

import java.util.ArrayList;
import java.util.List;

import dao.AgendaConsultaDAO;
import dao.ConsultaDAO;
import date.MyDate;

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

		for (Consulta consultaCadastrada : consultasCadastradas) {

			if (consultaCadastrada.getCPF_medico().equals(CPF_Target)
					|| consultaCadastrada.getCPF_paciente().equals(CPF_Target)) {

				ConsultaDAO.deleteConsulta(consultaCadastrada);
			}
		}

	}

	public static void removeAllConsultaAgendaContainsCPF(String CPF_Target) throws Exception {

		List<AgendaConsulta> agendasCadastradas = AgendaConsultaDAO.getAgendasConsulta();

		for (AgendaConsulta agendaCadastrada : agendasCadastradas) {

			if (agendaCadastrada.getCPF_medico().equals(CPF_Target)) {

				AgendaConsultaDAO.deleteAgendaConsulta(agendaCadastrada);
			}
		}

	}

	public static List<Consulta> getHistoryConsultasPaciente(String CPF_Target) {

		List<Consulta> consultasCadastradas = ConsultaDAO.getConsultas();
		List<Consulta> historyConsultasPaciente = new ArrayList<>();

		for (Consulta consultaCadastrada : consultasCadastradas) {

			if (consultaCadastrada.getCPF_paciente().equals(CPF_Target)) {

				historyConsultasPaciente.add(consultaCadastrada);
			}
		}

		return historyConsultasPaciente;

	}

	public static List<Consulta> getAllConsultasMarcadasByCPF_Medico(String CPF_MEDICO) {

		List<Consulta> allConsultasMarcadas = new ArrayList<>();
		List<Consulta> consultasCadastradas = ConsultaDAO.getConsultas();

		for (Consulta consultaCadastrada : consultasCadastradas) {

			if (isConsultaNotRealizada(consultaCadastrada) && consultaContainsCPF(consultaCadastrada, CPF_MEDICO)) {

				allConsultasMarcadas.add(consultaCadastrada);
			}
		}

		return allConsultasMarcadas;
	}

	private static boolean isConsultaNotRealizada(Consulta consultaCadastrada) {

		return !consultaCadastrada.isRealizada();
	}

	private static boolean consultaContainsCPF(Consulta consultaTarget, String CPF_Target) {

		String cpfMedicoConsulta = consultaTarget.getCPF_medico();
		String cpfPacienteConsulta = consultaTarget.getCPF_paciente();

		return cpfMedicoConsulta.equals(CPF_Target) || cpfPacienteConsulta.equals(CPF_Target);
	}

	public static List<Consulta> getConsultasMarcadasHojeByCPF_Medico(String CPF_MEDICO) {

		List<Consulta> allConsultasMarcadas = new ArrayList<>();
		List<Consulta> consultasCadastradas = ConsultaDAO.getConsultas();

		for (Consulta consultaCadastrada : consultasCadastradas) {

			if (isConsultaNotRealizada(consultaCadastrada) && consultaContainsCPF(consultaCadastrada, CPF_MEDICO)
					&& isConsultaMarcadaHoje(consultaCadastrada)) {

				allConsultasMarcadas.add(consultaCadastrada);
			}
		}

		return allConsultasMarcadas;
	}

	private static boolean isConsultaMarcadaHoje(Consulta consultaCadastrada) {

		String dataAtual = new MyDate().getCurrentDate();
		String dataMarcadaConsulta = consultaCadastrada.getData();

		return dataAtual.equals(dataMarcadaConsulta);
	}

}
