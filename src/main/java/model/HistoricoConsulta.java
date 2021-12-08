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

import dao.MedicoDAO;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class HistoricoConsulta {

	private Consulta consulta;

	/**
	 * Construtor da classe
	 * @param consulta
	 */
	public HistoricoConsulta(Consulta consulta) {

		this.consulta = consulta;
	}

	public Consulta getConsulta() {

		return consulta;
	}

	public void setConsulta(Consulta consulta) {

		this.consulta = consulta;
	}

	/**
	 * Retorna dados de um objeto da classe em uma String
	 * @return String
	 */
	@Override
	public String toString() {

		Medico medicoQueRealizouConsulta = MedicoDAO.findByCPF(consulta.getCPF_medico());

		String nomeMedicoQueRealizouConsulta = medicoQueRealizouConsulta.getNome();
		String dataConsulta = consulta.getData();
		String horaConsulta = consulta.getHora();

		return "Data: " + dataConsulta + "\n" + "Hora: " + horaConsulta + "\n" + "Médico(a): "
				+ nomeMedicoQueRealizouConsulta;
	}

}
