package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.commun.Fonctions;
import serveur.modele.Plateau;
import serveur.modele.Point;

/**
 * Classe servant a convertir un plateau en PlateauSauvegarde pour la sauvegarde
 * de l'objet
 * 
 * @author Alexandre
 */
public class PlateauSauvegarde implements Serializable {

	/**
	 * Variable pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ArrayList des hexagones à sauvegarder
	 */
	private ArrayList<HexagoneSauvegarde> hexagones;

	/**
	 * ArrayList des points du Plateau
	 */
	private ArrayList<Point> points;

	/**
	 * ArrayList des villes du Plateau
	 */
	private ArrayList<VilleSauvegarde> villes;

	/**
	 * ArrayList des routes du Plateau
	 */
	private ArrayList<RouteSauvegarde> routes;

	/**
	 * ArrayList des jetons du Plateau
	 */
	private ArrayList<JetonSauvegarde> jetons = new ArrayList<JetonSauvegarde>();
	/**
	 * Difficulté de la partie
	 */
	private String difficulte;

	/**
	 * Constructeur
	 * 
	 * @param plateau
	 *            - plateau à convertir
	 * @throws RemoteException
	 */
	public PlateauSauvegarde(Plateau plateau) throws RemoteException {
		this.hexagones = Fonctions.transformArrayHexagoneSauvegarde(plateau.getHexagones());
		this.villes = Fonctions.transformArrayVilleSauvegarde(plateau.getVilles());
		this.routes = Fonctions.transformArrayRouteSauvegarde(plateau.getRoutes());
		this.jetons = Fonctions.transformArrayJetonSauvegarde(plateau.getJetons());
		this.difficulte = Plateau.getDifficulte();
	}

	/**
	 * Constructeur vide pour la désérialisation
	 */
	public PlateauSauvegarde() {
	}

	/**
	 * Getter de l'ArrayList des hexagones à sauvegarder
	 * 
	 * @return la liste des hexagones
	 */

	public ArrayList<HexagoneSauvegarde> getHexagones() {
		return hexagones;
	}

	/**
	 * Setter de la liste des hexagones à sauvegarder
	 * 
	 * @param hexagones
	 *            - nouvel hexagone
	 */
	public void setHexagones(ArrayList<HexagoneSauvegarde> hexagones) {
		this.hexagones = hexagones;
	}

	/**
	 * Getter de l'ArrayList des points à sauvegarder
	 * 
	 * @return la liste des points
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}

	/**
	 * Setter de la liste des points à sauvegarder
	 * 
	 * @param points
	 *            - nouveaux points
	 */
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	/**
	 * Getter de l'ArrayList des villes à sauvegarder
	 * 
	 * @return la liste des villes
	 */
	public ArrayList<VilleSauvegarde> getVilles() {
		return villes;
	}

	/**
	 * Setter de la liste des villes à sauvegarder
	 * 
	 * @param villes
	 *            - nouvelles villes
	 */

	public void setVilles(ArrayList<VilleSauvegarde> villes) {
		this.villes = villes;
	}

	/**
	 * Getter de l'ArrayList des routes à sauvegarder
	 * 
	 * @return la liste des routes
	 */
	public ArrayList<RouteSauvegarde> getRoutes() {
		return routes;
	}

	/**
	 * Setter de la liste des routes à sauvegarder
	 * 
	 * @param routes
	 *            - nouvelles routes
	 */

	public void setRoutes(ArrayList<RouteSauvegarde> routes) {
		this.routes = routes;
	}

	/**
	 * Getter de l'ArrayList des jetons à sauvegarder
	 * 
	 * @return la liste des jetons
	 */
	public ArrayList<JetonSauvegarde> getJetons() {
		return jetons;
	}

	/**
	 * Setter de la liste des jetons à sauvegarder
	 * 
	 * @param jetons
	 *            - nouveaux jetons
	 */
	public void setJetons(ArrayList<JetonSauvegarde> jetons) {
		this.jetons = jetons;
	}

	/**
	 * Getter de Serialversionuid
	 * 
	 * @return Long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Getter de la Difficulté
	 * 
	 * @return String
	 */
	public String getDifficulte() {
		return difficulte;
	}

	/**
	 * Setter de la Difficulté
	 * 
	 * @param String
	 */
	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}

}
