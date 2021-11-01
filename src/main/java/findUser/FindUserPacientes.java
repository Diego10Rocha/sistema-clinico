package findUser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import interfaces.IFindUser;
import model.Paciente;
import model.Usuario;

public class FindUserPacientes implements IFindUser {

	private Gson gson = new Gson();
	private String PATH_CADASTROS_PACIENTE = "Arquivos/cadastrosPaciente.json";

	@Override
	public Object findUser(String keyWord) {

		Object userFound = null;

		String cpfPacienteCadastrado;

		List<Paciente> cadastrosPaciente = readFile(Paciente[].class);

		for (Paciente pacienteCadastrado : cadastrosPaciente) {

			cpfPacienteCadastrado = pacienteCadastrado.getCPF();

			if (cpfPacienteCadastrado.equals(keyWord)) {

				userFound = pacienteCadastrado;
			}

		}

		return userFound;
	}

	@Override
	public Object findUser(String keyWord1, String keyWord2) {

		Object userFound = null;

		String cpfPacienteCadastrado;
		String senhaPacienteCadastrado;

		List<Paciente> cadastrosPaciente = readFile(Paciente[].class);

		for (Paciente pacienteCadastrado : cadastrosPaciente) {

			cpfPacienteCadastrado = pacienteCadastrado.getCPF();
			senhaPacienteCadastrado = pacienteCadastrado.getSenha();

			if (cpfPacienteCadastrado.equals(keyWord1) && senhaPacienteCadastrado.equals(keyWord2)) {

				userFound = pacienteCadastrado;
			}

		}

		return userFound;
	}

	@Override
	public <T extends Usuario> List<T> readFile(Class<T[]> type) {

		List<T> listCadastrosFromJson = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(PATH_CADASTROS_PACIENTE))) {

			T[] arrayCadastrosFromJson = gson.fromJson(br, type);

			listCadastrosFromJson = Arrays.asList(arrayCadastrosFromJson);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return listCadastrosFromJson;
	}

}
