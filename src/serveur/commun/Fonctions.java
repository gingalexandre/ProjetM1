package serveur.commun;

import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.bdd.modeleSauvegarde.HexagoneSauvegarde;
import serveur.bdd.modeleSauvegarde.JetonSauvegarde;
import serveur.bdd.modeleSauvegarde.JoueurSauvegarde;
import serveur.bdd.modeleSauvegarde.RouteSauvegarde;
import serveur.bdd.modeleSauvegarde.VilleSauvegarde;
import serveur.modele.Hexagone;
import serveur.modele.Jeton;
import serveur.modele.Joueur;
import serveur.modele.Route;
import serveur.modele.Ville;
import serveur.modele.carte.ArmeePuissante;
import serveur.modele.carte.Chevalier;
import serveur.modele.carte.Invention;
import serveur.modele.carte.LongueRoute;
import serveur.modele.carte.Monopole;
import serveur.modele.carte.Victoire;
import serveur.modele.service.CarteInterface;
import serveur.modele.service.HexagoneInterface;
import serveur.modele.service.JetonInterface;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;
import serveur.reseau.proxy.JoueurServeur;

/**
 * Classe permettant de stocker les fonctions communes aux autres classes
 * 
 * @author Alexandre
 */
public class Fonctions {

	/**
	 * Méthode permettant de crypter un String en entrée
	 * 
	 * @param entree
	 *            - string à crypter
	 * @return le string crypté
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

	/**
	 * Méthode permettant de convertir une ArrayList<JetonSauvegarde> en
	 * ArrayList<JetonInterface>
	 * 
	 * @param hexagones
	 *            ArrayList<JetonSauvegarde>
	 * @return ArrayList<JetonInterface>
	 * @throws RemoteException
	 */
	public static ArrayList<JetonInterface> transformArrayJeton(ArrayList<JetonSauvegarde> jetons)
			throws RemoteException {
		ArrayList<JetonInterface> res = new ArrayList<JetonInterface>();
		for (JetonSauvegarde jeton : jetons) {
			if (jeton != null) {
				res.add(new Jeton(jeton));
			} else {
				res.add(null);
			}
		}
		return res;
	}

	/**
	 * Méthode permettant de convertir une ArrayList<HexagoneSauvegarde> en
	 * ArrayList<HexagoneSauvegarde>
	 * 
	 * @param hexagones
	 *            ArrayList<HexagoneSauvegarde>
	 * @return ArrayList<HexagoneInterface>
	 * @throws RemoteException
	 */
	public static ArrayList<HexagoneInterface> transformArrayHexagone(ArrayList<HexagoneSauvegarde> hexagones)
			throws RemoteException {
		ArrayList<HexagoneInterface> res = new ArrayList<HexagoneInterface>();
		for (HexagoneSauvegarde hexagone : hexagones) {
			res.add(new Hexagone(hexagone));
		}
		return res;
	}

	/**
	 * Méthode permettant de convertir une ArrayList<VilleSauvegarde> en
	 * ArrayList<VilleSauvegarde>
	 * 
	 * @param villes
	 *            ArrayList<VilleSauvegarde>
	 * @return ArrayList<VilleInterface>
	 * @throws RemoteException
	 */
	public static ArrayList<VilleInterface> transformArrayVille(ArrayList<VilleSauvegarde> villes)
			throws RemoteException {
		ArrayList<VilleInterface> res = new ArrayList<VilleInterface>();
		for (VilleSauvegarde ville : villes) {
			if (ville != null) {
				res.add(new Ville(ville));
			}
		}
		return res;
	}

	/**
	 * Méthode permettant de convertir une ArrayList<RouteSauvegarde> en
	 * ArrayList<RouteSauvegarde>
	 * 
	 * @param routes
	 *            ArrayList<RouteSauvegarde>
	 * @return ArrayList<RouteInterface>
	 * @throws RemoteException
	 */
	public static ArrayList<RouteInterface> transformArrayRoute(ArrayList<RouteSauvegarde> routes)
			throws RemoteException {
		ArrayList<RouteInterface> res = new ArrayList<RouteInterface>();
		for (RouteSauvegarde route : routes) {
			res.add(new Route(route));
		}
		return res;
	}

	/**
	 * Méthode permettant de convertir une ArrayList<JoueurSauvegarde> en
	 * ArrayList<JoueurInterface>
	 * 
	 * @param joueurs
	 *            ArrayList<JoueurSauvegarde>
	 * @return ArrayList<JoueurInterface>
	 * @throws RemoteException
	 */
	public static ArrayList<JoueurInterface> transformArrayJoueur(ArrayList<JoueurSauvegarde> joueurs)
			throws RemoteException {
		ArrayList<JoueurInterface> res = new ArrayList<JoueurInterface>();
		for (JoueurSauvegarde joueur : joueurs) {
			res.add(new Joueur(joueur));
		}
		return res;
	}

	/**
	 * Méthode permettant de convertir une ArrayList<JoueurServeur> en ArrayList
	 * <JoueurSauvegarde>
	 * 
	 * @param joueursServeur
	 *            ArrayList<JoueurServeur>
	 * @return ArrayList<JoueurSauvegarde>
	 * @throws RemoteException
	 */
	public static ArrayList<JoueurSauvegarde> transformArrayJoueurSauvegarde(ArrayList<JoueurServeur> joueursServeur)
			throws RemoteException {
		ArrayList<JoueurSauvegarde> res = new ArrayList<JoueurSauvegarde>();
		for (JoueurServeur js : joueursServeur) {
			res.add(new JoueurSauvegarde(js.getJoueur()));
		}
		return res;
	}

	/**
	 * Méthode permettant de convertir une ArrayList<JoueurServeur> en ArrayList
	 * <JoueurSauvegarde>
	 * 
	 * @param joueursServeur
	 *            ArrayList<JoueurServeur>
	 * @return ArrayList<JoueurSauvegarde>
	 * @throws RemoteException
	 */
	public static ArrayList<CarteSauvegarde> transformArrayCarteSauvegarde(ArrayList<CarteInterface> cartesServeur)
			throws RemoteException {
		ArrayList<CarteSauvegarde> res = new ArrayList<CarteSauvegarde>();
		for (CarteInterface carte : cartesServeur) {
			res.add(new CarteSauvegarde(carte));
		}
		return res;
	}

	public static ArrayList<CarteInterface> transformArrayCarte(ArrayList<CarteSauvegarde> cartes) {
		ArrayList<CarteInterface> res = new ArrayList<CarteInterface>();
		for (CarteSauvegarde carte : cartes) {
			try {
				res.add(Fonctions.addCarte(carte));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public static CarteInterface addCarte(CarteSauvegarde carte) throws RemoteException {
		if (carte.getType() != null) {
			if (carte.getType().equals("ArmeePuissante")) {
				return new ArmeePuissante(carte);
			} else if (carte.getType().equals("Chevalier")) {
				return new Chevalier(carte);
			} else if (carte.getType().equals("Invention")) {
				return new Invention(carte);
			} else if (carte.getType().equals("LongueRoute")) {
				return new LongueRoute(carte);
			} else if (carte.getType().equals("Monopole")) {
				return new Monopole(carte);
			} else if (carte.getType().equals("Route")) {
				return new serveur.modele.carte.Route(carte);
			} else if (carte.getType().equals("Victoire")) {
				return new Victoire(carte);
			} else {
				return null;
			}
		}
		return null;
	}

	public static LinkedList<CarteSauvegarde> transformArrayCarte(List<CarteInterface> cartes) {
		LinkedList<CarteSauvegarde> res = new LinkedList<CarteSauvegarde>();
		for (CarteInterface carte : cartes) {

			try {
				res.add(new CarteSauvegarde(carte));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public static LinkedList<CarteInterface> transformListCarteSauvegarde(List<CarteSauvegarde> cartes) {
		LinkedList<CarteInterface> res = new LinkedList<CarteInterface>();
		for (CarteSauvegarde carte : cartes) {

			try {
				res.add(Fonctions.addCarte(carte));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
}
