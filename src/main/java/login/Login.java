/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programação
Concluido em: 07/12/2021
Declaro que este código foi elaborado por Diego Cerqueira e Joanderson Santos em dupla e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package login;

import dao.MedicoDAO;
import dao.RecepcionistaDAO;
import instanceType.InstanceType;
import model.Usuario;
import resultLoginTry.ResultLoginTry;

/**
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */
public class Login {

	private static String CPF_userLogged;
	private static InstanceType instanceCpfWasRegistered;

	
	/**
	 * Metodo de login
	 * @param cpf
	 * @param senha
	 * @return ResultLoginTry
	 */
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

	/**
	 * Verifica se um cpf já está registrado
	 * @param cpf
	 * @return boolean
	 */
	private static boolean isCpfRegistered(String cpf) {

		boolean isCpfRegistered = false;

		if (MedicoDAO.cpfAlreadyRegistered(cpf)
				|| RecepcionistaDAO.cpfAlreadyRegistered(cpf)) {

			isCpfRegistered = true;

			setInstanceCpfWasRegistered(cpf);
		}

		return isCpfRegistered;
	}

	/**
	 * Verifica de quem é o cpf que está sendo usado para login no sistema e o guarda
	 * @param cpf
	 */
	public static void setInstanceCpfWasRegistered(String cpf) {
		
		if (MedicoDAO.cpfAlreadyRegistered(cpf)) {

			instanceCpfWasRegistered = InstanceType.MEDICO;
		}
		
		else if (RecepcionistaDAO.cpfAlreadyRegistered(cpf)) {

			instanceCpfWasRegistered = InstanceType.RECEPCIONISTA;
		}
		
	}
	
	/**
	 * Retorna qual Usuário é o dono de um cpf
	 * @param cpf
	 * @return
	 */
	private static Usuario setUserOwnerCpf(String cpf) {

		Usuario userOwnerCPF = null;

		if (instanceCpfWasRegistered == InstanceType.MEDICO) {

			userOwnerCPF = MedicoDAO.findByCPF(cpf);
		}

		else if (instanceCpfWasRegistered == InstanceType.RECEPCIONISTA) {

			userOwnerCPF = RecepcionistaDAO.findByCPF(cpf);

		}

		return userOwnerCPF;
	}

	/**
	 * Verifica se a senha está correta
	 * @param temp
	 * @param senha
	 * @return boolean
	 */
	private static boolean isPasswordCorrect(Usuario temp, String senha) {

		return temp.getSenha().equals(senha);
	}

	public static String getCPF_userLogged() {
		return CPF_userLogged;
	}

	public static void setCPF_userLogged(String cPF_userLogged) {
		CPF_userLogged = cPF_userLogged;
	}

	/**
	 * Retorna qual o usuário com um determinado cpf
	 * @return InstanceType
	 */
	public static InstanceType getInstanceCpfWasRegistered() {

		return instanceCpfWasRegistered;
	}

}
