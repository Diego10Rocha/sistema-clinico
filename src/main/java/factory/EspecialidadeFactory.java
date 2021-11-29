package factory;

import model.Especialidade;

public class EspecialidadeFactory {

	public static void createEspecialidade(String nomeEspecialidadePrincipal, String nomeSubEspecialidade) {

		new Especialidade(nomeEspecialidadePrincipal, true);

		new Especialidade(nomeSubEspecialidade, false);

	}

	public static void createEspecialidade(String nomeEspecialidade, boolean isPrincipal) {

		new Especialidade(nomeEspecialidade, isPrincipal);
	}

}
