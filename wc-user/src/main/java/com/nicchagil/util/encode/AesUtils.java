package com.nicchagil.util.encode;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AesUtils {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 根据指定密钥加密
	 */
	public static String encode(String source, String key) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, AesUtils.getOriginSecretKey(key));
			
			byte[] targetBytes = cipher.doFinal(source.getBytes());
			return Base64.getEncoder().encodeToString(targetBytes);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据指定密钥解密
	 */
	public static String decode(String source, String key) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, AesUtils.getOriginSecretKey(key));
			
			byte[] sourceBytes = Base64.getDecoder().decode(source);
			byte[] targetBytes = cipher.doFinal(sourceBytes);
			return new String(targetBytes);
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据指定密钥获取Cipher
	 */
	public static SecretKey getOriginSecretKey(String key) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128, new SecureRandom(key.getBytes()));
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] encodedBytes = secretKey.getEncoded();
		SecretKey secondSecretKey = new SecretKeySpec(encodedBytes, "AES");
		return secondSecretKey;
	}
	
	@Test
	public void encodeAndDecode() {
		String source = "hello world";
		String key = "666666";
		
		String encodeTarget = AesUtils.encode(source, key);
		String decodeTarget = AesUtils.decode(encodeTarget, key);
		
		this.logger.info("source : {}", source);
		this.logger.info("encodeTarget : {}", encodeTarget);
		this.logger.info("target : {}", decodeTarget);
	}
	
}
