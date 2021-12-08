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

package findUser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Prontuario;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class FindProntuarioRegister {

	private final String PATH_CADASTROS_PRONTUARIO = "Arquivos/cadastrosProntuario.json";;
	private Gson gson = new Gson();

	/**
	 * Metodo que retorna o prontuário a partir do CPF de um paciente
	 * @param ID_PACIENTE
	 * @return Prontuario
	 */
	public Prontuario find(String ID_PACIENTE) {

		Prontuario prontuarioFound = null;

		String ID_PACIENTE_Cadastrado;

		List<Prontuario> cadastros = readFile();

		for (Prontuario prontuario : cadastros) {

			ID_PACIENTE_Cadastrado = prontuario.getCPF_PACIENTE();

			if (ID_PACIENTE_Cadastrado.equals(ID_PACIENTE)) {

				prontuarioFound = prontuario;
			}

		}

		return prontuarioFound;

	}

	/**
	 * Metodo que retorna uma lista de printuários
	 * @return List<Prontuario>
	 */
	private List<Prontuario> readFile() {

		List<Prontuario> cadastrosFromJson = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(PATH_CADASTROS_PRONTUARIO))) {

			Type typeList = new TypeToken<ArrayList<Prontuario>>() {
			}.getType();

			cadastrosFromJson = gson.fromJson(br, typeList);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return cadastrosFromJson;

	}
}
