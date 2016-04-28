/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.modele;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import serveur.bdd.modeleSauvegarde.JoueurSauvegarde;
import serveur.modele.service.CarteInterface;
import serveur.modele.service.JoueurInterface;

/**
 *
 * @author Arthur
 */
public class Joueur extends UnicastRemoteObject implements JoueurInterface, Serializable{

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
	 * Points de victoires supplémentaires
	 */
	boolean routeLaPlusLongue, armeeLaPlusPuissante = false;
	
	/**
	 * Constructeur de joueur
	 * Est appele lors de l'ajout d'un proxy sur le serveur
	 */
	public Joueur() throws RemoteException{
		initialisationAttributs();
	}
	
	
	public Joueur(int id, String nomUtilisateur, Date dateDeNaissance, String couleur, boolean pret, int pointVictoire,
			int nbColonie, int nbVille, int nbRoute, HashMap<Integer, Integer> stockRessource, ArrayList<CarteInterface> cartes)
			throws RemoteException {
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

	public void aUneRoutePlusLongue(Joueur j){
		//TODO
	}
	
	public void aUneArmeePlusGrande(Joueur j){
		//TODO
	}

	public boolean isRouteLaPlusLongue() {
		return routeLaPlusLongue;
	}


	public void setRouteLaPlusLongue(boolean routeLaPlusLongue) {
		this.routeLaPlusLongue = routeLaPlusLongue;
	}


	public boolean isArmeeLaPlusPuissante() {
		return armeeLaPlusPuissante;
	}


	public void setArmeeLaPlusPuissante(boolean armeeLaPlusPuissante) {
		this.armeeLaPlusPuissante = armeeLaPlusPuissante;
	}


	/**
	 * Constructeur de joueur
	 * @param nom - nom du joueur
	 */
	public Joueur(String nom) throws RemoteException{
		this.setNomUtilisateur(nom);
		initialisationAttributs();
	}
	
	/**
	 * Constructeur de joueur
	 * @param nom - nom du joueur
	 * @param dateNaissance - date de naissance
	 */
	public Joueur(String nom, Date dateNaissance) throws RemoteException{
		this.setNomUtilisateur(nom);
		this.setDateDeNaissance(dateNaissance);
		initialisationAttributs();
	}

	public Joueur(JoueurSauvegarde joueur) throws RemoteException{
		this.id = joueur.getId();
		this.nomUtilisateur = joueur.getNomUtilisateur();
		this.dateDeNaissance = joueur.getDateDeNaissance();
		this.couleur = joueur.getCouleur();
		this.pret = joueur.isPret();
		this.pointVictoire = joueur.getPointVictoire();
		this.nbColonie = joueur.getNbColonie();
		this.nbVille = joueur.getNbVille();
		this.nbRoute = joueur.getNbRoute();
		this.stockRessource = joueur.getStockRessource();
		this.cartes = joueur.getCartes();
	}



	/**
	 * Permet d'initialiser des divers attributs d'un joueur
	 * @throws RemoteException 
	 */
	private void initialisationAttributs() throws RemoteException{
		compteurDeJoueur++;
		this.id = compteurDeJoueur;
		this.setPointVictoire(0);
		this.stockRessource.put(Ressource.BOIS, 0);
		this.stockRessource.put(Ressource.BLE, 0);
		this.stockRessource.put(Ressource.ARGILE, 0);
		this.stockRessource.put(Ressource.MINERAIE, 0);
		this.stockRessource.put(Ressource.LAINE, 0);
	}
	
	/**
	 * Permet d'obtenir le stock de ressources du joueur
	 * @return le stock de ressources du joueur
	 */
	public HashMap<Integer, Integer> getStockRessource() throws RemoteException{
		return this.stockRessource;
	}

	public void setStockRessource(HashMap<Integer, Integer> stockRessource) throws RemoteException{
		this.stockRessource = stockRessource;
	}

	public void setId(int id) throws RemoteException{
		this.id = id;
	}
	
	public int getId() throws RemoteException{
		return this.id;
	}

	/**
	 * @return si le joueur est pret a jouer ou non
	 */
	public boolean getPret()  throws RemoteException{
		return pret;
	}

	/**
	 * Permet de recuperer la valeur de l'attribut pret
	 * @param pret
	 */
	public void setPret(boolean pret)  throws RemoteException{
		this.pret = pret;
	}
	
	public void construireRoute()  throws RemoteException{

	}

	public void construireColonie()  throws RemoteException{

	}

	public void construireVille()  throws RemoteException{

	}

	public void ajoutRessource(int typeRessource, int value)  throws RemoteException{
		this.stockRessource.put(typeRessource, this.stockRessource.get(typeRessource) + value);
	}

	public void supprimerRessource(int typeRessource, int value)  throws RemoteException{
		this.stockRessource.put(typeRessource, this.stockRessource.get(typeRessource) - value);
	}

	public void echangerRessource(int typeRessourceDonnee, int quantiteDonnee, Joueur partenaireEchange,
			int typeRessourceRecup, int quantiteRecup)  throws RemoteException{

	}

	public void jouerCarteDevelopement(CarteInterface carte)  throws RemoteException{

	}

	public void joueurCarteSpeciale(CarteInterface carte)  throws RemoteException{

	}

	public void ajouterPointVictoire()  throws RemoteException{
		this.pointVictoire++;
	}
	
	public void supprimerPointVictoire()  throws RemoteException{
		this.pointVictoire--;
	}
	
	public String getNomUtilisateur()  throws RemoteException{
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur)  throws RemoteException{
		this.nomUtilisateur = nomUtilisateur;
	}

	public Date getDateDeNaissance()  throws RemoteException{
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance)  throws RemoteException{
		this.dateDeNaissance = dateDeNaissance;
	}
	
	public String getCouleur()  throws RemoteException{
		return this.couleur;
	}

	public void setCouleur(String couleur)  throws RemoteException{
		this.couleur = couleur;
	}

	public int getNbColonie()  throws RemoteException{
		return nbColonie;
	}

	public void setNbColonie(int nbColonie)  throws RemoteException{
		this.nbColonie = nbColonie;
	}

	public int getNbVille()  throws RemoteException{
		return nbVille;
	}

	public void setNbVille(int nbVille)  throws RemoteException{
		this.nbVille = nbVille;
	}

	public boolean encoreAssezVille()  throws RemoteException{
		return (this.nbVille > 0);
	}

	public boolean encoreAssezColonie()  throws RemoteException{
		return (this.nbVille > 0);
	}

	public boolean encoreAssezRoute()  throws RemoteException{
		return (this.nbRoute > 0);
	}

	public int getPointVictoire()  throws RemoteException{
		return pointVictoire;
	}

	public void setPointVictoire(int pointVictoire)  throws RemoteException{
		this.pointVictoire = pointVictoire;
	}

	public ArrayList<CarteInterface> getCartes()  throws RemoteException{
		return cartes;
	}

	public void addCartes(CarteInterface carte)  throws RemoteException{
		this.cartes.add(carte);
	}

	public int getNbRoute()  throws RemoteException{
		return nbRoute;
	}

	public void setNbRoute(int nbRoute)  throws RemoteException{
		this.nbRoute = nbRoute;
	}

	@Override
	public int compareTo(JoueurInterface j) throws RemoteException{
		if (this.dateDeNaissance.after(j.getDateDeNaissance())){
			return 1;
		}
		else if (this.dateDeNaissance.before(j.getDateDeNaissance())){
			return -1;
		}
		else{
			return 0;
		}
	}
	/**
	 * Méthode equals
	 */
	public boolean equals(Object o){
		return o instanceof Joueur && ((Joueur)o).id==this.id;
	}
	
	
}
