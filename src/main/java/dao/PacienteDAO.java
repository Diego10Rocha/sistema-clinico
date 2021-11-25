package dao;

import java.util.List;

import file.ConnectionFile;
import findUser.FindUserRegister;
import model.Paciente;

public class PacienteDAO {

	private static FindUserRegister<Paciente> findUserRegister = new FindUserRegister<>(Paciente[].class,
			"Arquivos/Pacientes.json");
	private static final String PATH_CADASTROS_PACIENTE = "Arquivos/Pacientes.json";
	private static ConnectionFile connectionFile = new ConnectionFile();

	public static List<Paciente> getPatients() {

		List<Paciente> patients = connectionFile.readFile(PATH_CADASTROS_PACIENTE, Paciente[].class);

		return patients;
	}

	public static boolean insertPatient(Paciente patient) {

		try {

			if (cpfAlreadyRegistered(patient.getCPF())) {

				throw new Exception("CPF já cadastrado");
			}

			connectionFile.writer(patient);

			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;

	}

	public static boolean updatePatient(Paciente newPatient) {

		boolean isUpdated = false;
		boolean isOldPatientRegistered;

		List<Paciente> patients = getPatients();

		isOldPatientRegistered = cpfAlreadyRegistered(newPatient.getCPF());

		if (isOldPatientRegistered) {

			Paciente oldPatient = findByCPF(newPatient.getCPF());

			patients.remove(oldPatient);

			patients.add(newPatient);

			connectionFile.reWriter(patients, PATH_CADASTROS_PACIENTE);
		}

		return isUpdated;
	}

	public static boolean deletePatient(Paciente paciente) {

		boolean isDeletado;

		List<Paciente> patients = getPatients();

		isDeletado = patients.remove(paciente);

		connectionFile.reWriter(patients, PATH_CADASTROS_PACIENTE);

		return isDeletado;
	}

	public static Paciente login(String cpf, String senha) {

		return findUserRegister.findUser(cpf, senha);
	}

	public static boolean cpfAlreadyRegistered(String cpf) {

		Paciente temp = findUserRegister.findUser(cpf);
		return temp != null;
	}

	public static Paciente findByCPF(String cpf) {

		return findUserRegister.findUser(cpf);
	}
}
