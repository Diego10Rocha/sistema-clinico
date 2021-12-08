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

package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class ConnectionFile<T> {

	private Gson gson = new Gson();
	private final String PATH;
	private final Class<T[]> type;

	/**
	 * Construtor da classe
	 * @param type
	 * @param PATH
	 */
	public ConnectionFile(Class<T[]> type, String PATH) {

		this.type = type;
		this.PATH = PATH;

	}

	/**
	 * Metodo pra ecrever um objeto em um arquivo
	 * @param obj
	 * @return boolean
	 */
	public boolean writer(T obj) {

		String json;

		List<T> cadastros = readFile();

		cadastros.add(obj);

		try (FileWriter writer = new FileWriter(PATH)) {

			json = gson.toJson(cadastros);
			System.out.println(json);
			writer.write(json);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return true;

	}

	/**
	 * Metodo para atualizar os dados de um arquivo
	 * @param objs
	 * @return boolean
	 */
	public boolean reWriter(List<T> objs) {

		String json;

		try (FileWriter writer = new FileWriter(PATH)) {

			json = gson.toJson(objs);

			writer.write(json);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return true;

	}

	/**
	 * Metodo para atualizar os dados de um arquivo
	 * @param objs
	 * @return boolean
	 */
	public boolean reWriter(T obj) {

		String json;

		try (FileWriter writer = new FileWriter(PATH)) {

			List<T> temp = new ArrayList<>();

			temp.add(obj);

			json = gson.toJson(temp);

			writer.write(json);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return true;

	}

	/**
	 * Metodo para ler os dados de um arquivo
	 * @param objs
	 * @return boolean
	 */
	public List<T> readFile() {

		try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {

			T[] arrayCadastrosFromJson = gson.fromJson(br, type);

			if (arrayCadastrosFromJson == null)

				return new ArrayList<T>();

			return new ArrayList<T>(Arrays.asList(arrayCadastrosFromJson));

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

}
