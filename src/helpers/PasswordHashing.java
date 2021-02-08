package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
	
	
	public String hashPassword(String password) {
		String result = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("SHA-256");
			md5.update(password.getBytes());
			byte[] resultado = md5.digest();
			
			
			//Pasamos cada byte a String
			StringBuilder sb = new StringBuilder();
			
			for(byte resultadoFinal: resultado) {
				sb.append(String.format("%02x", resultadoFinal));
			}
			
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}
