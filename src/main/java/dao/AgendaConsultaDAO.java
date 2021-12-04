package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import file.ConnectionFile;
import model.AgendaConsulta;

public class AgendaConsultaDAO {

	private static ConnectionFile<AgendaConsulta> connectionFile = new ConnectionFile<>(AgendaConsulta[].class,
			"Arquivos/AgendaConsulta.json");

	public static List<AgendaConsulta> getAgendasConsulta() {

		List<AgendaConsulta> agendasConsulta = connectionFile.readFile();

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

	public static boolean updateAgenda(AgendaConsulta oldAgendaConsulta, AgendaConsulta newAgenda) {

		boolean isNewAlreadyRegistered = agendaConsultaAlreadyRegistered(newAgenda);

		if (isNewAlreadyRegistered)

			return false;

		else {

			List<AgendaConsulta> agendasConsulta = getAgendasConsulta();

			boolean isOldAgendaRegistered = isCPF_MedicoAgendaAlreadyRegistered(newAgenda.getCPF_medico());
			
			if (isOldAgendaRegistered) {

				agendasConsulta.remove(oldAgendaConsulta);

				agendasConsulta.add(newAgenda);

				connectionFile.reWriter(agendasConsulta);

				return true;
			}

			return false;
		}
	}

	public static List<AgendaConsulta> findByCPF_Medico(String CPF_medico) {

		List<AgendaConsulta> agendasConsulta = getAgendasConsulta();

		agendasConsulta = agendasConsulta.stream()
				.filter(agenda -> agenda.getCPF_medico().equals(CPF_medico)).toList();
		ArrayList<AgendaConsulta> agendas = new ArrayList<>(agendasConsulta);
		return agendas;
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

		connectionFile.reWriter(agendasConsulta);

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
