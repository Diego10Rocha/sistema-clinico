package dao;

import java.util.List;
import java.util.Optional;

import file.FileUserRegister;
import model.Paciente;

public class PacienteDAO {
	
	private static final String PATH_CADASTROS_PACIENTE = "Arquivos/cadastrosPaciente.json";
	private static FileUserRegister fileUserRegister = new FileUserRegister();

	public static List<Paciente> getPatients() {

		List<Paciente> patients = fileUserRegister.readFile(PATH_CADASTROS_PACIENTE, Paciente[].class);

		return patients;
	}

	public static boolean insertPatient(Paciente patient) {

		try {

			if (!cpfIsUnique(patient.getCPF())) {

				throw new Exception("CPF já cadastrado");
			}

			fileUserRegister.writer(patient);

			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;

	}

	public static boolean updatePatient(Paciente patient, Paciente patientOld) {
		
		List<Paciente> patients = fileUserRegister.readFile(PATH_CADASTROS_PACIENTE, Paciente[].class);
		
		patients.set(patients.indexOf(patientOld), patient);
		
		return fileUserRegister.reWriter(patients, PATH_CADASTROS_PACIENTE);
	}

	public static boolean deletePatient(Paciente paciente) {
		
		boolean isDeletado;
		
		List<Paciente> patients = fileUserRegister.readFile(PATH_CADASTROS_PACIENTE, Paciente[].class);
		
		isDeletado = patients.remove(paciente);
		
		fileUserRegister.reWriter(patients, PATH_CADASTROS_PACIENTE);
	
		
		return isDeletado;
	}

	public static Paciente login(String cpf, String senha) {
		
		try {
			List<Paciente> patients = getPatients();

			if (patients.isEmpty())
				return null;
			
			Optional<Paciente> loged = patients.stream()
					.filter(patient -> patient.getCPF().equals(cpf) && patient.getSenha().equals(senha)).findFirst();

			if (!loged.isPresent())
				return null;
			return loged.get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static boolean cpfIsUnique(String cpf) {
		
		try {
			List<Paciente> patients = getPatients();

			if (patients.isEmpty())
				return false;

			Optional<Paciente> pacienteWithCpfRegistered = patients.stream()
					.filter(patient -> patient.getCPF().equals(cpf)).findFirst();

			if (pacienteWithCpfRegistered.isPresent())
				return false;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
