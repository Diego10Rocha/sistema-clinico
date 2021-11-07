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
		/*
		System.out.println("Pacientes: \n");

		List<Paciente> pacientes = PacienteDAO.getPatients();
		pacientes.forEach(paciente -> {
			System.out.println(paciente.getNome());
		});
		
		
		
		List<Medico> medicos = MedicoDAO.getDoctors();
	
		medicos.forEach(medico -> {


			System.out.println(medico.getNome());

		});
		
		System.out.println("\nRecepcionistas: \n");

		Recepcionista r = new Recepcionista("Vivian", "111.263.263-98", "938");
		RecepcionistaDAO.insertReceptionist(r);
		System.out.println(RecepcionistaDAO.login("111.263.263-98", "938"));
		List<Recepcionista> recepcionistas = RecepcionistaDAO.getReceptionists();
		recepcionistas.forEach(recepcionista -> {
			System.out.println(recepcionista.getNome());
		});
		
		*/
		
		System.out.println("\nEspecialidades: \n");

		Especialidade eNew = new Especialidade("Cardiologia", true);
		
		
		List<Especialidade> especialidades = EspecialidadeDAO.getSpecialties();
		especialidades.forEach(especialidade -> {
			System.out.println(especialidade.getNome());
		});
	}
}
