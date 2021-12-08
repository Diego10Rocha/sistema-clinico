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

package factory;

import dao.EspecialidadeDAO;
import model.Medico;
import model.Paciente;
import model.Recepcionista;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class UserFactory {

	/**
	 * Metodo de cadastro de médicos com subespecialidade
	 * @param name
	 * @param password
	 * @param CPF
	 * @param CRM
	 * @param especialidadePrincipal
	 * @param subEspecialidade
	 * @param horaDisponivelConsulta
	 */
	public static void createMedico(String name, String password, String CPF, String CRM, String especialidadePrincipal,
			String subEspecialidade, String horaDisponivelConsulta) {

		EspecialidadeFactory.createEspecialidade(especialidadePrincipal, subEspecialidade);

		int idEspecialidadePrincipal = EspecialidadeDAO.findByName(especialidadePrincipal).getId();
		int idSubEspecialidade = EspecialidadeDAO.findByName(subEspecialidade).getId();

		new Medico(name, password, CPF, CRM, idEspecialidadePrincipal, idSubEspecialidade, horaDisponivelConsulta);
	}

	/**
	 * Metodo de cadastro de médicos sem subespecialidade
	 * @param name
	 * @param password
	 * @param CPF
	 * @param CRM
	 * @param especialidadePrincipal
	 * @param subEspecialidade
	 * @param horaDisponivelConsulta
	 */
	public static void createMedico(String name, String password, String CPF, String CRM, String especialidadePrincipal,
			String horaDisponivelConsulta) {

		EspecialidadeFactory.createEspecialidade(especialidadePrincipal, true);

		int idEspecialidadePrincipal = EspecialidadeDAO.findByName(especialidadePrincipal).getId();

		new Medico(name, password, CPF, CRM, idEspecialidadePrincipal, horaDisponivelConsulta);
	}

	/**
	 * Metodo de cadasro de pacientes
	 * @param name
	 * @param CPF
	 * @param dataNascimento
	 */
	public static void createPaciente(String name, String CPF, String dataNascimento) {

		new Paciente(name, CPF, dataNascimento);
	}

	/**
	 * Metodo de cadastro de Recepcionista
	 * @param name
	 * @param CPF
	 * @param password
	 */
	public static void createRecepcionista(String name, String CPF, String password) {

		new Recepcionista(name, CPF, password);

	}

}
