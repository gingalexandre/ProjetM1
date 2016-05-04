package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;

import serveur.modele.Point;
import serveur.modele.service.VilleInterface;

/**
 * Classe servant a convertir une villeInterface en villeSauvegarde pour la sauvegarde de l'objet
 * @author Alexandre
 */
public class VilleSauvegarde implements Serializable {

	/**
	 * Variable pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Emplacement de la ville
	 */
	private Point emplacement;
	
	/**
	 * Position dans la liste des villes de la 1ère ville adjacente à la ville
	 */
	private int ville_adj1;
	
	/**
	 * Position dans la liste des villes de la 2nd ville adjacente à la ville
	 */
	private int ville_adj2;
	
	/**
	 * Position dans la liste des villes de la 3ème ville adjacente à la ville
	 */
	private int ville_adj3;
	
	/**
	 * 1ère route adjacente à la ville
	 */
	private RouteSauvegarde route_adj1;
	
	/**
	 * 2nd route adjacente à la ville
	 */
	private RouteSauvegarde route_adj2;
	
	/**
	 * 3ème route adjacente à la ville
	 */
	private RouteSauvegarde route_adj3;
	
	/**
	 * Propriétaire de la ville
	 */
	private JoueurSauvegarde ville;
	
	/**
	 * Unité du gain que la ville confère au joueur
	 */
	private int gain;
	
	/**
	 * Si c'est un port 0 sinon -1
	 */
	private int port1;

	/**
	 * Si c'est une colonie
	 */
	private boolean colonieVille;

	/**
	 * Constructeur
	 * @param ville - VilleInterface à convertir
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
		this.colonieVille = ville.isColonie();
		this.port1 = ville.isPort();
		// Dans le cas où la dernière est null suite aux contraintes du plateau
		if (ville.getRoute_adj3() != null) {
			this.route_adj3 = new RouteSauvegarde(ville.getRoute_adj3());
		} else {
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
	 * Constructeur vide pour la désérialisation
	 */
	public VilleSauvegarde() throws RemoteException {

	}

	/**
	 * Getter de l'emplacement de la ville
	 * @return l'emplacement de la ville
	 */
	public Point getEmplacement() {
		return emplacement;
	}

	/**
	 * Setter de l'emplacement de la ville
	 * @param nouvel emplacement
	 */
	public void setEmplacement(Point emplacement) {
		this.emplacement = emplacement;
	}

	/**
	 * Getter pour la position dans la liste des villes de la 1ère ville adjacente
	 * @return la position de la première ville adjacente
	 */
	public int getVille_adj1() {
		return ville_adj1;
	}

	/**
	 * Setter pour la position dans la liste des villes de la 1ère ville adjacente
	 * @param ville_adj1 - nouvelle position
	 */
	public void setVille_adj1(int ville_adj1) {
		this.ville_adj1 = ville_adj1;
	}

	/**
	 * Getter pour la position dans la liste des villes de la 2nd ville adjacente
	 * @return la position de la deuxième ville adjacente
	 */
	public int getVille_adj2() {
		return ville_adj2;
	}

	/**
	 * Setter pour la position dans la liste des villes de la 2nd ville adjacente
	 * @param ville_adj2 - nouvelle position
	 */
	public void setVille_adj2(int ville_adj2) {
		this.ville_adj2 = ville_adj2;
	}

	/**
	 * Getter pour la position dans la liste des villes de la 3ème ville adjacente
	 * @return la position de la troisième ville adjacente
	 */
	public int getVille_adj3() {
		return ville_adj3;
	}

	/**
	 * Setter pour la position dans la liste des villes de la 3ème ville adjacente
	 * @param ville_adj3 - nouvelle position
	 */
	public void setVille_adj3(int ville_adj3) {
		this.ville_adj3 = ville_adj3;
	}

	/**
	 * Getter pour la première route adjacente
	 * @return la première route adjacente
	 */
	public RouteSauvegarde getRoute_adj1() {
		return route_adj1;
	}

	/**
	 * Setter pour la première route adjacente
	 * @param route_adj1 - nouvelle route adjacente
	 */
	public void setRoute_adj1(RouteSauvegarde route_adj1) {
		this.route_adj1 = route_adj1;
	}

	/**
	 * Getter pour la deuxième route adjacente
	 * @return la deuxième route adjacente
	 */
	public RouteSauvegarde getRoute_adj2() {
		return route_adj2;
	}

	/**
	 * Setter pour la deuxième route adjacente
	 * @param route_adj2 - nouvelle route adjacente
	 */
	public void setRoute_adj2(RouteSauvegarde route_adj2) {
		this.route_adj2 = route_adj2;
	}

	/**
	 * Getter pour la troisième route adjacente
	 * @return la troisième route adjacente
	 */
	public RouteSauvegarde getRoute_adj3() {
		return route_adj3;
	}

	/**
	 * Setter pour la troisième route adjacente
	 * @param route_adj3 - nouvelle route adjacente
	 */
	public void setRoute_adj3(RouteSauvegarde route_adj3) {
		this.route_adj3 = route_adj3;
	}

	/**
	 * Getter du propriétaire de la ville
	 * @return le propriétaire de la ville
	 */
	public JoueurSauvegarde getVille() {
		return ville;
	}

	/**
	 * Setter du propriétaire de la ville
	 * @param ville - nouveau propriétaire
	 */
	public void setVille(JoueurSauvegarde ville) {
		this.ville = ville;
	}

	/**
	 * Getter du gain que procure la ville
	 * @return le gain que procure la ville
	 */
	public int getGain() {
		return gain;
	}

	/**
	 * Setter du gain que procure la ville
	 * @param gain - nouveau gain
	 */
	public void setGain(int gain) {
		this.gain = gain;
	}
	
	

	public int getPort1() {
		return port1;
	}

	public void setPort1(int port1) {
		this.port1 = port1;
	}

	public boolean isColonieVille() {
		return colonieVille;
	}

	public void setColonieVille(boolean colonieVille) {
		this.colonieVille = colonieVille;
	}

	

	/**
	 * Méthode equals
	 */
	public boolean equals(Object o) {
		return o instanceof VilleSauvegarde && ((VilleSauvegarde) o).emplacement.equals(this.emplacement)
				&& ((VilleSauvegarde) o).ville_adj1 == this.ville_adj1
				&& ((VilleSauvegarde) o).ville_adj2 == this.ville_adj2
				&& ((VilleSauvegarde) o).ville_adj3 == this.ville_adj3;
	}
}
