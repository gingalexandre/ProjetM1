package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import serveur.commun.Fonctions;
import serveur.modele.service.JoueurInterface;

/**
 * Classe servant a convertir un JoueurInterface en JoueurSauvegarde pour la sauvegarde de l'objet
 * @author Alexandre
 */
public class JoueurSauvegarde implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Id du joueur
	 */
	private int id;
	
	/**
	 * Compteur du joueur
	 */
	private static int compteurDeJoueur = 0;

	/**
	 * Pseudo du joueur dans la base de donnees
	 */
	private String nomUtilisateur;

	/**
	 * Date de naissance du joueur dans la base de donnees
	 */
	private Date dateDeNaissance;

	/**
	 * Couleur de jeu du joueur
	 */
	private String couleur;

	/**
	 * Si le joueur est pret a jouer ou non
	 */
	private boolean pret;

	/**
	 * Point de victoires du joueur
	 */
	private int pointVictoire;

	/**
	 * Nombre de colonies du joueur
	 */
	private int nbColonie = 5;

	/**
	 * Nombre de villes du joueur
	 */
	private int nbVille = 4;

	/**
	 * Nombre de routes du joueur
	 */
	private int nbRoute = 15;

	/**
	 * Stock de ressources du joueur
	 */
	private HashMap<Integer, Integer> stockRessource = new HashMap<>();

	/**
	 * Cartes du joueur
	 */
	private ArrayList<CarteSauvegarde> cartes = new ArrayList<CarteSauvegarde>();

	/**
	 * Constructeur
	 * @param joueur - joueur à convertir
	 * @throws RemoteException
	 */

	/**
	 * @param joueur
	 * @throws RemoteException
	 */
	public JoueurSauvegarde(JoueurInterface joueur) throws RemoteException {
		super();
		this.id = joueur.getId();
		this.nomUtilisateur = joueur.getNomUtilisateur();
		this.dateDeNaissance = joueur.getDateDeNaissance();
		this.couleur = joueur.getCouleur();
		this.pret = joueur.getPret();
		this.pointVictoire = joueur.getPointVictoire();
		this.nbColonie = joueur.getNbColonie();
		this.nbVille = joueur.getNbVille();
		this.nbRoute = joueur.getNbRoute();
		this.stockRessource = joueur.getStockRessource();
		this.cartes = Fonctions.transformArrayCarteSauvegarde(joueur.getCartes());
	}

	/**
	 * Constructeur vide pour la désérialisation
	 */
	public JoueurSauvegarde() {}

	/**
	 * Getter de l'Id
	 * @return Integer
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter de l'Id
	 * @param id - nouvel id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter du compteur de joueur présent sur le serveur
	 * @return Integer
	 */
	public static int getCompteurDeJoueur() {
		return compteurDeJoueur;
	}

	/**
	 * Setter du compteur de joueur présent sur le serveur
	 * @param compteurDeJoueur
	 */
	public static void setCompteurDeJoueur(int compteurDeJoueur) {
		JoueurSauvegarde.compteurDeJoueur = compteurDeJoueur;
	}

	/**
	 * Getter du nom de l'utilisateur
	 * @return String
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	/**
	 * Setter du nom de l'utilisateur
	 * @param nomUtilisateur
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	/**
	 * Getter de la Date de Naissance du joueur
	 * @return Date
	 */
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	/**
	 * Setter de la Date de Naissance du joueur
	 * @param dateDeNaissance - nouvelle date de naissance
	 */
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
	 * Getter de la couleur du joueur
	 * @return String
	 */
	public String getCouleur() {
		return couleur;
	}

	/**
	 * Setter de la couleur du joueur
	 * @param couleur
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	/**
	 * Getter du booléen pour voir si le joueur est prêt pour la partie
	 * @return Booléen
	 */
	public boolean isPret() {
		return pret;
	}

	/**
	 * Setter du booléen pour voir si le joueur est prêt pour la partie
	 * @param pret
	 */
	public void setPret(boolean pret) {
		this.pret = pret;
	}

	/**
	 * Getter du nombre de points de victoire du joueur
	 * @return Integer
	 */
	public int getPointVictoire() {
		return pointVictoire;
	}

	/**
	 * Setter du nombre de points de victoire du joueur
	 * @param pointVictoire
	 */
	public void setPointVictoire(int pointVictoire) {
		this.pointVictoire = pointVictoire;
	}

	/**
	 * Getter du nombre de Colonie du joueur
	 * @return Integer
	 */
	public int getNbColonie() {
		return nbColonie;
	}

	/**
	 * Setter du nombre de Colonie du joueur
	 * @param nbColonie
	 */
	public void setNbColonie(int nbColonie) {
		this.nbColonie = nbColonie;
	}

	/**
	 * Getter du nombre de ville du joueur
	 * @return Integer
	 */
	public int getNbVille() {
		return nbVille;
	}

	/**
	 * Setter du nombre de ville du joueur
	 * @param nbVille
	 */
	public void setNbVille(int nbVille) {
		this.nbVille = nbVille;
	}

	/**
	 * Getter du nombre de route du joueur
	 * @return Integer
	 */
	public int getNbRoute() {
		return nbRoute;
	}

	/**
	 * Setter du nombre de route du joueur
	 * @param nbRoute
	 */
	public void setNbRoute(int nbRoute) {
		this.nbRoute = nbRoute;
	}

	/**
	 * Getter du Serialversionuid
	 * @return Long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Getter de la map des ressources du joueur schéma de la map : <IdentifiantRessource, Nombre>
	 * @return HashMap<Integer, Integer>
	 */
	public HashMap<Integer, Integer> getStockRessource() {
		return stockRessource;
	}

	/**
	 * Setter de la map des ressources du joueur schéma de la map : <IdentifiantRessource, Nombre>
	 * @param stockRessource
	 */
	public void setStockRessource(HashMap<Integer, Integer> stockRessource) {
		this.stockRessource = stockRessource;
	}

	/**
	 * Getter de la liste de carte du joueur
	 * @return ArrayList<CarteInterface>
	 */
	public ArrayList<CarteSauvegarde> getCartes() {
		return cartes;
	}

	/**
	 * Setter de la liste de carte du joueur
	 * @param cartes
	 */
	public void setCartes(ArrayList<CarteSauvegarde> cartes) {
		this.cartes = cartes;
	}

	/**
	 * Méthode equals
	 */
	public boolean equals(Object o) {
		return o instanceof JoueurSauvegarde && ((JoueurSauvegarde) o).id == this.id;
	}
}
