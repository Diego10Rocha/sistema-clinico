package dao;

import java.util.List;
import java.util.Optional;

import file.ConnectionFile;
import model.Especialidade;
import model.Medico;

public class EspecialidadeDAO {

	private static ConnectionFile<Especialidade> connectionFile = new ConnectionFile<>(Especialidade[].class,
			"Arquivos/Especialidades.json");

	public static List<Especialidade> getSpecialties() {

		List<Especialidade> specialties = connectionFile.readFile();

		return specialties;
	}

	public static boolean insertSpecialty(Especialidade specialty) {

		try {

			if (specialtyAlreadyRegistered(specialty.getNome())) {

				throw new Exception("Especialidade já cadastrada");
			}

			setId(specialty);

			connectionFile.writer(specialty);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return false;

	}

	private static void setId(Especialidade specialty) {

		int id = countSpecialty() + 1;

		specialty.setId(id);
	}

	public static int countSpecialty() {

		return getSpecialties().size();

	}

	public static boolean updateSpecialty(Especialidade newSpecialty) {

		boolean isUpdated = false;

		List<Especialidade> specialtys = getSpecialties();

		Especialidade oldSpecialty = findById(newSpecialty.getId());

		if (oldSpecialty != null) {

			specialtys.remove(oldSpecialty);

			specialtys.add(newSpecialty);

			connectionFile.reWriter(specialtys);
		}

		return isUpdated;
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

		connectionFile.reWriter(specialties);

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

	public static Especialidade findById(int id) {

		List<Especialidade> specialties = getSpecialties();

		Optional<Especialidade> specialtie = specialties.stream()
				.filter(specialtieRecord -> specialtieRecord.getId() == id).findFirst();

		return specialtie.isPresent() ? specialtie.get() : null;
	}

}
