package model;

public class Prontuario {

	private final String CPF_PACIENTE;
	private String anamnese;
	private String exameFisico;
	private String hipotesesDiagnosticas;
	private String diagnosticosDefinitivos;
	private String tratamentosEfetuados;

	public Prontuario(String CPF_PACIENTE) {

		this.CPF_PACIENTE = CPF_PACIENTE;

	}

	public String getFormularioString() {

		String formularioProntuario;

		formularioProntuario = CPF_PACIENTE + " Prontuário" + "\n" + "\n" + "Anamnese: " + "\n" + anamnese + "\n" + "\n"
				+ "Exame Físico: " + "\n" + exameFisico + "\n" + "\n" + "Hipóteses diagnósticas: " + "\n"
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

	@Override
	public String toString() {
		return "Prontuario [paciente=" + CPF_PACIENTE + ", anamnese=" + anamnese + ", exameFisico=" + exameFisico
				+ ", hipotesesDiagnosticas=" + hipotesesDiagnosticas + ", diagnosticosDefinitivos="
				+ diagnosticosDefinitivos + ", tratamentosEfetuados=" + tratamentosEfetuados + "]";
	}

	public String getCPF_PACIENTE() {
		return CPF_PACIENTE;
	}

}
