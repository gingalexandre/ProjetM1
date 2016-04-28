package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import serveur.modele.service.CarteInterface;
import serveur.modele.service.JoueurInterface;

/**
 * Classe servant a convertir un JoueurInterface en JoueurSauvegarde pour la
 * sauvegarde de l'objet
 * 
 * @author Alexandre
 *
 */
public class JoueurSauvegarde implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
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
	private ArrayList<CarteInterface> cartes = new ArrayList<CarteInterface>();

	/**
	 * Constructeur
	 * 
	 * @param joueur
	 *            : JoueurInterface : joueur à convertir
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
		this.cartes = joueur.getCartes();
	}

	/**
	 * Constructeur vide pour la désérialisation
	 */
	public JoueurSauvegarde() {

	}

	/**
	 * Getter de l'Id
	 * 
	 * @return Integer
	 */
	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter de l'Id
	 * 
	 * @param id
	 *            : Integer
	 */
	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter du Compteur de Joueur présent sur le serveur
	 * 
	 * @return Integer
	 */
	public static int getCompteurDeJoueur() {
		return compteurDeJoueur;
	}

	/**
	 * Setter du Compteur de Joueur présent sur le serveur
	 * 
	 * @param compteurDeJoueur
	 */
	public static void setCompteurDeJoueur(int compteurDeJoueur) {
		JoueurSauvegarde.compteurDeJoueur = compteurDeJoueur;
	}

	/**
	 * Getter du Nom de l'Utilisateur
	 * 
	 * @return String
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	/**
	 * Setter du Nom de l'Utilisateur
	 * 
	 * @param nomUtilisateur
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	/**
	 * Getter de la Date de Naissance du Joueur
	 * 
	 * @return Date
	 */
	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	/**
	 * Setter de la Date de Naissance du Joueur
	 * 
	 * @param dateDeNaissance
	 */
	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
	 * Getter de la couleur du Joueur
	 * 
	 * @return String
	 */
	public String getCouleur() {
		return couleur;
	}

	/**
	 * Setter de la couleur du Joueur
	 * 
	 * @param couleur
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	/**
	 * Getter du booléen pour voir si le Joueur est prêt pour la partie
	 * 
	 * @return Booléen
	 */
	public boolean isPret() {
		return pret;
	}

	/**
	 * Setter du booléen pour voir si le Joueur est prêt pour la partie
	 * 
	 * @param pret
	 */
	public void setPret(boolean pret) {
		this.pret = pret;
	}

	/**
	 * Getter du nombre de points de victoire du Joueur
	 * 
	 * @return Integer
	 */
	public int getPointVictoire() {
		return pointVictoire;
	}

	/**
	 * Setter du nombre de points de victoire du Joueur
	 * 
	 * @param pointVictoire
	 */
	public void setPointVictoire(int pointVictoire) {
		this.pointVictoire = pointVictoire;
	}

	/**
	 * Getter du nombre de Colonie du Joueur
	 * 
	 * @return Integer
	 */
	public int getNbColonie() {
		return nbColonie;
	}

	/**
	 * Setter du nombre de Colonie du Joueur
	 * 
	 * @param nbColonie
	 */
	public void setNbColonie(int nbColonie) {
		this.nbColonie = nbColonie;
	}

	/**
	 * Getter du nombre de Ville du Joueur
	 * 
	 * @return Integer
	 */
	public int getNbVille() {
		return nbVille;
	}

	/**
	 * Setter du nombre de Ville du Joueur
	 * 
	 * @param nbVille
	 */
	public void setNbVille(int nbVille) {
		this.nbVille = nbVille;
	}

	/**
	 * Getter du nombre de Route du Joueur
	 * 
	 * @return Integer
	 */
	public int getNbRoute() {
		return nbRoute;
	}

	/**
	 * Setter du nombre de Route du Joueur
	 * 
	 * @param nbRoute
	 */
	public void setNbRoute(int nbRoute) {
		this.nbRoute = nbRoute;
	}

	/**
	 * Getter du Serialversionuid
	 * 
	 * @return Long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Getter de la Map des Ressources du Joueur Schéma de la Map : <Identifiant
	 * Ressource, Nombre>
	 * 
	 * @return HashMap<Integer, Integer>
	 */
	public HashMap<Integer, Integer> getStockRessource() {
		return stockRessource;
	}

	/**
	 * Setter de la Map des Ressources du Joueur Schéma de la Map : <Identifiant
	 * Ressource, Nombre>
	 * 
	 * @param stockRessource
	 */
	public void setStockRessource(HashMap<Integer, Integer> stockRessource) {
		this.stockRessource = stockRessource;
	}

	/**
	 * Getter de la liste de carte du Joueur
	 * 
	 * @return ArrayList<CarteInterface>
	 */
	public ArrayList<CarteInterface> getCartes() {
		return cartes;
	}

	/**
	 * Setter de la liste de carte du Joueur
	 * 
	 * @param cartes
	 */
	public void setCartes(ArrayList<CarteInterface> cartes) {
		this.cartes = cartes;
	}

	/**
	 * Méthode equals
	 */
	public boolean equals(Object o) {
		return o instanceof JoueurSauvegarde && ((JoueurSauvegarde) o).id == this.id;
	}
}
