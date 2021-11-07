package login;

import dao.MedicoDAO;
import dao.PacienteDAO;
import dao.RecepcionistaDAO;

public class Login {

	private static String CPF_userLogged;

	public static void makeLogin(String cpf, String senha) throws Exception {

		if (PacienteDAO.login(cpf, senha) != null) {

			setCPF_userLogged(cpf);

			openScreenLoginPaciente();

		}

		else if (MedicoDAO.login(cpf, senha) != null) {

			setCPF_userLogged(cpf);

			openScreenLoginMedico();
		}

		else if (RecepcionistaDAO.login(cpf, senha) != null) {

			setCPF_userLogged(cpf);

			openScreenLoginRecepcionista();
		}

		else {

			throw new Exception("Cpf e/ou senha inválidos");
		}

	}

	private static void openScreenLoginRecepcionista() {
		// TODO Auto-generated method stub

	}

	private static void openScreenLoginMedico() {
		// TODO Auto-generated method stub

	}

	private static void openScreenLoginPaciente() {
		// TODO Auto-generated method stub

	}

	public static String getCPF_userLogged() {
		return CPF_userLogged;
	}

	public static void setCPF_userLogged(String cPF_userLogged) {
		CPF_userLogged = cPF_userLogged;
	}

}
