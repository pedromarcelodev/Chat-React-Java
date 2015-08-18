package br.com.chatreact.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashString {

	public static String sha256(String str) {
		try {
			StringBuffer buffer = new StringBuffer();
			byte[] bytes;
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			bytes = md.digest();
			for (byte b : bytes) {
				buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}