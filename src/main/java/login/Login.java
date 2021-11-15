package login;

import dao.MedicoDAO;
import dao.PacienteDAO;
import dao.RecepcionistaDAO;
import instanceType.InstanceType;
import model.Usuario;
import resultLoginTry.ResultLoginTry;

public class Login {

	private static String CPF_userLogged;
	private static InstanceType instanceCpfWasRegistered;

	public static ResultLoginTry makeLogin(String cpf, String senha) {

		boolean isCpfRegistered = isCpfRegistered(cpf);

		if (isCpfRegistered) {

			boolean isPasswordCorrect;

			Usuario userTemp = setUserOwnerCpf(cpf);

			isPasswordCorrect = isPasswordCorrect(userTemp, senha);

			if (!isPasswordCorrect) {

				return ResultLoginTry.FAIL_PASSWORD;
			}

			setCPF_userLogged(cpf);

			return ResultLoginTry.SUCCESS;
		}

		return ResultLoginTry.FAIL_CPF;
	}

	private static boolean isCpfRegistered(String cpf) {

		boolean isCpfRegistered = false;

		if (PacienteDAO.cpfAlreadyRegistered(cpf) || MedicoDAO.cpfAlreadyRegistered(cpf)
				|| RecepcionistaDAO.cpfAlreadyRegistered(cpf)) {

			isCpfRegistered = true;

			setInstanceCpfWasRegistered(cpf);
		}

		return isCpfRegistered;
	}

	public static void setInstanceCpfWasRegistered(String cpf) {
		
		if (PacienteDAO.cpfAlreadyRegistered(cpf)) {

			instanceCpfWasRegistered = InstanceType.PACIENTE;
		}
		
		else if (MedicoDAO.cpfAlreadyRegistered(cpf)) {

			instanceCpfWasRegistered = InstanceType.MEDICO;
		}
		
		else if (RecepcionistaDAO.cpfAlreadyRegistered(cpf)) {

			instanceCpfWasRegistered = InstanceType.RECEPCIONISTA;
		}
		
	}

	private static Usuario setUserOwnerCpf(String cpf) {

		Usuario userOwnerCPF = null;

		if (instanceCpfWasRegistered == InstanceType.PACIENTE) {

			userOwnerCPF = PacienteDAO.findByCPF(cpf);

		}

		else if (instanceCpfWasRegistered == InstanceType.MEDICO) {

			userOwnerCPF = MedicoDAO.findByCPF(cpf);
		}

		else if (instanceCpfWasRegistered == InstanceType.RECEPCIONISTA) {

			userOwnerCPF = RecepcionistaDAO.findByCPF(cpf);

		}

		return userOwnerCPF;
	}

	private static boolean isPasswordCorrect(Usuario temp, String senha) {

		return temp.getSenha().equals(senha);
	}

	public static String getCPF_userLogged() {
		return CPF_userLogged;
	}

	public static void setCPF_userLogged(String cPF_userLogged) {
		CPF_userLogged = cPF_userLogged;
	}

	public static InstanceType getInstanceCpfWasRegistered() {

		return instanceCpfWasRegistered;
	}

}
