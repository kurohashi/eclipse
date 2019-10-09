import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SamEncr {

	public static void main(String[] args) {
		try {
			String text = "Hello";
			String key = "p^5*/-+~m!)\"v@#$";
			
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(text.getBytes());
			System.err.println("Encrypted: " + new String(encrypted));
			
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			String decrypted = new String(cipher.doFinal(encrypted));
			System.err.println("Decrypted: " + decrypted);
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
