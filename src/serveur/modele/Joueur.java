/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.modele;

import serveur.modele.carte.Carte;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Arthur
 */
public class Joueur implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private static int compteurDeJoueur = 0;
	
	/**
	 * Pseudo du joueur dans la base de donn�es 
	 */
	private String nomUtilisateur;

	/**
	 * Date de naissance du joueur dans la base de donn�es
	 */
	private Date dateDeNaissance;
	
	/**
	 * Couleur de jeu du joueur
	 */
	private String couleur;
	
	private int pointVictoire;

	private int nbColonie = 5;
	private int nbVille = 4;
	private int nbRoute = 15;

	private HashMap<Integer, Integer> stockRessource = new HashMap<>();
	private ArrayList<Carte> cartes = new ArrayList<Carte>();

	/**
	 * Constructeur de joueur
	 * Est appel� lors de l'ajout d'un proxy sur le serveur
	 */
	public Joueur(){
		initialisationAttributs();
	}
	
	/**
	 * Constructeur de joueur
	 * @param nom - nom du joueur
	 */
	public Joueur(String nom) {
		this.setNomUtilisateur(nomUtilisateur);
		initialisationAttributs();
	}

	/**
	 * Permet d'initialiser des divers attributs d'un joueur
	 */
	private void initialisationAttributs(){
		compteurDeJoueur++;
		this.id = compteurDeJoueur;
		this.setPointVictoire(0);
		this.stockRessource.put(Ressource.BOIS, 0);
		this.stockRessource.put(Ressource.BLE, 0);
		this.stockRessource.put(Ressource.ARGILE, 0);
		this.stockRessource.put(Ressource.MINERAIE, 0);
		this.stockRessource.put(Ressource.LAINE, 0);
	}
	
	public HashMap<Integer, Integer> getStockRessource() {
		return this.stockRessource;
	}

	public void setStockRessource(HashMap<Integer, Integer> stockRessource) {
		this.stockRessource = stockRessource;
	}

	public int getId() {
		return this.id;
	}

	public void construireRoute() {

	}

	public void construireColonie() {

	}

	public void construireVille() {

	}

	public void ajoutRessource(int typeRessource, int value) {
		this.stockRessource.put(typeRessource, this.stockRessource.get(typeRessource) + value);
	}

	public void supprimerRessource(int typeRessource, int value) {
		this.stockRessource.put(typeRessource, this.stockRessource.get(typeRessource) - value);
	}

	public void echangerRessource(int typeRessourceDonnee, int quantiteDonnee, Joueur partenaireEchange,
			int typeRessourceRecup, int quantiteRecup) {

	}

	public void jouerCarteDevelopement(Carte carte) {

	}

	public void joueurCarteSpeciale(Carte carte) {

	}

	public void ajouterPointVictoire() {
		this.setPointVictoire(this.getPointVictoire() + 1);
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
		return this.couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
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

	public boolean encoreAssezVille() {
		return (this.nbVille > 0);
	}

	public boolean encoreAssezColonie() {
		return (this.nbVille > 0);
	}

	public boolean encoreAssezRoute() {
		return (this.nbRoute > 0);
	}

	public int getPointVictoire() {
		return pointVictoire;
	}

	public void setPointVictoire(int pointVictoire) {
		this.pointVictoire = pointVictoire;
	}

	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	public void addCartes(Carte carte) {
		this.cartes.add(carte);
	}

	public int getNbRoute() {
		return nbRoute;
	}

	public void setNbRoute(int nbRoute) {
		this.nbRoute = nbRoute;
	}
	
	public int compareTo(Joueur j){
		if (this.dateDeNaissance.after(j.dateDeNaissance)){
			return -10;
		}
		else if (this.dateDeNaissance.before(j.dateDeNaissance)){
			return 10;
		}
		else{
			return 0;
		}
	}
}
