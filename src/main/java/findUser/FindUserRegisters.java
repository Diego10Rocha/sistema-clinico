package findUser;

import java.util.List;

import interfaces.IFindUser;
import model.Usuario;

public class FindUserRegisters implements IFindUser {

	private FindUserPacientes findUserPacientes = new FindUserPacientes();
	private FindUserMedicos findUserMedicos = new FindUserMedicos();
	private FindUserRecpcionista findUserRecepcionista = new FindUserRecpcionista();

	@Override
	public Object findUser(String keyWord) {

		Object userFoundOnPacientes = findUserPacientes.findUser(keyWord);
		Object userFoundOnMedicos = findUserMedicos.findUser(keyWord);
		Object userFoundOnRecepcionista = findUserRecepcionista.findUser(keyWord);

		if (userFoundOnPacientes != null)
			
			return userFoundOnPacientes;

		else if (userFoundOnMedicos != null)
			
			return userFoundOnMedicos;

		else
			return userFoundOnRecepcionista;

	}

	@Override
	public Object findUser(String keyWord1, String keyWord2) {
		
		Object userFoundOnPacientes = findUserPacientes.findUser(keyWord1, keyWord2);
		Object userFoundOnMedicos = findUserMedicos.findUser(keyWord1, keyWord2);
		Object userFoundOnRecepcionista = findUserRecepcionista.findUser(keyWord1, keyWord2);

		if (userFoundOnPacientes != null)
			
			return userFoundOnPacientes;

		else if (userFoundOnMedicos != null)
			
			return userFoundOnMedicos;

		else
			return userFoundOnRecepcionista;
	}

	@Override
	public <T extends Usuario> List<T> readFile(Class<T[]> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
