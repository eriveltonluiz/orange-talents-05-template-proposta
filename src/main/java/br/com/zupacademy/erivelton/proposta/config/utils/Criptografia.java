package br.com.zupacademy.erivelton.proposta.config.utils;

import java.nio.charset.Charset;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class Criptografia {
	
	private static String chave = new String(Hex.encode("salt".getBytes(Charset.forName("utf-8"))));

	public static String criptografar(String documento) {
		TextEncryptor encryptor = Encryptors.text("password", chave);
		
		String criptografado = encryptor.encrypt(documento);
		
		return new String(criptografado);
	}
	
	public static String descriptografar(String documentoCriptografado) {
		TextEncryptor encryptor = Encryptors.text("password", chave);
		
		String descriptografado = encryptor.decrypt(documentoCriptografado);
		
		return new String(descriptografado);
	}
}
