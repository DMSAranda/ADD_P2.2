package dam2.add.p22.lib;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class Encriptacion {
	
	public static String encriptador(String clave) {

		// ALTA
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(clave);
		
		return encryptedPassword;
	}
	
	public static boolean desencriptador(String clave, String encryptedPassword) {
		
		boolean correcta;
		
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		
		if (passwordEncryptor.checkPassword(clave, encryptedPassword)) {
			correcta = true; // correct!
		} else {
			correcta = false;
		}
		
		return correcta;
		
	}


}
