package sistemaClinico;

import findUser.FindUserRegisters;

public class SistemaClinico {
	public static void main(String[] args) {

		FindUserRegisters file = new FindUserRegisters();

		Object userFound = file.findUser("192.344.21", "131");

		System.out.println(userFound.getClass());
	}
}
