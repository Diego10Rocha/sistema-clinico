package findUser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Especialidade;

public class FindEspecialidadeRegister {

	private final String PATH_CADASTROS_ESPECIALIDADE = "Arquivos/cadastrosEspecialidade.json";;
	private Gson gson = new Gson();

	public Especialidade find(String nameTarget) {

		Especialidade especialidadeFound = null;

		String nameEspecialidadeCadastrada;

		List<Especialidade> cadastros = readFile();

		for (Especialidade especialidadeCadastrada : cadastros) {

			nameEspecialidadeCadastrada = especialidadeCadastrada.getNome();

			if (nameEspecialidadeCadastrada.equals(nameTarget)) {

				especialidadeFound = especialidadeCadastrada;
			}

		}

		return especialidadeFound;

	}

	private List<Especialidade> readFile() {
		
		List<Especialidade> cadastrosFromJson = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(PATH_CADASTROS_ESPECIALIDADE))) {

			Type typeList = new TypeToken<ArrayList<Especialidade>>() {
			}.getType();

			cadastrosFromJson = gson.fromJson(br, typeList);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return cadastrosFromJson;
		
	}
}
