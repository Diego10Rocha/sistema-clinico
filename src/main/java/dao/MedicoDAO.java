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

import model.Medico;


public class MedicoDAO {

	private static final String PATH_CADASTROS_MEDICO = "Arquivos/cadastrosMedico.json";
	private static Gson gson = new Gson();
	
	public static List<Medico> getDoctors(){
		
		List<Medico> doctors = new ArrayList<>();

		try {
			File file = new File( PATH_CADASTROS_MEDICO );
			if(!file.exists())
				return doctors;
			
			BufferedReader br = new BufferedReader(new FileReader(PATH_CADASTROS_MEDICO));
			
			Type typeList = new TypeToken<ArrayList<Medico>>() {
			}.getType();

			doctors = gson.fromJson(br, typeList);
			br.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return doctors;
	}
	
	
	public static boolean insertDoctor(Medico doctor) {
		
		try {
			File file = new File( PATH_CADASTROS_MEDICO );
			List<Medico> doctors;
			
			if(!cpfIsUnique(doctor.getCPF()))
				throw new Exception("CPF já cadastrado");
			
			if(!file.exists()) {
				file.createNewFile();
				doctors = new ArrayList<>();
			}else {
				doctors = getDoctors();
			}
			doctors.add(doctor);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(doctors));
			bw.close();
			fw.close();
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static boolean updateDoctor(Medico doctor, Medico doctorOld) {
		try {
			File file = new File( PATH_CADASTROS_MEDICO );
			if(!file.exists())
				return false;
			
			List<Medico> doctors = getDoctors();
			doctors.set(doctors.indexOf(doctorOld), doctor);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(doctors));
			bw.close();
			fw.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteMedico(Medico doctor) {
		try {
			File file = new File( PATH_CADASTROS_MEDICO );
			if(!file.exists())
				return false;
			
			List<Medico> doctors = getDoctors();
			
			if(doctors.isEmpty())
				return false;
			doctors.remove(doctor);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(doctors));
			bw.close();
			fw.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Medico login(String cpf, String senha) {
		try {
			List<Medico> doctors = getDoctors();
			
			if(doctors.isEmpty())
				return null;
			Optional<Medico> loged = doctors.stream().filter(doctor -> 
			doctor.getCPF().equals(cpf) && doctor.getSenha().equals(senha)
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
			List<Medico> doctors = getDoctors();
			
			if(doctors.isEmpty())
				return false;
			
			Optional<Medico> doctorWithCpfRegistered = doctors.stream().filter(doctor -> 
				doctor.getCPF().equals(cpf)
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
