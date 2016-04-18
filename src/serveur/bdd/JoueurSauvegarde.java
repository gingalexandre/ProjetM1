package serveur.bdd;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import serveur.modele.carte.Carte;

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
	private ArrayList<Carte> cartes = new ArrayList<Carte>();


	

	public JoueurSauvegarde(int id, String nomUtilisateur, Date dateDeNaissance, String couleur, boolean pret,
			int pointVictoire, int nbColonie, int nbVille, int nbRoute, HashMap<Integer, Integer> stockRessource,
			ArrayList<Carte> cartes) {
		super();
		this.id = id;
		this.nomUtilisateur = nomUtilisateur;
		this.dateDeNaissance = dateDeNaissance;
		this.couleur = couleur;
		this.pret = pret;
		this.pointVictoire = pointVictoire;
		this.nbColonie = nbColonie;
		this.nbVille = nbVille;
		this.nbRoute = nbRoute;
		this.stockRessource = stockRessource;
		this.cartes = cartes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getCompteurDeJoueur() {
		return compteurDeJoueur;
	}

	public static void setCompteurDeJoueur(int compteurDeJoueur) {
		JoueurSauvegarde.compteurDeJoueur = compteurDeJoueur;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public boolean isPret() {
		return pret;
	}

	public void setPret(boolean pret) {
		this.pret = pret;
	}

	public int getPointVictoire() {
		return pointVictoire;
	}

	public void setPointVictoire(int pointVictoire) {
		this.pointVictoire = pointVictoire;
	}

	public int getNbColonie() {
		return nbColonie;
	}

	public void setNbColonie(int nbColonie) {
		this.nbColonie = nbColonie;
	}

	public int getNbVille() {
		return nbVille;
	}

	public void setNbVille(int nbVille) {
		this.nbVille = nbVille;
	}

	public int getNbRoute() {
		return nbRoute;
	}

	public void setNbRoute(int nbRoute) {
		this.nbRoute = nbRoute;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HashMap<Integer, Integer> getStockRessource() {
		return stockRessource;
	}

	public void setStockRessource(HashMap<Integer, Integer> stockRessource) {
		this.stockRessource = stockRessource;
	}

	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	public void setCartes(ArrayList<Carte> cartes) {
		this.cartes = cartes;
	}
	
	
	
}
