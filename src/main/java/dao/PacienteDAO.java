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

import file.ConnectionFile;
import findUser.FindUserRegister;
import model.Paciente;

/**
 * Classe DAO para persistência em arquivo de Pacientes
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class PacienteDAO {

	private static FindUserRegister<Paciente> findUserRegister = new FindUserRegister<>(Paciente[].class,
			"Arquivos/Pacientes.json");
	private static ConnectionFile<Paciente> connectionFile = new ConnectionFile<>(Paciente[].class,
			"Arquivos/Pacientes.json");

	/**
	 * Retorna a lista de pacientes cadastrados
	 * @return List<Paciente>
	 */
	public static List<Paciente> getPatients() {

		List<Paciente> patients = connectionFile.readFile();

		return patients;
	}

	/**
	 * Cadastra um paciente
	 * @param patient
	 * @return boolean
	 */
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

	/**
	 * metodo para atualizar os dados de um paciente
	 * @param newPatient
	 * @return boolean
	 */
	public static boolean updatePatient(Paciente newPatient) {

		boolean isUpdated = false;
		boolean isOldPatientRegistered;

		List<Paciente> patients = getPatients();

		isOldPatientRegistered = cpfAlreadyRegistered(newPatient.getCPF());

		if (isOldPatientRegistered) {

			Paciente oldPatient = findByCPF(newPatient.getCPF());

			patients.remove(oldPatient);

			patients.add(newPatient);

			connectionFile.reWriter(patients);
		}

		return isUpdated;
	}

	/**
	 * Metodo para apagar os dados de um paciente
	 * @param paciente
	 * @return boolean
	 */
	public static boolean deletePatient(Paciente paciente) {

		boolean isDeletado;

		List<Paciente> patients = getPatients();

		isDeletado = patients.remove(paciente);

		connectionFile.reWriter(patients);

		return isDeletado;
	}

	/**
	 * Verifica se um cpf já foi cadastrado em algum paciente
	 * @param cpf
	 * @return boolean
	 */
	public static boolean cpfAlreadyRegistered(String cpf) {

		Paciente temp = findUserRegister.findUser(cpf);
		return temp != null;
	}

	/**
	 * Busca um paciente a partir de um CPF
	 * @param cpf
	 * @return Paciente
	 */
	public static Paciente findByCPF(String cpf) {

		return findUserRegister.findUser(cpf);
	}
}
