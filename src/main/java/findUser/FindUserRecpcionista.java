package findUser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import interfaces.IFindUser;
import model.Recepcionista;
import model.Usuario;

public class FindUserRecpcionista implements IFindUser {

	private Gson gson = new Gson();
	private String PATH_CADASTROS_RECEPCIONISTA = "Arquivos/cadastrosRecepcionista.json";

	@Override
	public Object findUser(String keyWord) {

		Object userFound = null;

		String cpfRecepcionistaCadastrada;

		List<Recepcionista> cadastrosRecepcionista = readFile(Recepcionista[].class);

		for (Recepcionista recepcionistaCadastrado : cadastrosRecepcionista) {

			cpfRecepcionistaCadastrada = recepcionistaCadastrado.getCPF();

			if (cpfRecepcionistaCadastrada.equals(keyWord)) {

				userFound = recepcionistaCadastrado;
			}

		}
		return userFound;
	}

	@Override
	public Object findUser(String keyWord1, String keyWord2) {

		Object userFound = null;

		String cpfRecepcionistaCadastrada;
		String senhaRecepcionistaCadastrada;

		List<Recepcionista> cadastrosRecepcionista = readFile(Recepcionista[].class);

		for (Recepcionista recepcionistaCadastrado : cadastrosRecepcionista) {

			cpfRecepcionistaCadastrada = recepcionistaCadastrado.getCPF();
			senhaRecepcionistaCadastrada = recepcionistaCadastrado.getSenha();

			if (cpfRecepcionistaCadastrada.equals(keyWord1) && senhaRecepcionistaCadastrada.equals(keyWord2)) {

				userFound = recepcionistaCadastrado;
			}

		}

		return userFound;
	}

	@Override
	public <T extends Usuario> List<T> readFile(Class<T[]> type) {

		List<T> listCadastrosFromJson = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(PATH_CADASTROS_RECEPCIONISTA))) {

			T[] arrayCadastrosFromJson = gson.fromJson(br, type);

			listCadastrosFromJson = Arrays.asList(arrayCadastrosFromJson);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return listCadastrosFromJson;
	}

}
