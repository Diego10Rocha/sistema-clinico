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
		
		List<T> users = readFile();
		
		Optional<T> userLoged = users.stream()
				.filter(user -> user.getCPF().equals(CPF)).findFirst();
		
		return userLoged.isPresent()? userLoged.get() : null;

	}

	public T findUser(String CPF, String senha) {

		List<T> users = readFile();
		
		Optional<T> userLoged = users.stream()
				.filter(user -> user.getCPF().equals(CPF) && user.getSenha().equals(senha)).findFirst();

		return userLoged.isPresent()? userLoged.get() : null;
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
