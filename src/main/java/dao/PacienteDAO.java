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

import model.Paciente;

public class PacienteDAO {

	private final static String PATH_CADASTROS_PACIENTE = "Arquivos/cadastrosPaciente.json";
	private static Gson gson = new Gson();
	
	public static List<Paciente> getPatients(){
		
		List<Paciente> patients = new ArrayList<>();

		try {
			File arquivo = new File( PATH_CADASTROS_PACIENTE );
			if(!arquivo.exists())
				return patients;
			
			BufferedReader br = new BufferedReader(new FileReader(PATH_CADASTROS_PACIENTE));
			
			Type typeList = new TypeToken<ArrayList<Paciente>>() {
			}.getType();

			patients = gson.fromJson(br, typeList);
			br.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return patients;
	}
	
	
	public static boolean insertPatient(Paciente patient) {
		
		try {
			File file = new File( PATH_CADASTROS_PACIENTE );
			List<Paciente> patients;
			
			if(!cpfIsUnique(patient.getCPF())) {
				throw new Exception("CPF já cadastrado");
			}
			if(!file.exists()) {
				file.createNewFile();
				patients = new ArrayList<>();
			}else {
				patients = getPatients();
			}
			patients.add(patient);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(patients));
			bw.close();
			fw.close();
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static boolean updatePatient(Paciente patient, Paciente patientOld) {
		try {
			File file = new File( PATH_CADASTROS_PACIENTE );
			if(!file.exists())
				return false;
			
			List<Paciente> patients = getPatients();
			patients.set(patients.indexOf(patientOld), patient);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(patients));
			bw.close();
			fw.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deletePatient(Paciente paciente) {
		try {
			File file = new File( PATH_CADASTROS_PACIENTE );
			if(!file.exists())
				return false;
			
			List<Paciente> patients = getPatients();
			
			if(patients.isEmpty())
				return false;
			patients.remove(paciente);
			
			FileWriter fw = new FileWriter( file );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(gson.toJson(patients));
			bw.close();
			fw.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Paciente login(String cpf, String senha) {
		try {
			List<Paciente> patients = getPatients();
			
			if(patients.isEmpty())
				return null;
			Optional<Paciente> loged = patients.stream().filter(patient -> 
				patient.getCPF().equals(cpf) && patient.getSenha().equals(senha)
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
			List<Paciente> patients = getPatients();
			
			if(patients.isEmpty())
				return false;
			
			Optional<Paciente> pacienteWithCpfRegistered = patients.stream().filter(patient -> 
				patient.getCPF().equals(cpf)
			).findFirst();
			
			if(pacienteWithCpfRegistered.isPresent())
				return false;
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
