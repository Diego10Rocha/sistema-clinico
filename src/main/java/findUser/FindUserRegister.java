package findUser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import model.Usuario;


public class FindUserRegister<T extends Usuario> {

	private final Class<T[]> type;
	private String path;
	private Gson gson;

	public FindUserRegister(Class<T[]> type, String path) {
		
		this.type = type;
		this.path = path;
		this.gson = new Gson();
	}

	public T findUser(String CPF) {

		T userFound = null;

		String cpfUserCadastrado;

		List<T> cadastros = readFile();

		for (T userCadastrado : cadastros) {

			cpfUserCadastrado = userCadastrado.getCPF();

			if (cpfUserCadastrado.equals(CPF)) {

				userFound = userCadastrado;
			}

		}

		return userFound;

	}

	public T findUser(String CPF, String senha) {

		T userFound = null;

		String cpfUserCadastrado;
		String senhaUserCadastrado;

		List<T> cadastros = readFile();

		for (T userCadastrado : cadastros) {

			cpfUserCadastrado = userCadastrado.getCPF();
			senhaUserCadastrado = userCadastrado.getSenha();

			if (cpfUserCadastrado.equals(CPF) && senhaUserCadastrado.equals(senha)) {

				userFound = userCadastrado;
			}

		}

		return userFound;
	}
	

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
