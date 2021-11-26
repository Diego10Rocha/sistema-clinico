package dao;

import java.util.List;
import java.util.Optional;

import file.ConnectionFile;
import model.AgendaConsulta;

public class AgendaConsultaDAO {

	private static final String PATH_CADASTROS_AGENDA_CONSULTA = "Arquivos/AgendaConsulta.json";
	private static ConnectionFile connectionFile = new ConnectionFile();

	public static List<AgendaConsulta> getAgendasConsulta() {

		List<AgendaConsulta> agendasConsulta = connectionFile.readFile(PATH_CADASTROS_AGENDA_CONSULTA,
				AgendaConsulta[].class);

		return agendasConsulta;
	}

	public static boolean insertAgendaConsulta(AgendaConsulta agendaConsulta) {

		try {

			if (agendaConsultaAlreadyRegistered(agendaConsulta)) {

				throw new Exception("Agenda já cadastrada");
			}

			connectionFile.writer(agendaConsulta);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return false;

	}

	public static boolean updateAgenda(AgendaConsulta newAgenda) {

		boolean isNewAlreadyRegistered = agendaConsultaAlreadyRegistered(newAgenda);

		if (isNewAlreadyRegistered)

			return false;

		else {

			List<AgendaConsulta> agendasConsulta = getAgendasConsulta();

			boolean isOldAgendaRegistered = isCPF_MedicoAgendaAlreadyRegistered(newAgenda.getCPF_medico());

			if (isOldAgendaRegistered) {

				AgendaConsulta oldAgendaConsulta = findByCPF_Medico(newAgenda.getCPF_medico());

				agendasConsulta.remove(oldAgendaConsulta);

				agendasConsulta.add(newAgenda);

				connectionFile.reWriter(agendasConsulta, PATH_CADASTROS_AGENDA_CONSULTA);

				return true;
			}

			return false;
		}
	}

	private static AgendaConsulta findByCPF_Medico(String CPF_medico) {

		List<AgendaConsulta> agendasConsulta = getAgendasConsulta();

		Optional<AgendaConsulta> agendaFound = agendasConsulta.stream()
				.filter(agenda -> agenda.getCPF_medico().equals(CPF_medico)).findFirst();

		return agendaFound.isPresent() ? agendaFound.get() : null;
	}

	private static boolean isCPF_MedicoAgendaAlreadyRegistered(String CPF_Medico_Target) {

		List<AgendaConsulta> agendasConsulta = getAgendasConsulta();

		return agendasConsulta.stream().filter(agenda -> agenda.getCPF_medico().equals(CPF_Medico_Target)).findFirst()
				.isPresent();

	}

	public static boolean deleteAgendaConsulta(AgendaConsulta agendaConsulta) throws Exception {

		boolean isDeletada;

		List<AgendaConsulta> agendasConsulta = getAgendasConsulta();

		isDeletada = agendasConsulta.remove(agendaConsulta);

		connectionFile.reWriter(agendasConsulta, PATH_CADASTROS_AGENDA_CONSULTA);

		return isDeletada;
	}

	public static boolean agendaConsultaAlreadyRegistered(AgendaConsulta agendaConsulta) {

		boolean agendaAlreadyRegistered = false;

		Optional<AgendaConsulta> temp = findAgenda(agendaConsulta);

		if (temp.isPresent()) {

			agendaAlreadyRegistered = true;
		}

		return agendaAlreadyRegistered;

	}

	private static Optional<AgendaConsulta> findAgenda(AgendaConsulta agendaTarget) {

		List<AgendaConsulta> agendasConsulta = getAgendasConsulta();

		return agendasConsulta.stream().filter(agenda -> agenda.equals(agendaTarget)).findFirst();
	}

}
