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

package model;

import java.util.ArrayList;
import java.util.List;

import dao.AgendaConsultaDAO;
import dao.ConsultaDAO;
import date.MyDate;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class GerenciadorConsulta {

	/**
	 * Verifica se uma consulta está marcada para algum paciente
	 * @param CPF_Target
	 * @return boolean
	 */
	public static boolean hasConsultaMarcada(String CPF_Target) {

		List<Consulta> consultasCadastradas = ConsultaDAO.getConsultas();

		boolean foundAssociation = consultasCadastradas.stream()
				.anyMatch(consulta -> consulta.getCPF_medico().equals(CPF_Target)
						|| consulta.getCPF_paciente().equals(CPF_Target));

		return foundAssociation;

	}

	/**
	 * Verifica se uma consulta já foi realizada
	 * @param CPF_Target
	 * @return boolean
	 */
	public static boolean hasConsultaRealizada(String CPF_Target) {

		List<Consulta> consultasCadastradas = ConsultaDAO.getConsultas();

		boolean foundAssociation = consultasCadastradas.stream()
				.anyMatch(consulta -> (consulta.getCPF_medico().equals(CPF_Target)
						|| consulta.getCPF_paciente().equals(CPF_Target)) && consulta.isRealizada());

		return foundAssociation;

	}

	/**
	 * Metodo que remove todas as consultas de um medico
	 * @param CPF_Target
	 * @throws Exception
	 */
	public static void removeAllConsultaContainsCPF(String CPF_Target) throws Exception {

		List<Consulta> consultasCadastradas = ConsultaDAO.getConsultas();

		for (Consulta consultaCadastrada : consultasCadastradas) {

			if (consultaCadastrada.getCPF_medico().equals(CPF_Target)
					|| consultaCadastrada.getCPF_paciente().equals(CPF_Target)) {

				ConsultaDAO.deleteConsulta(consultaCadastrada);
			}
		}

	}

	/**
	 * Metodo que remove todas as agendas de um medico
	 * @param CPF_Target
	 * @throws Exception
	 */
	public static void removeAllConsultaAgendaContainsCPF(String CPF_Target) throws Exception {

		List<AgendaConsulta> agendasCadastradas = AgendaConsultaDAO.getAgendasConsulta();

		for (AgendaConsulta agendaCadastrada : agendasCadastradas) {

			if (agendaCadastrada.getCPF_medico().equals(CPF_Target)) {

				AgendaConsultaDAO.deleteAgendaConsulta(agendaCadastrada);
			}
		}

	}

	/**
	 * Retorna todas as consultas marcadas de um medico
	 * @param CPF_MEDICO
	 * @return List<Consulta>
	 */
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

	/**
	 * Verifica se uma consulta ainda não foi realizada
	 * @param consultaCadastrada
	 * @return boolean
	 */
	private static boolean isConsultaNotRealizada(Consulta consultaCadastrada) {

		return !consultaCadastrada.isRealizada();
	}

	/**
	 * Verifica se uma consulta possui um cpf associado, seja ele de um médico ou de um paciente
	 * @param consultaTarget
	 * @param CPF_Target
	 * @return boolean
	 */
	private static boolean consultaContainsCPF(Consulta consultaTarget, String CPF_Target) {

		String cpfMedicoConsulta = consultaTarget.getCPF_medico();
		String cpfPacienteConsulta = consultaTarget.getCPF_paciente();

		return cpfMedicoConsulta.equals(CPF_Target) || cpfPacienteConsulta.equals(CPF_Target);
	}

	/**
	 * Retornas as consultas do dia de um medico
	 * @param CPF_MEDICO
	 * @return List<Consulta>
	 */
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

	/**
	 * Verifica se uma consulta foi marcada no dia
	 * @param consultaCadastrada
	 * @return boolean
	 */
	private static boolean isConsultaMarcadaHoje(Consulta consultaCadastrada) {

		String dataAtual = new MyDate().getCurrentDate();
		String dataMarcadaConsulta = consultaCadastrada.getData();

		return dataAtual.equals(dataMarcadaConsulta);
	}

	/**
	 * Retorna o histórico de consultas de um paciente
	 * @param CPF_Paciente
	 * @return List<HistoricoConsulta>
	 */
	public static List<HistoricoConsulta> getHistoryConsultasPaciente(String CPF_Paciente) {

		List<Consulta> consultasCadastradas = ConsultaDAO.getConsultas();
		List<HistoricoConsulta> historyConsultasPaciente = new ArrayList<>();

		HistoricoConsulta historicoConsulta;

		for (Consulta consultaCadastrada : consultasCadastradas) {

			if (isConsultaRealizada(consultaCadastrada) && consultaContainsCPF(consultaCadastrada, CPF_Paciente)) {

				historicoConsulta = new HistoricoConsulta(consultaCadastrada);

				historyConsultasPaciente.add(historicoConsulta);
			}
		}

		return historyConsultasPaciente;

	}

	/**
	 * Verifica se uma consulta já foi realizada
	 * @param consultaCadastrada
	 * @return boolean
	 */
	private static boolean isConsultaRealizada(Consulta consultaCadastrada) {

		return consultaCadastrada.isRealizada();
	}

}
