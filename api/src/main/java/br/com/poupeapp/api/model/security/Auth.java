package br.com.poupeapp.api.model.security;

import javax.xml.bind.DatatypeConverter;

import br.com.poupeapp.api.model.Usuario;

public class Auth {
	private static final String PREFIXO="*PoUpEaPp|";
	public static String generateToken(Usuario usuario) {
		String token = PREFIXO+usuario.toString();
		token = DatatypeConverter.printHexBinary(token.getBytes());
		return token;
	}
	public static boolean isValid(String token) {
		byte[] vetor = DatatypeConverter.parseHexBinary(token);
		String credencial = new String(vetor);
		if(credencial.startsWith(PREFIXO)) {
			return true;
		}
		return false;
	}
}
