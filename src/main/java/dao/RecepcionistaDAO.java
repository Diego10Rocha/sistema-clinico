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
import model.Recepcionista;

/**
 * Classe DAO para persistência em arquivo de Recepcionistas
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class RecepcionistaDAO {

	private static FindUserRegister<Recepcionista> findUserRegister = new FindUserRegister<>(Recepcionista[].class,
			"Arquivos/Recepcionistas.json");
	private static ConnectionFile<Recepcionista> connectionFile = new ConnectionFile<>(Recepcionista[].class,
			"Arquivos/Recepcionistas.json");

	/**
	 * Metodo que retorna uma lista de recepcionistas que estão salvos em arquivo
	 * @return List<Recepcionista>
	 */
	public static List<Recepcionista> getReceptionists() {

		List<Recepcionista> receptionists = connectionFile.readFile();

		return receptionists;
	}

	/**
	 * Metodo de cadastro de recepcionista 
	 * @param receptionist
	 * @return boolean
	 */
	public static boolean insertReceptionist(Recepcionista receptionist) {

		try {

			if (cpfAlreadyRegistered(receptionist.getCPF()) 
					|| MedicoDAO.cpfAlreadyRegistered(receptionist.getCPF())) {

				throw new Exception("Recepcionista já cadastrada");
			}

			connectionFile.writer(receptionist);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return false;
	}

	/**
	 * Metodo de atualizar os dados de um recepcionista(Não está nos requisitos mas a modelagem está pronta, apenas não está na GUI)
	 * @param newReceptionist
	 * @return boolean
	 */
	public static boolean updateReceptionist(Recepcionista newReceptionist) {

		boolean isUpdated = false;
		boolean isOldReceptionistRegistered;

		List<Recepcionista> receptionists = getReceptionists();

		isOldReceptionistRegistered = cpfAlreadyRegistered(newReceptionist.getCPF());

		if (isOldReceptionistRegistered) {

			Recepcionista oldReceptionist = findByCPF(newReceptionist.getCPF());

			receptionists.remove(oldReceptionist);

			receptionists.add(newReceptionist);

			connectionFile.reWriter(receptionists);
		}

		return isUpdated;
	}

	/**
	 * Metodo para apagar um recepcionista(Não está nos requisitos mas a modelagem está pronta, apenas não está na GUI)
	 * @param receptionist
	 * @return boolean
	 */
	public static boolean deleteReceptionist(Recepcionista receptionist) {

		boolean isDeletado;

		List<Recepcionista> receptionists = getReceptionists();

		isDeletado = receptionists.remove(receptionist);

		connectionFile.reWriter(receptionists);

		return isDeletado;
	}

	/**
	 * Metodo de login de recepcionistas
	 * @param cpf
	 * @param senha
	 * @return Recepcionista
	 */
	public static Recepcionista login(String cpf, String senha) {

		return findUserRegister.findUser(cpf, senha);
	}

	/**
	 * Verifica se um dado cpf está nos registros de recepcionistas
	 * @param cpf
	 * @return boolean
	 */
	public static boolean cpfAlreadyRegistered(String cpf) {

		Recepcionista temp = findUserRegister.findUser(cpf);

		return temp != null;
	}

	/**
	 * Metodo que busca um recepcionista através de um CPF
	 * @param cpf
	 * @return Recepcionista
	 */
	public static Recepcionista findByCPF(String cpf) {

		return findUserRegister.findUser(cpf);
	}
}
