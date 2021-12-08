/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programação
Concluido em: 07/12/2021
Declaro que este código foi elaborado por Diego Cerqueira e Joanderson Santos em dupla e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import file.ConnectionFile;
import model.AgendaConsulta;

/**
 * Classe DAO para persistência em arquivo de Agendas de consultas
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class AgendaConsultaDAO {

	private static ConnectionFile<AgendaConsulta> connectionFile = new ConnectionFile<>(AgendaConsulta[].class,
			"Arquivos/AgendaConsulta.json");

	/**
	 * Retorna uma lista com as agendas de consultas
	 * @return List<AgendaConsulta>
	 */
	public static List<AgendaConsulta> getAgendasConsulta() {

		List<AgendaConsulta> agendasConsulta = connectionFile.readFile();

		return agendasConsulta;
	}

	/**
	 * Metodo que cadastra uma agenda de consulta
	 * @param agendaConsulta
	 * @return boolean
	 */
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

	/**
	 * Metodo que atualiza uma agenda de consulta
	 * @param oldAgendaConsulta
	 * @param newAgenda
	 * @return boolean
	 */
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

	
	/**
	 * Metodo que Busca as consultas de um determinado médico
	 * @param CPF_medico
	 * @return List<AgendaConsulta>
	 */
	public static List<AgendaConsulta> findByCPF_Medico(String CPF_medico) {

		List<AgendaConsulta> agendasConsulta = getAgendasConsulta();

		agendasConsulta = agendasConsulta.stream()
				.filter(agenda -> agenda.getCPF_medico().equals(CPF_medico)).toList();
		ArrayList<AgendaConsulta> agendas = new ArrayList<>(agendasConsulta);
		return agendas;
	}

	/**
	 * Metodo que verifica se um cpf já está registrado como médico
	 * @param CPF_Medico_Target
	 * @return boolean
	 */
	private static boolean isCPF_MedicoAgendaAlreadyRegistered(String CPF_Medico_Target) {

		List<AgendaConsulta> agendasConsulta = getAgendasConsulta();

		return agendasConsulta.stream().filter(agenda -> agenda.getCPF_medico().equals(CPF_Medico_Target)).findFirst()
				.isPresent();

	}

	/**
	 * Metodo para apagar uma agenda
	 * @param agendaConsulta
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean deleteAgendaConsulta(AgendaConsulta agendaConsulta) throws Exception {

		boolean isDeletada;

		List<AgendaConsulta> agendasConsulta = getAgendasConsulta();

		isDeletada = agendasConsulta.remove(agendaConsulta);

		connectionFile.reWriter(agendasConsulta);

		return isDeletada;
	}

	/**
	 * Metodo que verifica se uma agenda já está registrada
	 * @param agendaConsulta
	 * @return boolean
	 */
	public static boolean agendaConsultaAlreadyRegistered(AgendaConsulta agendaConsulta) {

		boolean agendaAlreadyRegistered = false;

		Optional<AgendaConsulta> temp = findAgenda(agendaConsulta);

		if (temp.isPresent()) {

			agendaAlreadyRegistered = true;
		}

		return agendaAlreadyRegistered;

	}

	/**
	 * Busca uma agenda
	 * @param agendaTarget
	 * @return Optional<AgendaConsulta>
	 */
	private static Optional<AgendaConsulta> findAgenda(AgendaConsulta agendaTarget) {

		List<AgendaConsulta> agendasConsulta = getAgendasConsulta();

		return agendasConsulta.stream().filter(agenda -> agenda.equals(agendaTarget)).findFirst();
	}

}
