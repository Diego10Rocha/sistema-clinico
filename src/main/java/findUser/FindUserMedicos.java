package findUser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import interfaces.IFindUser;
import model.Medico;
import model.Usuario;

public class FindUserMedicos implements IFindUser {

	private Gson gson = new Gson();
	private String PATH_CADASTROS_MEDICO = "Arquivos/cadastrosMedico.json";

	@Override
	public Object findUser(String keyWord) {

		Object userFound = null;

		String cpfMedicoCadastrado;

		List<Medico> cadastrosMedico = readFile(Medico[].class);

		for (Medico medicoCadastrado : cadastrosMedico) {

			cpfMedicoCadastrado = medicoCadastrado.getCPF();

			if (cpfMedicoCadastrado.equals(keyWord)) {

				userFound = medicoCadastrado;
			}

		}

		return userFound;

	}

	@Override
	public Object findUser(String keyWord1, String keyWord2) {
		
		Object userFound = null;

		String cpfMedicoCadastrado;
		String senhaMedicoCadastrado;

		List<Medico> cadastrosMedico = readFile(Medico[].class);

		for (Medico medicoCadastrado : cadastrosMedico) {

			cpfMedicoCadastrado = medicoCadastrado.getCPF();
			senhaMedicoCadastrado = medicoCadastrado.getSenha();

			if (cpfMedicoCadastrado.equals(keyWord1) && senhaMedicoCadastrado.equals(senhaMedicoCadastrado)) {

				userFound = medicoCadastrado;
			}

		}

		return userFound;
	}

	@Override
	public <T extends Usuario> List<T> readFile(Class<T[]> type) {

		List<T> listCadastrosFromJson = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(PATH_CADASTROS_MEDICO))) {

			T[] arrayCadastrosFromJson = gson.fromJson(br, type);

			listCadastrosFromJson = Arrays.asList(arrayCadastrosFromJson);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return listCadastrosFromJson;
	}

}
