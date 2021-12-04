package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class ConnectionFile<T> {

	private Gson gson = new Gson();
	private final String PATH;
	private final Class<T[]> type;

	public ConnectionFile(Class<T[]> type, String PATH) {

		this.type = type;
		this.PATH = PATH;

	}

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
