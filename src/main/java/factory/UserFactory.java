package factory;

import dao.EspecialidadeDAO;
import model.Medico;
import model.Paciente;
import model.Recepcionista;

public class UserFactory {

	public static void createMedico(String name, String password, String CPF, String CRM, String especialidadePrincipal,
			String subEspecialidade, String horaDisponivelConsulta) {

		EspecialidadeFactory.createEspecialidade(especialidadePrincipal, subEspecialidade);

		int idEspecialidadePrincipal = EspecialidadeDAO.findByName(especialidadePrincipal).getId();
		int idSubEspecialidade = EspecialidadeDAO.findByName(subEspecialidade).getId();

		new Medico(name, password, CPF, CRM, idEspecialidadePrincipal, idSubEspecialidade, horaDisponivelConsulta);
	}

	public static void createMedico(String name, String password, String CPF, String CRM, String especialidadePrincipal,
			String horaDisponivelConsulta) {

		EspecialidadeFactory.createEspecialidade(especialidadePrincipal, true);

		int idEspecialidadePrincipal = EspecialidadeDAO.findByName(especialidadePrincipal).getId();

		new Medico(name, password, CPF, CRM, idEspecialidadePrincipal, horaDisponivelConsulta);
	}

	public static void createPaciente(String name, String CPF, String dataNascimento) {

		new Paciente(name, CPF, dataNascimento);
	}

	public static void createRecepcionista(String name, String CPF, String password) {

		new Recepcionista(name, CPF, password);

	}

}
