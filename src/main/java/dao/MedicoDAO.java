package dao;

import java.util.List;

import file.ConnectionFile;
import findUser.FindUserRegister;
import model.Medico;

public class MedicoDAO {

	private static FindUserRegister<Medico> findUserRegister = new FindUserRegister<>(Medico[].class,
			"Arquivos/Medicos.json");
	private static ConnectionFile<Medico> connectionFile = new ConnectionFile<>(Medico[].class,
			"Arquivos/Medicos.json");

	public static List<Medico> getDoctors() {

		List<Medico> doctors = connectionFile.readFile();

		return doctors;
	}

	public static boolean insertDoctor(Medico doctor) {

		try {

			if (cpfAlreadyRegistered(doctor.getCPF())) {

				throw new Exception("CPF já cadastrado");
			}

			connectionFile.writer(doctor);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return false;

	}

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

	public static boolean deleteMedico(Medico doctor) {

		boolean isDeletado;

		List<Medico> doctors = getDoctors();

		isDeletado = doctors.remove(doctor);

		connectionFile.reWriter(doctors);

		return isDeletado;
	}

	public static Medico login(String cpf, String senha) {

		return findUserRegister.findUser(cpf, senha);
	}

	public static boolean cpfAlreadyRegistered(String cpf) {

		Medico temp = findUserRegister.findUser(cpf);

		return temp != null;
	}

	public static Medico findByCPF(String cpf) {

		return findUserRegister.findUser(cpf);
	}
}
