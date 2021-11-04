package sistemaClinico;

import java.util.List;

import dao.EspecialidadeDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import dao.RecepcionistaDAO;
import findUser.FindEspecialidadeRegister;
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
		*/
		System.out.println("\nMedicos: \n");

		Medico m = new Medico("Lucas", "999", "119.163.263-98", "999913252",
				new Especialidade("Neurocirurgia", true));
		
		
		MedicoDAO.deleteMedico(m);
		
		List<Medico> medicos = MedicoDAO.getDoctors();
	
		medicos.forEach(medico -> {


			System.out.println(medico.getNome());

		});
		/*
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
		});*/
	}
}
