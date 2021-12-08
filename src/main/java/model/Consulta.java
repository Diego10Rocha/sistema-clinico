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

import dao.PacienteDAO;
import date.MyDateTime;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class Consulta implements Comparable<Consulta> {

	private String data;
	private String hora;
	private final String CPF_medico;
	private final String CPF_paciente;
	private boolean isRealizada;

	/**
	 * Contrutor da classe
	 * @param data
	 * @param hora
	 * @param CPF_medico
	 * @param CPF_paciente
	 */
	public Consulta(String data, String hora, String CPF_medico, String CPF_paciente) {

		this.data = data;
		this.hora = hora;
		this.CPF_medico = CPF_medico;
		this.CPF_paciente = CPF_paciente;

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

	public boolean isRealizada() {
		return isRealizada;
	}

	public void setRealizada(boolean isRealizada) {
		this.isRealizada = isRealizada;
	}

	public String getCPF_medico() {
		return CPF_medico;
	}

	public String getCPF_paciente() {
		return CPF_paciente;
	}

	/**
	 * Retorna dados de um objeto da classe em String
	 * @return String
	 */
	@Override
	public String toString() {

		String nomePaciente = PacienteDAO.findByCPF(CPF_paciente).getNome();

		return "Data: " + this.getData() + "\n" + "Hora: " + this.getHora() + "\n" + "Paciente: " + nomePaciente;
	}

	/**
	 * Sobrescrita do metodo equals da classe <b>Object</>
	 * @param obj objeto a ser comparado
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {

		boolean objIsEqual = false;

		if (obj instanceof Consulta) {

			Consulta consultaASerComparada = (Consulta) obj;

			if (consultaASerComparada.getData().equals(this.getData())
					&& consultaASerComparada.getHora().equals(this.getHora())
					&& consultaASerComparada.getCPF_medico().equals(this.getCPF_medico())
					&& consultaASerComparada.getCPF_paciente().equals(this.getCPF_paciente())) {

				objIsEqual = true;
			}

		}

		return objIsEqual;
	}

	/**
	 * Metodo da interface comparable que define os criterios de ordenação de uma lista de <b>Consulta</b>
	 * @param compare objeto a ser comparado
	 * @return int
	 */
	@Override
	public int compareTo(Consulta compare) {

		return MyDateTime.compareTo(this.getHora(), compare.getHora());
	}

}
