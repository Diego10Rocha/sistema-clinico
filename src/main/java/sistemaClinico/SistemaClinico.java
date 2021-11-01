package sistemaClinico;

import java.util.List;

import dao.PacienteDAO;
import model.Paciente;

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
		Paciente p = new Paciente("Diegão", "123", "036.263.263-98", "2021/03/20");
		PacienteDAO.insertPaciente(p);
		List<Paciente> pacientes = PacienteDAO.getPacientes();
		pacientes.forEach(paciente -> {
			System.out.println(paciente.getNome());
		});
	}
}
