package dao;

import java.util.List;

import file.ConnectionFile;
import findUser.FindUserRegister;
import model.Paciente;

public class PacienteDAO {

	private static FindUserRegister<Paciente> findUserRegister = new FindUserRegister<>(Paciente[].class,
			"Arquivos/cadastrosPaciente.json");
	private static final String PATH_CADASTROS_PACIENTE = "Arquivos/cadastrosPaciente.json";
	private static ConnectionFile connectionFile = new ConnectionFile();

	public static List<Paciente> getPatients() {

		List<Paciente> patients = connectionFile.readFile(PATH_CADASTROS_PACIENTE, Paciente[].class);

		return patients;
	}

	public static boolean insertPatient(Paciente patient) {

		try {

			if (!cpfIsUnique(patient.getCPF())) {

				throw new Exception("CPF já cadastrado");
			}

			connectionFile.writer(patient);

			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;

	}

	public static boolean updatePatient(Paciente patient, Paciente patientOld) {

		List<Paciente> patients = connectionFile.readFile(PATH_CADASTROS_PACIENTE, Paciente[].class);

		patients.set(patients.indexOf(patientOld), patient);

		return connectionFile.reWriter(patients, PATH_CADASTROS_PACIENTE);
	}

	public static boolean deletePatient(Paciente paciente) {

		boolean isDeletado;

		List<Paciente> patients = connectionFile.readFile(PATH_CADASTROS_PACIENTE, Paciente[].class);

		isDeletado = patients.remove(paciente);

		connectionFile.reWriter(patients, PATH_CADASTROS_PACIENTE);

		return isDeletado;
	}

	public static Paciente login(String cpf, String senha) {

		return findUserRegister.findUser(cpf, senha);
	}

	private static boolean cpfIsUnique(String cpf) {

		Paciente temp = findUserRegister.findUser(cpf);

		return temp == null;
	}
}
