/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles;

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
    
    private HashMap<Integer, Integer> stockRessource = new HashMap<>();
    private ArrayList<Carte> cartes = new ArrayList();
    
    public Joueur(String n){
    	compteurDeJoueur++;
        this.id = compteurDeJoueur;
        this.nom = n;
        this.pointVictoire = 0;
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
    
    public void jouerCarteDevelopement(){
        
    }
    
    public void joueurCarteSpeciale(){
        
    }
    
    public void ajouterPointVictoire(){
        this.pointVictoire++;
    }
    
}
