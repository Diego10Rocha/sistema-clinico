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
import model.Consulta;

/**
 * Classe DAO para persistência em arquivo de Consultas
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class ConsultaDAO {

	private static ConnectionFile<Consulta> connectionFile = new ConnectionFile<>(Consulta[].class,
			"Arquivos/Consultas.json");

	/**
	 * Busca a lista de consultas salvas em arquivo
	 * @return List<Consulta>
	 */
	public static List<Consulta> getConsultas() {

		List<Consulta> consultas = connectionFile.readFile();

		return consultas;
	}

	/**
	 * Metodo de cadastro de consultas
	 * @param consulta
	 * @return boolean
	 */
	public static boolean insertConsulta(Consulta consulta) {
		System.out.println(consulta.getHora());

		try {

			if (consultaAlreadyRegistered(consulta)) {

				throw new Exception("Consulta já cadastrada");
				
			}
			connectionFile.writer(consulta);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return false;

	}

	/**
	 * Metodo de atualizar os dados de uma consulta
	 * @param newConsulta
	 * @param consultaOld
	 * @return boolean
	 */
	public static boolean updateSpecialty(Consulta newConsulta, Consulta consultaOld) {

		List<Consulta> consultas = getConsultas();

		consultas.set(consultas.indexOf(consultaOld), newConsulta);

		return connectionFile.reWriter(consultas);
	}

	/**
	 * Metodo para apagar uma consulta
	 * @param consulta
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean deleteConsulta(Consulta consulta) throws Exception {

		boolean isDeletada;

		List<Consulta> consultas = getConsultas();

		if (consulta.isRealizada())
			throw new Exception("A consulta possui medico(s)  e paciente(s) associado(s)");

		isDeletada = consultas.remove(consulta);

		connectionFile.reWriter(consultas);

		return isDeletada;
	}

	/**
	 * Metodo que verifica se uma consulta já está registrada
	 * @param consulta
	 * @return boolean
	 */
	public static boolean consultaAlreadyRegistered(Consulta consulta) {

		boolean consultaAlreadyRegistered = false;

		Optional<Consulta> temp = findConsulta(consulta);

		if (temp.isPresent()) {

			consultaAlreadyRegistered = true;
		}

		return consultaAlreadyRegistered;

	}

	/**
	 * Metodo que verifica se uma consulta já está registrada e a retorna
	 * @param consulta
	 * @return boolean
	 */
	private static Optional<Consulta> findConsulta(Consulta consultaTarget) {

		List<Consulta> consultas = getConsultas();

		return consultas.stream().filter(consulta -> consulta.equals(consultaTarget)).findFirst();
	}

}
