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
import model.Medico;

/**
 * Classe DAO para persistência em arquivo de Medicos
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class MedicoDAO {

	private static FindUserRegister<Medico> findUserRegister = new FindUserRegister<>(Medico[].class,
			"Arquivos/Medicos.json");
	private static ConnectionFile<Medico> connectionFile = new ConnectionFile<>(Medico[].class,
			"Arquivos/Medicos.json");

	/**
	 * Busca a lista de medicos dentro do arquivo
	 * @return List<Medico>
	 */
	public static List<Medico> getDoctors() {

		List<Medico> doctors = connectionFile.readFile();

		return doctors;
	}

	/**
	 * Metodo de cadastro de médicos
	 * @param doctor
	 * @return boolean
	 */
	public static boolean insertDoctor(Medico doctor) {

		try {

			if (cpfAlreadyRegistered(doctor.getCPF()) 
					|| RecepcionistaDAO.cpfAlreadyRegistered(doctor.getCPF())) {

				throw new Exception("CPF já cadastrado");
			}

			connectionFile.writer(doctor);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return false;

	}

	/**
	 * Metodo de atualizar os dados de um médico
	 * @param newDoctor
	 * @return boolean
	 */
	public static boolean updateDoctor(Medico newDoctor) {

		boolean isUpdated = false;
		boolean isOldDoctorRegistered;

		List<Medico> doctors = getDoctors();

		isOldDoctorRegistered = cpfAlreadyRegistered(newDoctor.getCPF());

		if (isOldDoctorRegistered) {

			Medico oldDoctor = findByCPF(newDoctor.getCPF());

			doctors.remove(oldDoctor);

			doctors.add(newDoctor);

			connectionFile.reWriter(doctors);
		}

		return isUpdated;
	}

	/**
	 * Metodo para apagar um médico
	 * @param doctor
	 * @return boolean
	 */
	public static boolean deleteMedico(Medico doctor) {

		boolean isDeletado;

		List<Medico> doctors = getDoctors();

		isDeletado = doctors.remove(doctor);

		connectionFile.reWriter(doctors);

		return isDeletado;
	}

	/**
	 * Metodo de login
	 * @param cpf
	 * @param senha
	 * @return Medico
	 */
	public static Medico login(String cpf, String senha) {

		return findUserRegister.findUser(cpf, senha);
	}

	/**
	 * Metodo que verifica se um determinado CPF está cadastrado nos registros de médicos
	 * @param cpf
	 * @return boolean
	 */
	public static boolean cpfAlreadyRegistered(String cpf) {

		Medico temp = findUserRegister.findUser(cpf);

		return temp != null;
	}

	public static Medico findByCPF(String cpf) {

		return findUserRegister.findUser(cpf);
	}
}
