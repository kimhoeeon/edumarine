package com.mtf.edumarine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

@SpringBootApplication
public class EduMarineApplication {

	public static void main(String[] args) {

		disableAddressCache();

		SpringApplication.run(EduMarineApplication.class, args);

		/*String password = "threaten*00";

		//salt값 생성
		String salt = Salt();
		System.out.println(salt);

		//암호화
		String pw_encrypt = SHA512(password, salt);
		System.out.println(pw_encrypt);*/
	}

	private static void disableAddressCache() {
		Security.setProperty("networkaddress.cache.ttl", "0");
		Security.setProperty("networkaddress.cache.negative.ttl", "0");
	}

	/*public static String Salt() {

		String salt="";
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			random.nextBytes(bytes);
			salt = new String(Base64.getEncoder().encode(bytes));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return salt;
	}

	public static String SHA512(String password, String hash) {
		String salt = hash+password;
		String hex = null;
		try {
			MessageDigest msg = MessageDigest.getInstance("SHA-512");
			msg.update(salt.getBytes());

			hex = String.format("%128x", new BigInteger(1, msg.digest()));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hex;
	}*/

}
