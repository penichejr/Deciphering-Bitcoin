package bitcoin;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class Taller {
	
	
	private final static String ALGORITMO = "RSA";
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		String mensaje  = " hola ";
		
		int opcion=2;
		
		if(opcion==1) {
		
		imprimir(mensaje.getBytes());
		
		
		KeyPairGenerator generador = KeyPairGenerator.getInstance(ALGORITMO);
		generador.initialize(1024);
		KeyPair keyPair = generador.generateKeyPair();
		PublicKey llavePublica = keyPair.getPublic();
		PrivateKey llavePrivada = keyPair.getPrivate();
		
		
		byte[] textoCifrado = cifrar(llavePublica, ALGORITMO, mensaje);
		imprimir(textoCifrado);
		
		byte[] textoDecifrado = decifrar(llavePrivada, ALGORITMO, textoCifrado);
		imprimir(textoDecifrado);
		
		String msjRecuperado= new String (textoDecifrado, StandardCharsets.UTF_8);
		
		System.out.println("Texto recuperado: "+msjRecuperado);
		
		}
		
		
		if(opcion==2)
		{
			System.out.println(mensaje);
			byte[] bytes = mensaje.getBytes();
			System.out.print("MD5 :");
			imprimirHexa(getDigest("MD5", bytes));
			System.out.print("SHA-1 :");
			imprimirHexa(getDigest("SHA-1", bytes));;
			
			
		}
		
		
		
		
	}
	
	
	public static byte[] getDigest(String algoritm, byte[] buffer) {
		
		try {
			MessageDigest digest = MessageDigest.getInstance(algoritm);
			digest.update(buffer);
			return digest.digest();
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static void imprimirHexa(byte[] byteArray) {
		String out= "";
		for (int i = 0; i < byteArray.length; i++) {
			if((byteArray[i] & 0xff) <= 0xf) {
				out += "0";
			}		
			out += Integer.toHexString(byteArray[i] & 0xff).toLowerCase();
		}
		
		System.out.println(out);
	}
	
	
	
	
	
	public static byte[] cifrar (Key llave, String algoritmo, String texto) {
		byte[] textoCifrado;
		
		try {
			Cipher cifrador = Cipher.getInstance(algoritmo);
			byte[] textoClaro = texto.getBytes();
			
			cifrador.init(Cipher.ENCRYPT_MODE, llave);
			textoCifrado = cifrador.doFinal(textoClaro);
			
			return textoCifrado;
		}
		
		catch (Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
			return null;
		}
	}
	
	
	
	
	public static byte[] decifrar(Key llave, String algoritmo, byte[] texto) {
		byte[] textoClaro;
		
		try {
			Cipher cifrador=Cipher.getInstance(algoritmo);
			cifrador.init(Cipher.DECRYPT_MODE, llave);
			textoClaro = cifrador.doFinal(texto);
		}
		catch(Exception e) {
			System.out.println("Excepcion: "+e.getMessage());
			return null;
		}
		
		return textoClaro;
	}
	
	
	public static void imprimir(byte[] contenido) {
		for (int i = 0; i < contenido.length; i++) {
			System.out.println(contenido[i]+" ");
			
		}
		System.out.println("\n ===");
	}
	
	

}
