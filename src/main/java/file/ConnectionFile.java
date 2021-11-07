package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import instanceType.InstanceType;
import model.Especialidade;
import model.Medico;
import model.Paciente;
import model.Recepcionista;

public class ConnectionFile {

	private Gson gson = new Gson();

	private final String PATH_CADASTROS_PACIENTE = "Arquivos/Pacientes.json";
	private final String PATH_CADASTROS_MEDICO = "Arquivos/Medicos.json";
	private final String PATH_CADASTROS_RECPCIONISTA = "Arquivos/Recepcionistas.json";
	private final String PATH_CADASTROS_ESPECIALIDADE = "Arquivos/Especialidades.json";
	private final String PATH_CADASTROS_PRONTUARIO = "Arquivos/Prontuarios.json";

	public ConnectionFile() {

	}

	public boolean writer(Object obj) {

		String pathToWriter = getPath(obj);

		String json;

		List<Object> cadastros = readFile(pathToWriter);

		cadastros.add(obj);
		
		File file = new File( pathToWriter );
		
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		try (FileWriter writer = new FileWriter(pathToWriter)) {

			json = gson.toJson(cadastros);

			writer.write(json);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return true;

	}

	public boolean reWriter(Object obj, String path) {

		String json;

		try (FileWriter writer = new FileWriter(path)) {

			json = gson.toJson(obj);

			writer.write(json);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return true;

	}

	public String getPath(Object obj) {

		String path;

		InstanceType instanceTypeObj = getInstanceType(obj);

		if (instanceTypeObj == InstanceType.PACIENTE) {

			path = PATH_CADASTROS_PACIENTE;
		}

		else if (instanceTypeObj == InstanceType.MEDICO) {

			path = PATH_CADASTROS_MEDICO;
		}

		else if (instanceTypeObj == InstanceType.RECEPCIONISTA) {

			path = PATH_CADASTROS_RECPCIONISTA;
		}

		else if (instanceTypeObj == InstanceType.ESPECIALIDADE) {

			path = PATH_CADASTROS_ESPECIALIDADE;
		}

		else

			path = PATH_CADASTROS_PRONTUARIO;

		return path;

	}

	private InstanceType getInstanceType(Object obj) {

		InstanceType instanceTypeObj = null;

		if (obj instanceof Paciente) {

			instanceTypeObj = InstanceType.PACIENTE;
		}

		else if (obj instanceof Medico) {

			instanceTypeObj = InstanceType.MEDICO;
		}

		else if (obj instanceof Recepcionista) {

			instanceTypeObj = InstanceType.RECEPCIONISTA;
		}

		else if (obj instanceof Especialidade) {

			instanceTypeObj = InstanceType.ESPECIALIDADE;
		}

		else
			instanceTypeObj = InstanceType.PRONTUARIO;

		return instanceTypeObj;
	}

	public List<Object> readFile(String path) {

		List<Object> cadastrosFromJson = new ArrayList<>();
		
		File file = new File( path );
		
		if(!file.exists())
			try {
				file.createNewFile();
				return new ArrayList<Object>();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			Type typeList = new TypeToken<ArrayList<Object>>() {
			}.getType();

			cadastrosFromJson = gson.fromJson(br, typeList);
			
			if(cadastrosFromJson == null)
				return new ArrayList<Object>();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return cadastrosFromJson;
	}

	public <T> List<T> readFile(String path, Class<T[]> type) {
		
		File file = new File( path );
		
		if(!file.exists())
			try {
				file.createNewFile();
				return new ArrayList<T>();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			T[] arrayCadastrosFromJson = gson.fromJson(br, type);
			
			if(arrayCadastrosFromJson == null)
				return new ArrayList<T>();

			return new ArrayList<T>(Arrays.asList(arrayCadastrosFromJson));

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

}
