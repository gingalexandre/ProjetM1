package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Point;
import serveur.modele.service.VilleInterface;

/**
 * Classe servant a convertir une VilleInterface en VilleSauvegarde pour la
 * sauvegarde de l'objet
 * 
 * @author Alexandre
 *
 */
public class VilleSauvegarde implements Serializable {

	/**
	 * Variable pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Point : emplacement de la Ville
	 */
	private Point emplacement;
	/**
	 * Position dans la liste des villes de la 1ère Ville adjacente à la Ville
	 */
	private int ville_adj1;
	/**
	 * Position dans la liste des villes de la 2nd Ville adjacente à la Ville
	 */
	private int ville_adj2;
	/**
	 * Position dans la liste des villes de la 3ème Ville adjacente à la Ville
	 */
	private int ville_adj3;
	/**
	 * 1ère Route adjacente à la Ville
	 */
	private RouteSauvegarde route_adj1;
	/**
	 * 2nd Route adjacente à la Ville
	 */
	private RouteSauvegarde route_adj2;
	/**
	 * 3ème Route adjacente à la Ville
	 */
	private RouteSauvegarde route_adj3;
	/**
	 * Propriétaire de la Ville
	 */
	private JoueurSauvegarde ville;
	/**
	 * Unité du gain que la ville confère au joueur
	 */
	private int gain;

	/**
	 * Constructeur
	 * 
	 * @param ville
	 *            VilleInterface à convertir
	 * @throws RemoteException
	 */
	public VilleSauvegarde(VilleInterface ville) throws RemoteException {
		super();
		this.emplacement = ville.getEmplacement();
		this.ville_adj1 = ville.getVille_adj1();
		this.ville_adj2 = ville.getVille_adj2();
		this.ville_adj3 = ville.getVille_adj3();
		this.route_adj1 = new RouteSauvegarde(ville.getRoute_adj1());
		this.route_adj2 = new RouteSauvegarde(ville.getRoute_adj2());
		// Dans le cas où la dernière est null suite aux contraintes du plateau
		if (ville.getRoute_adj3() != null) {
			this.route_adj3 = new RouteSauvegarde(ville.getRoute_adj3());
		}
		else{
			this.route_adj3 = null;
		}
		if (ville.getOqp() != null) {
			this.ville = new JoueurSauvegarde(ville.getOqp());
		} else {
			this.ville = null;
		}
		this.gain = ville.getGain();
	}

	/**
	 * Getter de l'emplacement de la Ville
	 * 
	 * @return Point
	 */
	public Point getEmplacement() {
		return emplacement;
	}

	/**
	 * Setter de l'emplacement de la Ville
	 * 
	 * @param Point
	 *            emplacement
	 */
	public void setEmplacement(Point emplacement) {
		this.emplacement = emplacement;
	}

	/**
	 * Getter pour la position dans la liste des villes de la 1ère Ville
	 * Adjacente
	 * 
	 * @return int
	 */
	public int getVille_adj1() {
		return ville_adj1;
	}

	/**
	 * Setter pour la position dans la liste des villes de la 1ère Ville
	 * Adjacente
	 * 
	 * @param Integer
	 *            ville_adj1
	 */
	public void setVille_adj1(int ville_adj1) {
		this.ville_adj1 = ville_adj1;
	}

	/**
	 * Getter pour la position dans la liste des villes de la 2nd Ville
	 * Adjacente
	 * 
	 * @return int
	 */
	public int getVille_adj2() {
		return ville_adj2;
	}

	/**
	 * Setter pour la position dans la liste des villes de la 2nd Ville
	 * Adjacente
	 * 
	 * @param Integer
	 *            ville_adj2
	 */
	public void setVille_adj2(int ville_adj2) {
		this.ville_adj2 = ville_adj2;
	}

	/**
	 * Getter pour la position dans la liste des villes de la 3ème Ville
	 * Adjacente
	 * 
	 * @return int
	 */
	public int getVille_adj3() {
		return ville_adj3;
	}

	/**
	 * Setter pour la position dans la liste des villes de la 3ème Ville
	 * Adjacente
	 * 
	 * @param Integer
	 *            ville_adj3
	 */
	public void setVille_adj3(int ville_adj3) {
		this.ville_adj3 = ville_adj3;
	}

	/**
	 * Getter pour la 1ère route adjacente
	 * 
	 * @return Route
	 */
	public RouteSauvegarde getRoute_adj1() {
		return route_adj1;
	}

	/**
	 * Setter pour la 1ère route adjacente
	 * 
	 * @param route_adj2
	 */
	public void setRoute_adj1(RouteSauvegarde route_adj1) {
		this.route_adj1 = route_adj1;
	}

	/**
	 * Getter pour la 2nd route adjacente
	 * 
	 * @return Route
	 */
	public RouteSauvegarde getRoute_adj2() {
		return route_adj2;
	}

	/**
	 * Setter pour la 2nd route adjacente
	 * 
	 * @param route_adj2
	 */
	public void setRoute_adj2(RouteSauvegarde route_adj2) {
		this.route_adj2 = route_adj2;
	}

	/**
	 * Getter pour la 3ème route adjacente
	 * 
	 * @return Route
	 */
	public RouteSauvegarde getRoute_adj3() {
		return route_adj3;
	}

	/**
	 * Setter pour la 3ème route adjacente
	 * 
	 * @param route_adj2
	 */
	public void setRoute_adj3(RouteSauvegarde route_adj3) {
		this.route_adj3 = route_adj3;
	}

	/**
	 * Getter du Propriétaire de la Ville
	 * 
	 * @return JoueurSauvegarde
	 */
	public JoueurSauvegarde getville() {
		return ville;
	}

	/**
	 * Setter du Propriétaire de la Ville
	 * 
	 * @param JoueurSauvegarde
	 *            ville
	 */
	public void setville(JoueurSauvegarde ville) {
		this.ville = ville;
	}

	/**
	 * Getter du Gain que procure la Ville
	 * 
	 * @return Integer
	 */
	public int getGain() {
		return gain;
	}

	/**
	 * Setter du Gain que procure la Ville
	 * 
	 * @param Integer
	 *            gain
	 */
	public void setGain(int gain) {
		this.gain = gain;
	}
	


}
