package dao;

import java.util.List;

import file.ConnectionFile;
import findUser.FindUserRegister;
import model.Medico;

public class MedicoDAO {

	private static FindUserRegister<Medico> findUserRegister = new FindUserRegister<>(Medico[].class,
			"Arquivos/cadastrosMedico.json");
	private static final String PATH_CADASTROS_MEDICO = "Arquivos/cadastrosMedico.json";
	private static ConnectionFile connectionFile = new ConnectionFile();

	public static List<Medico> getDoctors() {

		List<Medico> doctors = connectionFile.readFile(PATH_CADASTROS_MEDICO, Medico[].class);

		return doctors;
	}

	public static boolean insertDoctor(Medico doctor) {

		try {

			if (!cpfIsUnique(doctor.getCPF())) {

				throw new Exception("CPF já cadastrado");
			}

			connectionFile.writer(doctor);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return false;

	}

	public static boolean updateDoctor(Medico doctor, Medico doctorOld) {

		List<Medico> doctors = getDoctors();

		doctors.set(doctors.indexOf(doctorOld), doctor);

		return connectionFile.reWriter(doctors, PATH_CADASTROS_MEDICO);
	}

	public static boolean deleteMedico(Medico doctor) {
		
		boolean isDeletado;

		List<Medico> doctors = getDoctors();

		isDeletado = doctors.remove(doctor);

		connectionFile.reWriter(doctors, PATH_CADASTROS_MEDICO);

		return isDeletado;
	}

	public static Medico login(String cpf, String senha) {
		
		return findUserRegister.findUser(cpf, senha);
	}

	private static boolean cpfIsUnique(String cpf) {
		
		Medico temp = findUserRegister.findUser(cpf);

		return temp == null;
	}
}
