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

import model.Recepcionista;

public class RecepcionistaDAO {
	
	private static final String PATH_CADASTROS_RECPCIONISTA = "Arquivos/cadastrosRecepcionista.json";
	private static Gson gson = new Gson();
	
	public static List<Recepcionista> getReceptionists(){
		
		List<Recepcionista> receptionists = new ArrayList<>();

		try {
			File file = new File( PATH_CADASTROS_RECPCIONISTA );
			if(!file.exists())
				return receptionists;
			
			BufferedReader br = new BufferedReader(new FileReader(PATH_CADASTROS_RECPCIONISTA));
			
			Type typeList = new TypeToken<ArrayList<Recepcionista>>() {
			}.getType();

			receptionists = gson.fromJson(br, typeList);
			br.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return receptionists;
	}
	
	
	public static boolean insertReceptionist(Recepcionista receptionist) {
		
		try {
			File file = new File( PATH_CADASTROS_RECPCIONISTA );
			List<Recepcionista> receptionists;
			
			if(!cpfIsUnique(receptionist.getCPF()))
				throw new Exception("CPF já cadastrado");
			
			if(!file.exists()) {
				file.createNewFile();
				receptionists = new ArrayList<>();
			}else {
				receptionists = getReceptionists();
			}
			receptionists.add(receptionist);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(receptionists));
			bw.close();
			fw.close();
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static boolean updateReceptionist(Recepcionista receptionist, Recepcionista receptionistOld) {
		try {
			File file = new File( PATH_CADASTROS_RECPCIONISTA );
			if(!file.exists())
				return false;
			
			List<Recepcionista> receptionists = getReceptionists();
			receptionists.set(receptionists.indexOf(receptionistOld), receptionist);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(receptionists));
			bw.close();
			fw.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteReceptionist(Recepcionista receptionist) {
		try {
			File file = new File( PATH_CADASTROS_RECPCIONISTA );
			if(!file.exists())
				return false;
			
			List<Recepcionista> receptionists = getReceptionists();
			
			if(receptionists.isEmpty())
				return false;
			receptionists.remove(receptionist);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(receptionists));
			bw.close();
			fw.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Recepcionista login(String cpf, String senha) {
		try {
			List<Recepcionista> receptionists = getReceptionists();
			
			if(receptionists.isEmpty())
				return null;
			Optional<Recepcionista> loged = receptionists.stream().filter(receptionist -> 
			receptionist.getCPF().equals(cpf) && receptionist.getSenha().equals(senha)
			).findFirst();
			
			if(!loged.isPresent())
				return null;
			return loged.get();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static boolean cpfIsUnique(String cpf) {
		try {
			List<Recepcionista> receptionists = getReceptionists();
			
			Optional<Recepcionista> receptionistWithCpfRegistered = receptionists.stream().filter(receptionist -> 
				receptionist.getCPF().equals(cpf)
			).findFirst();
			
			if(receptionistWithCpfRegistered.isPresent())
				return false;
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
