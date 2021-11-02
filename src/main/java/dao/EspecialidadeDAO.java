package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Especialidade;


public class EspecialidadeDAO {
	
	private static final String PATH_CADASTROS_ESPECIALIDADE = "Arquivos/cadastrosEspecialidade.json";
	private static Gson gson = new Gson();
	
	public static List<Especialidade> getSpecialties(){
		
		List<Especialidade> specialties = new ArrayList<>();

		try {
			File file = new File( PATH_CADASTROS_ESPECIALIDADE );
			if(!file.exists())
				return specialties;
			
			BufferedReader br = new BufferedReader(new FileReader(PATH_CADASTROS_ESPECIALIDADE));
			
			Type typeList = new TypeToken<ArrayList<Especialidade>>() {
			}.getType();

			specialties = gson.fromJson(br, typeList);
			br.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return specialties;
	}
	
	
	public static boolean insertSpecialty(Especialidade specialty) {
		
		try {
			File file = new File( PATH_CADASTROS_ESPECIALIDADE );
			List<Especialidade> specialties;
			
			if(!specialtyIsUnique(specialty.getNome()))
				throw new Exception("Especialidade já cadastrada");
			
			if(!file.exists()) {
				file.createNewFile();
				specialties = new ArrayList<>();
			}else {
				specialties = getSpecialties();
			}
			specialties.add(specialty);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(specialties));
			bw.close();
			fw.close();
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static boolean updateSpecialty(Especialidade specialty, Especialidade specialtyOld) {
		try {
			File file = new File( PATH_CADASTROS_ESPECIALIDADE );
			if(!file.exists())
				return false;
			
			List<Especialidade> specialties = getSpecialties();
			specialties.set(specialties.indexOf(specialtyOld), specialty);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(specialties));
			bw.close();
			fw.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteSpecialty(Especialidade specialty) {
		try {
			File file = new File( PATH_CADASTROS_ESPECIALIDADE );
			if(!file.exists())
				return false;
			
			List<Especialidade> specialties = getSpecialties();
			
			if(specialties.isEmpty())
				return false;
			specialties.remove(specialty);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(specialties));
			bw.close();
			fw.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static boolean specialtyIsUnique(String nome) {
		try {
			List<Especialidade> specialties = getSpecialties();
			
			if(specialties.isEmpty())
				return false;
			
			Optional<Especialidade> doctorWithCpfRegistered = specialties.stream().filter(specialty -> 
			specialty.getNome().equals(nome)
			).findFirst();
			
			if(doctorWithCpfRegistered.isPresent())
				return false;
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
