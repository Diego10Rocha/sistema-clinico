package dao;

import java.util.List;

import file.ConnectionFile;
import findUser.FindUserRegister;
import model.Recepcionista;

public class RecepcionistaDAO {

	private static FindUserRegister<Recepcionista> findUserRegister = new FindUserRegister<>(Recepcionista[].class,
			"Arquivos/Recepcionistas.json");
	private static final String PATH_CADASTROS_RECPCIONISTA = "Arquivos/Recepcionistas.json";
	private static ConnectionFile connectionFile = new ConnectionFile();

	public static List<Recepcionista> getReceptionists() {

		List<Recepcionista> receptionists = connectionFile.readFile(PATH_CADASTROS_RECPCIONISTA, Recepcionista[].class);

		return receptionists;
	}

	public static boolean insertReceptionist(Recepcionista receptionist) {

		try {

			if (cpfAlreadyRegistered(receptionist.getCPF())) {

				throw new Exception("Recepcionista já cadastrada");
			}

			connectionFile.writer(receptionist);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return false;
	}

	public static boolean updateReceptionist(Recepcionista newReceptionist) {
		
		boolean isUpdated = false;
		boolean isOldReceptionistRegistered;

		List<Recepcionista> receptionists = getReceptionists();

		isOldReceptionistRegistered = cpfAlreadyRegistered(newReceptionist.getCPF());

		if (isOldReceptionistRegistered) {

			Recepcionista oldReceptionist = findByCPF(newReceptionist.getCPF());

			receptionists.remove(oldReceptionist);

			receptionists.add(newReceptionist);

			connectionFile.reWriter(receptionists, PATH_CADASTROS_RECPCIONISTA);
		}

		return isUpdated;
	}

	public static boolean deleteReceptionist(Recepcionista receptionist) {

		boolean isDeletado;

		List<Recepcionista> receptionists = getReceptionists();

		isDeletado = receptionists.remove(receptionist);

		connectionFile.reWriter(receptionists, PATH_CADASTROS_RECPCIONISTA);

		return isDeletado;
	}

	public static Recepcionista login(String cpf, String senha) {

		return findUserRegister.findUser(cpf, senha);
	}

	public static boolean cpfAlreadyRegistered(String cpf) {

		Recepcionista temp = findUserRegister.findUser(cpf);

		return temp != null;
	}

	public static Recepcionista findByCPF(String cpf) {

		return findUserRegister.findUser(cpf);
	}
}
