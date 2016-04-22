package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Plateau;
import serveur.modele.Point;
import serveur.modele.service.HexagoneInterface;
import serveur.modele.service.JetonInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;

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
		this.hexagones = this.transformArrayHexagoneSauvegarde(plateau.getHexagones());
		this.villes = this.transformArrayVilleSauvegarde(plateau.getVilles());
		this.routes = this.transformArrayRouteSauvegarde(plateau.getRoutes());
		this.jetons = this.transformArrayJetonSauvegarde(plateau.getJetons());
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
	public ArrayList<HexagoneSauvegarde> transformArrayHexagoneSauvegarde(ArrayList<HexagoneInterface> hexagones)
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

	public ArrayList<VilleSauvegarde> transformArrayVilleSauvegarde(ArrayList<VilleInterface> villes)
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
	public ArrayList<RouteSauvegarde> transformArrayRouteSauvegarde(ArrayList<RouteInterface> routes)
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
	public ArrayList<JetonSauvegarde> transformArrayJetonSauvegarde(ArrayList<JetonInterface> jetons)
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
