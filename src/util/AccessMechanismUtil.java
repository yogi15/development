package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccessMechanismUtil {

	static public String encodePassword(String passwordToHash) {

		String generatedPassword = null;

		try {

			String salt = getSalt();

			generatedPassword = getPasswordWithSalt(passwordToHash, salt);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		}

		return generatedPassword;

	}

	public static String getPasswordWithSalt(String passwordToHash, String salt) {

		MessageDigest md = null;

		try {
			
			md = MessageDigest.getInstance("SHA-256");
		
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			
		}
		
		md.update(salt.getBytes());

		byte[] bytes = md.digest(passwordToHash.getBytes());

		StringBuilder hexString = new StringBuilder();

		for (int i = 0; i < bytes.length; i++) {
			
			String hex=Integer.toHexString(0xff & bytes[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
		
		}
		System.out.println("---------" + hexString.toString());
		return hexString.toString();
	
	}

	public static String getSalt() throws NoSuchAlgorithmException {

		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

		byte[] salt = new byte[16];
		sr.nextBytes(salt);

		return salt.toString();
	}

}
