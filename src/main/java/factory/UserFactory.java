package factory;

import model.Medico;
import model.Paciente;
import model.Recepcionista;

public class UserFactory {

	public static void createMedico(String name, String password, String CPF, String CRM, String especialidade,
			String subEspecialidade, String horaDisponivelConsulta) {

		Medico newMedico = new Medico(name, password, CPF, CRM, especialidade, subEspecialidade,
				horaDisponivelConsulta);
	}

	public static void createPaciente(String name, String CPF, String dataNascimento) {

		Paciente newPaciente = new Paciente(name, CPF, dataNascimento);
	}

	public static void createRecepcionista(String name, String CPF, String password) {

		Recepcionista newRecepcionista = new Recepcionista(name, CPF, password);

	}
	
}
