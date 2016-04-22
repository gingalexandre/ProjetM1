package serveur.commun;

import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import serveur.bdd.modeleSauvegarde.HexagoneSauvegarde;
import serveur.bdd.modeleSauvegarde.JetonSauvegarde;
import serveur.bdd.modeleSauvegarde.RouteSauvegarde;
import serveur.bdd.modeleSauvegarde.VilleSauvegarde;
import serveur.modele.service.HexagoneInterface;
import serveur.modele.service.JetonInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;

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
	
	

	/**
	 * Méthode permettant de convertir une ArrayList<HexagoneInterface> en
	 * ArrayList<HexagoneSauvegarde>
	 * 
	 * @param hexagones
	 *            ArrayList<HexagoneInterface>
	 * @return ArrayList<HexagoneSauvegarde>
	 * @throws RemoteException
	 */
	public static ArrayList<HexagoneSauvegarde> transformArrayHexagoneSauvegarde(ArrayList<HexagoneInterface> hexagones)
			throws RemoteException {
		ArrayList<HexagoneSauvegarde> res = new ArrayList<HexagoneSauvegarde>();
		for (HexagoneInterface hexagone : hexagones) {
			res.add(new HexagoneSauvegarde(hexagone));
		}
		return res;
	}

	/**
	 * Méthode permettant de convertir une ArrayList<VilleInterface> en
	 * ArrayList<VilleSauvegarde>
	 * 
	 * @param hexagones
	 *            ArrayList<VilleInterface>
	 * @return ArrayList<VilleSauvegarde>
	 * @throws RemoteException
	 */

	public static ArrayList<VilleSauvegarde> transformArrayVilleSauvegarde(ArrayList<VilleInterface> villes)
			throws RemoteException {
		ArrayList<VilleSauvegarde> res = new ArrayList<VilleSauvegarde>();
		for (VilleInterface ville : villes) {

			res.add(new VilleSauvegarde(ville));

		}
		return res;
	}

	/**
	 * Méthode permettant de convertir une ArrayList<RouteInterface> en
	 * ArrayList<RouteSauvegarde>
	 * 
	 * @param hexagones
	 *            ArrayList<RouteInterface>
	 * @return ArrayList<RouteSauvegarde>
	 * @throws RemoteException
	 */
	public static ArrayList<RouteSauvegarde> transformArrayRouteSauvegarde(ArrayList<RouteInterface> routes)
			throws RemoteException {
		ArrayList<RouteSauvegarde> res = new ArrayList<RouteSauvegarde>();
		for (RouteInterface route : routes) {
			res.add(new RouteSauvegarde(route));
		}
		return res;
	}

	/**
	 * Méthode permettant de convertir une ArrayList<JetonInterface> en
	 * ArrayList<JetonSauvegarde>
	 * 
	 * @param hexagones
	 *            ArrayList<JetonInterface>
	 * @return ArrayList<JetonSauvegarde>
	 * @throws RemoteException
	 */
	public static ArrayList<JetonSauvegarde> transformArrayJetonSauvegarde(ArrayList<JetonInterface> jetons)
			throws RemoteException {
		ArrayList<JetonSauvegarde> res = new ArrayList<JetonSauvegarde>();
		for (JetonInterface jeton : jetons) {
			if (jeton != null) {
				res.add(new JetonSauvegarde(jeton));
			} else {
				res.add(null);
			}
		}
		return res;
	}
	
}
