package dao;

import java.util.List;
import java.util.Optional;

import file.ConnectionFile;
import model.Consulta;

public class ConsultaDAO {

	private static ConnectionFile<Consulta> connectionFile = new ConnectionFile<>(Consulta[].class,
			"Arquivos/Consultas.json");

	public static List<Consulta> getConsultas() {

		List<Consulta> consultas = connectionFile.readFile();

		return consultas;
	}

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

	public static boolean updateSpecialty(Consulta newConsulta, Consulta consultaOld) {

		List<Consulta> consultas = getConsultas();

		consultas.set(consultas.indexOf(consultaOld), newConsulta);

		return connectionFile.reWriter(consultas);
	}

	public static boolean deleteConsulta(Consulta consulta) throws Exception {

		boolean isDeletada;

		List<Consulta> consultas = getConsultas();

		if (consulta.isRealizada())
			throw new Exception("A consulta possui medico(s)  e paciente(s) associado(s)");

		isDeletada = consultas.remove(consulta);

		connectionFile.reWriter(consultas);

		return isDeletada;
	}

	public static boolean consultaAlreadyRegistered(Consulta consulta) {

		boolean consultaAlreadyRegistered = false;

		Optional<Consulta> temp = findConsulta(consulta);

		if (temp.isPresent()) {

			consultaAlreadyRegistered = true;
		}

		return consultaAlreadyRegistered;

	}

	private static Optional<Consulta> findConsulta(Consulta consultaTarget) {

		List<Consulta> consultas = getConsultas();

		return consultas.stream().filter(consulta -> consulta.equals(consultaTarget)).findFirst();
	}

}
