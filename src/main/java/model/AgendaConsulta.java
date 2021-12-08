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
import date.MyDate;
import date.MyDateTime;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class AgendaConsulta implements Comparable<AgendaConsulta> {

	private String data;
	private String hora;
	private boolean isMarcada;
	private final String CPF_medico;

	/**
	 * Construtor da classe
	 * 
	 * @param data
	 * @param hora
	 * @param CPF_medico
	 */
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

	/**
	 * Sobrescrita do metodo equals da classe <b>Object</>
	 * 
	 * @param obj objeto a ser comparado
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {

		boolean objIsEqual = false;

		if (obj instanceof AgendaConsulta) {

			AgendaConsulta agendaConsultaASerComparada = (AgendaConsulta) obj;

			if (agendaConsultaASerComparada.getData().equals(this.getData())
					&& agendaConsultaASerComparada.getHora().equals(this.getHora())
					&& agendaConsultaASerComparada.getCPF_medico().equals(this.getCPF_medico())
					&& agendaConsultaASerComparada.isMarcada() == this.isMarcada()) {
				objIsEqual = true;
			}

		}

		return objIsEqual;
	}

	/**
	 * Retorna dados de um objeto da classe em String
	 * 
	 * @return String
	 */
	@Override
	public String toString() {

		Medico medico = MedicoDAO.findByCPF(CPF_medico);

		return "Data: " + this.getData() + "\n" + "Hora: " + this.getHora() + "\n" + "Médico: " + medico.getNome()
				+ "\n" + "Especialidade: " + medico.getEspecialidadePrincipal().getNome();
	}

	/**
	 * Metodo da interface comparable que define os criterios de ordenação de uma
	 * lista de <b>AgendaConsulta</b>
	 * 
	 * @param compare objeto a ser comparado
	 * @return int
	 */
	@Override
	public int compareTo(AgendaConsulta compare) {

		MyDate myDate = new MyDate();

		int resultCompareToDate = myDate.compareTo(this.getData(), compare.getData());

		if (resultCompareToDate != 0)

			return resultCompareToDate;

		else {

			int resultCompareToTime = MyDateTime.compareTo(this.getHora(), compare.getHora());

			if (resultCompareToTime != 0)

				return resultCompareToTime;
		}

		return 0;

	}

	public boolean isMarcada() {
		return isMarcada;
	}

	public void setMarcada(boolean isMarcada) {
		this.isMarcada = isMarcada;
	}

}
