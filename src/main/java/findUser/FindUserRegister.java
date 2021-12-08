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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

import model.Usuario;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class FindUserRegister<T extends Usuario> {

	private final Class<T[]> type;
	private String path;
	private Gson gson;

	/**
	 * Construtor da classe
	 * @param type
	 * @param path
	 */
	public FindUserRegister(Class<T[]> type, String path) {
		
		this.type = type;
		this.path = path;
		this.gson = new Gson();
	}

	/**
	 * Metodo que busca um usuário a partir de um CPF
	 * @param CPF
	 * @return T
	 */
	public T findUser(String CPF) {
		
		List<T> users = readFile();
		
		Optional<T> userLoged = users.stream()
				.filter(user -> user.getCPF().equals(CPF)).findFirst();
		
		
		return userLoged.isPresent()? userLoged.get() : null;

	}

	/**
	 * Metodo que busca um usuário a partir de um login e uma senha
	 * @param CPF
	 * @param senha
	 * @return T
	 */
	public T findUser(String CPF, String senha) {

		List<T> users = readFile();
		
		Optional<T> userLoged = users.stream()
				.filter(user -> user.getCPF().equals(CPF) && user.getSenha().equals(senha)).findFirst();

		return userLoged.isPresent()? userLoged.get() : null;
	}
	

	/**
	 * Retorna uma lista com os usuários cadastrados
	 * @return List<T>
	 */
	public List<T> readFile() {

		List<T> listCadastrosFromJson = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			T[] arrayCadastrosFromJson = gson.fromJson(br, type);

			listCadastrosFromJson = Arrays.asList(arrayCadastrosFromJson);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return listCadastrosFromJson;

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Class<T[]> getType() {
		return type;
	}

}
