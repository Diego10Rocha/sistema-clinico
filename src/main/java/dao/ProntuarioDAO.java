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

package dao;

import java.util.List;
import java.util.Optional;

import file.ConnectionFile;
import model.Prontuario;

/**
 * Classe DAO para persistência em arquivo de Prontuários de Pacientes
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class ProntuarioDAO {

	private static ConnectionFile<Prontuario> connectionFile = new ConnectionFile<>(Prontuario[].class,
			"Arquivos/Prontuarios.json");

	/**
	 * Metodo que retorna uma lista de prontuários
	 * @return List<Prontuario>
	 */
	public static List<Prontuario> getMedicalRecords() {

		List<Prontuario> medicalRecords = connectionFile.readFile();

		return medicalRecords;
	}

	/**
	 * Metodo de cadastro em arquivo de prontuários
	 * @param medicalRecord
	 * @return boolean
	 */
	public static boolean insertMedicalRecord(Prontuario medicalRecord) {

		try {

			connectionFile.writer(medicalRecord);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return false;

	}

	/**
	 * Metodo de atualizar um prontuário
	 * @param medicalRecord
	 * @param medicalRecordOld
	 * @return boolean
	 */
	public static boolean updateMedicalRecord(Prontuario medicalRecord, Prontuario medicalRecordOld) {

		List<Prontuario> medicalRecords = getMedicalRecords();

		medicalRecords.set(medicalRecords.indexOf(medicalRecordOld), medicalRecord);

		return connectionFile.reWriter(medicalRecords);
	}

	/**
	 * Metodo para apagar um prontuário de um arquivo
	 * @param medicalRecord
	 * @return boolean
	 */
	public static boolean deleteMedicalRecord(Prontuario medicalRecord) {

		boolean isDeletado;

		List<Prontuario> medicalRecords = getMedicalRecords();

		isDeletado = medicalRecords.remove(medicalRecord);

		connectionFile.reWriter(medicalRecords);

		return isDeletado;
	}

	/**
	 * Metodo para buscar um prontuário através do cpf de um paciente
	 * @param CPF_PACIENTE
	 * @return Prontuario
	 */
	public Prontuario find(String CPF_PACIENTE) {

		List<Prontuario> medicalRecords = getMedicalRecords();

		Optional<Prontuario> medicalRecordFound = medicalRecords.stream()
				.filter(medicalRecord -> medicalRecord.getCPF_PACIENTE().equals(CPF_PACIENTE)).findFirst();

		return medicalRecordFound.isPresent() ? medicalRecordFound.get() : null;
	}

}
