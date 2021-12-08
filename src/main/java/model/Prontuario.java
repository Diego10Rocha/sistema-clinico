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

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class Prontuario {

	private final String CPF_PACIENTE;
	private String anamnese;
	private String exameFisico;
	private String hipotesesDiagnosticas;
	private String diagnosticosDefinitivos;
	private String tratamentosEfetuados;

	/**
	 * Contrutor da classe
	 * @param CPF_PACIENTE
	 */
	public Prontuario(String CPF_PACIENTE) {

		this.CPF_PACIENTE = CPF_PACIENTE;

	}

	/**
	 * Retorna dados de um objeto da classe em uma String
	 * @return String
	 */
	public String getFormularioString() {

		String formularioProntuario;
		String nomePacienteProntuario = PacienteDAO.findByCPF(CPF_PACIENTE).getNome();

		formularioProntuario = "Paciente: " + nomePacienteProntuario + "\n" + "\n" + "Anamnese: " + "\n" + anamnese
				+ "\n" + "\n" + "Exame Físico: " + "\n" + exameFisico + "\n" + "\n" + "Hipóteses diagnósticas: " + "\n"
				+ hipotesesDiagnosticas + "\n" + "\n" + "Diagnósticos definitivos: " + "\n" + diagnosticosDefinitivos
				+ "\n" + "\n" + "Tratamentos efetuados: " + "\n" + tratamentosEfetuados + "\n";

		return formularioProntuario;
	}

	public String getAnamnese() {
		return anamnese;
	}

	public void setAnamnese(String anamnese) {
		this.anamnese = anamnese;

	}

	public String getExameFisico() {
		return exameFisico;

	}

	public void setExameFisico(String exameFisico) {

		this.exameFisico = exameFisico;

	}

	public String getHipotesesDiagnosticas() {
		return hipotesesDiagnosticas;
	}

	public void setHipotesesDiagnosticas(String hipotesesDiagnosticas) {

		this.hipotesesDiagnosticas = hipotesesDiagnosticas;

	}

	public String getDiagnosticosDefinitivos() {
		return diagnosticosDefinitivos;
	}

	public void setDiagnosticosDefinitivos(String diagnosticosDefinitivos) {

		this.diagnosticosDefinitivos = diagnosticosDefinitivos;

	}

	public String getTratamentosEfetuados() {
		return tratamentosEfetuados;
	}

	public void setTratamentosEfetuados(String tratamentosEfetuados) {

		this.tratamentosEfetuados = tratamentosEfetuados;

	}

	/**
	 * Retorna dados de um objeto da classe em uma String
	 * @return String
	 */
	@Override
	public String toString() {

		return getFormularioString();
	}

	public String getCPF_PACIENTE() {
		return CPF_PACIENTE;
	}

}
