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

public class FindProntuarioRegister {

	private final String PATH_CADASTROS_PRONTUARIO = "Arquivos/cadastrosProntuario.json";;
	private Gson gson = new Gson();

	public Prontuario find(String ID_PACIENTE) {

		Prontuario prontuarioFound = null;

		String ID_PACIENTE_Cadastrado;

		List<Prontuario> cadastros = readFile();

		for (Prontuario prontuario : cadastros) {

			ID_PACIENTE_Cadastrado = prontuario.getID_PACIENTE();

			if (ID_PACIENTE_Cadastrado.equals(ID_PACIENTE)) {

				prontuarioFound = prontuario;
			}

		}

		return prontuarioFound;

	}

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
