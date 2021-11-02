package sistemaClinico;

import java.util.List;

import dao.EspecialidadeDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import dao.RecepcionistaDAO;
import model.Especialidade;
import model.Medico;
import model.Paciente;
import model.Recepcionista;

public class SistemaClinico {
	public static void main(String[] args) {

		/*FindUserRegisters file = new FindUserRegisters();

		Object userFound = file.findUser("192.344.21", "131");

		System.out.println(userFound.getClass());*/
	/*	
		FileUserRegister file = new FileUserRegister();
		List<Object> list = file.readFile("Arquivos/cadastrosPaciente.json");
		list.forEach(ob ->{System.out.println(ob.toString());});
		*/
		System.out.println("Pacientes: \n");
		Paciente p = new Paciente("Bonitão", "123", "036.263.263-98", "2021/03/20");
		PacienteDAO.insertPatient(p);
		List<Paciente> pacientes = PacienteDAO.getPatients();
		pacientes.forEach(paciente -> {
			System.out.println(paciente.getNome());
		});
		
		System.out.println("\nMedicos: \n");
		
		Medico m = new Medico("Bonitão", "123", "036.263.263-98", "34133513252", new Especialidade("Ginecologista", true));
		MedicoDAO.insertDoctor(m);
		List<Medico> medicos = MedicoDAO.getDoctors();
		medicos.forEach(medico -> {
			System.out.println(medico.getNome());
		});
		
		System.out.println("\nRecepcionistas: \n");
		
		Recepcionista r = new Recepcionista("Bonitão", "123", "036.263.263-98");
		RecepcionistaDAO.insertReceptionist(r);
		List<Recepcionista> recepcionistas = RecepcionistaDAO.getReceptionists();
		recepcionistas.forEach(recepcionista -> {
			System.out.println(recepcionista.getNome());
		});
		
		System.out.println("\nEspecialidades: \n");
		
		Especialidade e = new Especialidade("Pediatra", true);
		EspecialidadeDAO.insertSpecialty(e);
		List<Especialidade> especialidades = EspecialidadeDAO.getSpecialties();
		especialidades.forEach(especialidade -> {
			System.out.println(especialidade.getNome());
		});
	}
}
