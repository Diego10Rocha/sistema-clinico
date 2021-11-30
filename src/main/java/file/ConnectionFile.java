package file;

import java.io.BufferedReader;
import java.io.File;
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

		File file = new File(PATH);

		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

	public List<T> readFile() {

		File file = new File(PATH);

		if (!file.exists())
			try {
				file.createNewFile();
				return new ArrayList<T>();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
