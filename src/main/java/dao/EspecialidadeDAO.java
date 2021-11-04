package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

import file.ConnectionFile;
import findUser.FindEspecialidadeRegister;
import model.Especialidade;
import model.Recepcionista;


public class EspecialidadeDAO {
	
	private static FindEspecialidadeRegister findEspecialidade = new FindEspecialidadeRegister();
	private static final String PATH_CADASTROS_ESPECIALIDADE = "Arquivos/cadastrosEspecialidade.json";
	private static ConnectionFile connectionFile = new ConnectionFile();
	private static Gson gson = new Gson();
	
	public static List<Especialidade> getSpecialties(){
		
		List<Especialidade> specialties = connectionFile.readFile(PATH_CADASTROS_ESPECIALIDADE, Especialidade[].class);

		return specialties;
	}
	
	
	public static boolean insertSpecialty(Especialidade specialty) {
		
		try {

			if (!specialtyIsUnique(specialty.getNome())) {

				throw new Exception("Especialidade já cadastrada");
			}

			connectionFile.writer(specialty);

			return true;

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return false;

	}
	
	public static boolean updateSpecialty(Especialidade specialty, Especialidade specialtyOld) {
		
		List<Especialidade> specialties = getSpecialties();
		
		specialties.set(specialties.indexOf(specialtyOld), specialty);

		return connectionFile.reWriter(specialties, PATH_CADASTROS_ESPECIALIDADE);
	}
	
	public static boolean deleteSpecialty(Especialidade specialty) {
		
		boolean isDeletada;

		List<Especialidade> specialties = getSpecialties();
		
		isDeletada = specialties.remove(specialty);

		connectionFile.reWriter(specialties, PATH_CADASTROS_ESPECIALIDADE);

		return isDeletada;
	}
	
	private static boolean specialtyIsUnique(String nome) {
		
		Especialidade temp = findEspecialidade.find(nome);

		return temp == null;
	}

}
