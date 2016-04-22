package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.commun.Fonctions;
import serveur.modele.Plateau;
import serveur.modele.Point;

/**
 * Classe servant a convertir un Plateau en PlateauSauvegarde pour la sauvegarde
 * de l'objet
 * 
 * @author Alexandre
 *
 */
public class PlateauSauvegarde implements Serializable {
	/**
	 * Variable pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ArrayList des Hexagones à sauvegarder
	 */
	private ArrayList<HexagoneSauvegarde> hexagones;
	/**
	 * ArrayList des Points du Plateau
	 */
	private ArrayList<Point> points;
	/**
	 * ArrayList des Villes du Plateau
	 */
	private ArrayList<VilleSauvegarde> villes;
	/**
	 * ArrayList des Routes du Plateau
	 */
	private ArrayList<RouteSauvegarde> routes;
	/**
	 * ArrayList des Jetons du Plateau
	 */
	private ArrayList<JetonSauvegarde> jetons = new ArrayList<JetonSauvegarde>();

	/**
	 * Constructeur
	 * 
	 * @param plateau
	 *            Plateau à convertir
	 * @throws RemoteException
	 */
	public PlateauSauvegarde(Plateau plateau) throws RemoteException {
		this.hexagones = Fonctions.transformArrayHexagoneSauvegarde(plateau.getHexagones());
		this.villes = Fonctions.transformArrayVilleSauvegarde(plateau.getVilles());
		this.routes = Fonctions.transformArrayRouteSauvegarde(plateau.getRoutes());
		this.jetons = Fonctions.transformArrayJetonSauvegarde(plateau.getJetons());
	}

	/**
	 * Getter de l'ArrayList des hexagones à sauvegarder
	 * @return ArrayList<HexagoneSauvegarde>
	 */

	public ArrayList<HexagoneSauvegarde> getHexagones() {
		return hexagones;
	}
	/**
	 * Setter de la liste des Hexagones à sauvegarder
	 * @param ArrayList<HexagoneSauvegarde> hexagones
	 */
	public void setHexagones(ArrayList<HexagoneSauvegarde> hexagones) {
		this.hexagones = hexagones;
	}

	/**
	 * Getter de l'ArrayList des Points à sauvegarder
	 * @return ArrayList<PointSauvegarde>
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}

	/**
	 * Setter de la liste des Points à sauvegarder
	 * @param ArrayList<PointSauvegarde> points
	 */
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	/**
	 * Getter de l'ArrayList des Villes à sauvegarder
	 * @return ArrayList<VilleSauvegarde>
	 */
	public ArrayList<VilleSauvegarde> getVilles() {
		return villes;
	}
	
	/**
	 * Setter de la liste des Villes à sauvegarder
	 * @param ArrayList<VillesSauvegarde> villes
	 */

	public void setVilles(ArrayList<VilleSauvegarde> villes) {
		this.villes = villes;
	}

	/**
	 * Getter de l'ArrayList des Routes à sauvegarder
	 * @return ArrayList<RouteSauvegarde>
	 */
	public ArrayList<RouteSauvegarde> getRoutes() {
		return routes;
	}
	/**
	 * Setter de la liste des Routes à sauvegarder
	 * @param ArrayList<RouteSauvegarde> routes
	 */

	public void setRoutes(ArrayList<RouteSauvegarde> routes) {
		this.routes = routes;
	}
	/**
	 * Getter de l'ArrayList des Jetons à sauvegarder
	 * @return ArrayList<JetonSauvegarde>
	 */
	public ArrayList<JetonSauvegarde> getJetons() {
		return jetons;
	}

	/**
	 * Setter de la liste des Jetons à sauvegarder
	 * @param ArrayList<JetonSauvegarde> jetons
	 */
	public void setJetons(ArrayList<JetonSauvegarde> jetons) {
		this.jetons = jetons;
	}

	/**
	 * Getter de Serialversionuid
	 * @return Long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
