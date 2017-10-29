package com.nyp.shopping.common.utils;
/**
 * 
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nyp.shopping.common.constants.ApplicationConstants;

/**
 * @author Java Developer
 *
 */
@Component
public class EncryptionUtils {

	static final Logger logger = LoggerFactory.getLogger(EncryptionUtils.class);

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(encrypt("password".getBytes(ApplicationConstants.UTF8), "saltFromProperties"));

	}

	public static String encrypt(byte[] textToBeEncrypted, String salt) {

		logger.debug("Entered sha1()");
		byte[] sha1hash = new byte[40];
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(salt.getBytes(ApplicationConstants.UTF8));
			sha1hash = md.digest(textToBeEncrypted);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return convToHex(sha1hash);
	}

	static String convToHex(byte[] data) {

		logger.debug("Entered convToHex()");
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	public static String generatePassword() {

		logger.debug("Entered generatePassword()");
		return UUID.randomUUID().toString();
	}

}
