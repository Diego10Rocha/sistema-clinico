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
import model.Especialidade;
import model.Medico;

/**
 * Classe DAO para persistência em arquivo de Especialidades
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class EspecialidadeDAO {

	private static ConnectionFile<Especialidade> connectionFile = new ConnectionFile<>(Especialidade[].class,
			"Arquivos/Especialidades.json");

	private static ConnectionFile<Integer> conAmountSpecialtyAlreadyRegistered = new ConnectionFile<>(Integer[].class,
			"Arquivos/AmountSpecialtyAlreadyRegistered.json");

	/**
	 * Retorna a lista de especialidades cadastradas
	 * @return List<Especialidade>
	 */
	public static List<Especialidade> getSpecialties() {

		List<Especialidade> specialties = connectionFile.readFile();

		return specialties;
	}

	/**
	 * Metodo de cadastro de especialidades
	 * @param specialty
	 * @return boolean
	 */
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

	/**
	 * Metodo que cria um ID para uma especialidade
	 * @param specialty
	 */
	private static void setId(Especialidade specialty) {

		int id = getId();

		specialty.setId(id);

		updateAmountSpecialtyAlreadyRegistered(id);
	}

	/**
	 * Retorna o ID de uma especialidade
	 * @return int
	 */
	private static int getId() {

		int amountSpecialtyAlreadyRegistered = conAmountSpecialtyAlreadyRegistered.readFile().get(0);

		return amountSpecialtyAlreadyRegistered + 1;
	}

	/**
	 * @param lastIdRegistered
	 */
	private static void updateAmountSpecialtyAlreadyRegistered(int lastIdRegistered) {

		conAmountSpecialtyAlreadyRegistered.reWriter(lastIdRegistered);
	}

	/**
	 * Metodo para atualizar os dados de uma especialidade
	 * @param newSpecialty
	 * @return boolean
	 */
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

	/**
	 * Metodo para apagar uma especialidade
	 * @param specialty
	 * @return boolean
	 * @throws Exception
	 */
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

	/**
	 * Verifica se uma especialidade já está cadastrada
	 * @param nome
	 * @return boolean
	 */
	public static boolean specialtyAlreadyRegistered(String nome) {

		boolean specialtyAlreadyRegistered = false;

		Especialidade temp = findByName(nome);

		if (temp != null) {

			specialtyAlreadyRegistered = true;
		}

		return specialtyAlreadyRegistered;

	}

	/**
	 * Busca uma especialidade pelo seu nome
	 * @param name
	 * @return Especialidade
	 */
	public static Especialidade findByName(String name) {

		List<Especialidade> specialties = getSpecialties();

		Optional<Especialidade> specialtie = specialties.stream()
				.filter(specialtieRecord -> specialtieRecord.getNome().equals(name)).findFirst();

		return specialtie.isPresent() ? specialtie.get() : null;
	}

	/**
	 * Busca uma especialidade pelo seu ID
	 * @param id
	 * @return Especialidade
	 */
	public static Especialidade findById(int id) {

		List<Especialidade> specialties = getSpecialties();

		Optional<Especialidade> specialtie = specialties.stream()
				.filter(specialtieRecord -> specialtieRecord.getId() == id).findFirst();

		return specialtie.isPresent() ? specialtie.get() : null;
	}

}
