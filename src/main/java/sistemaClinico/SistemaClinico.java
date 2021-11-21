package sistemaClinico;

import java.util.List;

import dao.AgendaConsultaDAO;
import dao.ConsultaDAO;
import dao.EspecialidadeDAO;
import dao.MedicoDAO;
import dao.PacienteDAO;
import dao.RecepcionistaDAO;
import findUser.FindUserRegister;
import login.Login;
import model.AgendaConsulta;
import model.Consulta;
import model.Especialidade;
import model.Medico;
import model.Paciente;
import model.Recepcionista;
import resultLoginTry.ResultLoginTry;

public class SistemaClinico {
	public static void main(String[] args) {
		/*
		 * System.out.println("Pacientes: \n");
		 * 
		 * List<Paciente> pacientes = PacienteDAO.getPatients();
		 * pacientes.forEach(paciente -> { System.out.println(paciente.getNome()); });
		 * 
		 * 
		 * 
		 * List<Medico> medicos = MedicoDAO.getDoctors();
		 * 
		 * medicos.forEach(medico -> {
		 * 
		 * 
		 * System.out.println(medico.getNome());
		 * 
		 * });
		 * 
		 * System.out.println("\nRecepcionistas: \n");
		 * 
		 * Recepcionista r = new Recepcionista("Vivian", "111.263.263-98", "938");
		 * RecepcionistaDAO.insertReceptionist(r);
		 * System.out.println(RecepcionistaDAO.login("111.263.263-98", "938"));
		 * List<Recepcionista> recepcionistas = RecepcionistaDAO.getReceptionists();
		 * recepcionistas.forEach(recepcionista -> {
		 * System.out.println(recepcionista.getNome()); });
		 * 
		 */

		/**
		 * 
		 * ResultLoginTry resultLogin;
		 * 
		 * resultLogin = Login.makeLogin("019.263.263-98", "999");
		 * 
		 * System.out.println(Login.getCPF_userLogged());
		 * System.out.println(resultLogin.getValue());
		 */
		/*
		System.out.println("\nEspecialidades: \n");

		if (EspecialidadeDAO.insertSpecialty(new Especialidade("Pediatra", false))) {
			System.out.println("a");
		}
		List<Especialidade> especialidades = EspecialidadeDAO.getSpecialties();
		especialidades.forEach(especialidade -> {
			System.out.println(especialidade.getNome());
		});*/
		
		AgendaConsulta agendaConsulta = new AgendaConsulta();
		Medico medico = MedicoDAO.getDoctors().get(0);
		
		agendaConsulta.setData("21/11/2021");
		agendaConsulta.setHora(medico.getHoraDisponivelConsulta());
		agendaConsulta.setMedico(medico);
		
		AgendaConsultaDAO.insertAgendaConsulta(agendaConsulta);

	}
}
