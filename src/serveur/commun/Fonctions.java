package serveur.commun;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe permettant de stocker les fonctions communes aux autres classes
 * 
 * @author Alexandre
 *
 */
public class Fonctions {
	/**
	 * Méthode permettant de crypter un String en Entrée
	 * 
	 * @param entree
	 * @return String
	 */
	public static String crypte(String entree) {
		MessageDigest md = null;
		try {
			// Utilisation du SHA-1 comme GIT
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new String(md.digest(entree.getBytes()));
	}
}
