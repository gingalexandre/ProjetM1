/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.modeles;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Arthur
 */
public class Joueur {
    
    private int id;
    private static int compteurDeJoueur = 0;
    private String nom;
    private int pointVictoire;
    
    private int nbColonie = 5;
    private int nbVille = 4;

	private HashMap<Integer, Integer> stockRessource = new HashMap<>();
    private ArrayList<Carte> cartes = new ArrayList<Carte>();
    
    private String couleur;
    
    public Joueur(String n){
    	compteurDeJoueur++;
        this.id = compteurDeJoueur;
        this.setNom(n);
        this.setPointVictoire(0);
        this.stockRessource.put(Ressource.BOIS, 0);
        this.stockRessource.put(Ressource.BLE, 0);
        this.stockRessource.put(Ressource.ARGILE, 0);
        this.stockRessource.put(Ressource.MINERAIE, 0);
        this.stockRessource.put(Ressource.LAINE, 0);
    }
    
    public HashMap<Integer, Integer> getStockRessource(){
    	return this.stockRessource;
    }
    
    public int getId(){
    	return this.id;
    }
    
    public void construireRoute(){
        
    }
    
    public void construireColonie(){
        
    }
    
    public void construireVille(){
        
    }
    
    public void ajoutRessource(int typeRessource, int value){
        this.stockRessource.put(typeRessource, this.stockRessource.get(typeRessource) + value);
    }
    
    public void supprimerRessource(int typeRessource, int value){
    	this.stockRessource.put(typeRessource, this.stockRessource.get(typeRessource) - value);
    }
    
    public void echangerRessource(int typeRessourceDonnee, int quantiteDonnee, Joueur partenaireEchange, int typeRessourceRecup, int quantiteRecup){
        
    }
    
    public void jouerCarteDevelopement(Carte carte){
        
    }
    
    public void joueurCarteSpeciale(Carte carte){
        
    }
    
    public void ajouterPointVictoire(){
        this.setPointVictoire(this.getPointVictoire() + 1);
    }
    
    public String getCouleur(){
    	return this.couleur;
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
	
	public boolean encoreAssezVille(){
		return (this.nbVille>0);
	}
	
	public boolean encoreAssezColonie(){
		return (this.nbVille>0);
	}

	public int getPointVictoire() {
		return pointVictoire;
	}

	public void setPointVictoire(int pointVictoire) {
		this.pointVictoire = pointVictoire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	public void addCartes(Carte carte) {
		this.cartes.add(carte);
	}
    
}
