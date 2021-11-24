package dao;

import java.util.List;
import java.util.Optional;

import file.ConnectionFile;
import model.Especialidade;
import model.Medico;

public class EspecialidadeDAO {

	private static final String PATH_CADASTROS_ESPECIALIDADE = "Arquivos/Especialidades.json";
	private static ConnectionFile connectionFile = new ConnectionFile();

	public static List<Especialidade> getSpecialties() {

		List<Especialidade> specialties = connectionFile.readFile(PATH_CADASTROS_ESPECIALIDADE, Especialidade[].class);

		return specialties;
	}

	public static boolean insertSpecialty(Especialidade specialty) {

		try {

			if (specialtyAlreadyRegistered(specialty.getNome())) {

				throw new Exception("Especialidade já cadastrada");
			}

			connectionFile.writer(specialty);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return false;

	}

	public static boolean updateSpecialty(Especialidade specialty, Especialidade specialtyOld) {

		List<Especialidade> specialties = getSpecialties();

		specialties.set(specialties.indexOf(specialtyOld), specialty);

		return connectionFile.reWriter(specialties, PATH_CADASTROS_ESPECIALIDADE);
	}

	public static boolean deleteSpecialty(Especialidade specialty) throws Exception {

		boolean isDeletada;

		List<Especialidade> specialties = getSpecialties();
		List<Medico> doctors = MedicoDAO.getDoctors();

		boolean foundAssociation = doctors.stream()
				.anyMatch(doctor -> doctor.getEspecialidadePrincipal().equals(specialty)
						|| doctor.getSubEspecialidade().equals(specialty));

		if (foundAssociation)
			throw new Exception("A especialidade possui medico(s) associado(s)");

		isDeletada = specialties.remove(specialty);

		connectionFile.reWriter(specialties, PATH_CADASTROS_ESPECIALIDADE);

		return isDeletada;
	}

	public static boolean specialtyAlreadyRegistered(String nome) {

		boolean specialtyAlreadyRegistered = false;

		Especialidade temp = findByName(nome);

		if (temp != null) {

			specialtyAlreadyRegistered = true;
		}

		return specialtyAlreadyRegistered;

	}

	public static Especialidade findByName(String name) {

		List<Especialidade> specialties = getSpecialties();

		Optional<Especialidade> specialtie = specialties.stream()
				.filter(specialtieRecord -> specialtieRecord.getNome().equals(name)).findFirst();

		return specialtie.isPresent() ? specialtie.get() : null;
	}

}
