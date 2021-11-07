package dao;

import java.util.List;
import java.util.Optional;

import file.ConnectionFile;
import model.Prontuario;

public class ProntuarioDAO {
	
	private static final String PATH_CADASTROS_PRONTUARIO = "Arquivos/Prontuarios.json";
	private static ConnectionFile connectionFile = new ConnectionFile();
	
	public static List<Prontuario> getMedicalRecords() {

		List<Prontuario> medicalRecords = connectionFile.readFile(PATH_CADASTROS_PRONTUARIO, Prontuario[].class);

		return medicalRecords;
	}
	
	public static boolean insertMedicalRecord(Prontuario medicalRecord) {

		try {

			connectionFile.writer(medicalRecord);

			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;

	}

	public static boolean updateMedicalRecord(Prontuario medicalRecord, Prontuario medicalRecordOld) {

		List<Prontuario> medicalRecords = getMedicalRecords();

		medicalRecords.set(medicalRecords.indexOf(medicalRecordOld), medicalRecord);

		return connectionFile.reWriter(medicalRecords, PATH_CADASTROS_PRONTUARIO);
	}

	public static boolean deleteMedicalRecord(Prontuario medicalRecord) {

		boolean isDeletado;

		List<Prontuario> medicalRecords = getMedicalRecords();

		isDeletado = medicalRecords.remove(medicalRecord);

		connectionFile.reWriter(medicalRecords, PATH_CADASTROS_PRONTUARIO);

		return isDeletado;
	}
	
	public Prontuario find(String CPF_PACIENTE) {
		
		List<Prontuario> medicalRecords = getMedicalRecords();
		
		Optional<Prontuario> medicalRecordFound = medicalRecords.stream()
				.filter(medicalRecord -> medicalRecord.getCPF_PACIENTE().equals(CPF_PACIENTE)).findFirst();
		
		return medicalRecordFound.isPresent()? medicalRecordFound.get() : null;
	}

}
