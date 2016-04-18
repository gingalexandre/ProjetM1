/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Arthur
 */
/**
 * @author jerome
 *
 */
public class Partie implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/**
	 * Joueurs de la partie. Le joueur 4 peut etre null.
	 */
	private Joueur joueur1, joueur2, joueur3, joueur4;
	
	/**
	 * Plateau de la partie
	 */
	private Plateau plateau;
	
    private Ressource ressources;
    
    private ArrayList<Joueur> ordreJeu = new ArrayList<Joueur>();
    
    /** 
     * De 1 à 3 si 3 joueurs
     * De 1 à 4 si 4 joueurs
     */
    private static int tour;
    
    /**
     * @param plateau - plateau de la partie
     */
    public Partie(Plateau plateau){
    	 this.plateau = plateau;
    	 this.ordreJeu = new ArrayList<Joueur>();
    	 this.ressources = new Ressource();
    	 tour = 0;
    }
    
    /**
     * @param joueur1 - joueur 1 de la partie 
     * @param joueur2 - joueur 2 de la partie 
     * @param joueur3 - joueur 3 de la partie 
     * @param plateau - plateau de la partie
     */
    public Partie(Joueur joueur1, Joueur joueur2, Joueur joueur3, Plateau plateau){
    	this.joueur1 = joueur1;
    	this.joueur2 = joueur2;
    	this.joueur3 = joueur3;
    	this.plateau = plateau;
    	this.ordreJeu = new ArrayList<Joueur>();
   	 	this.ressources = new Ressource();
   	 	tour = 0;
    }
    
    
    /**
     * @param joueur1 - joueur 1 de la partie 
     * @param joueur2 - joueur 2 de la partie 
     * @param joueur3 - joueur 3 de la partie 
     * @param joueur4 - joueur 4 de la partie 
     * @param plateau - plateau de la partie
     */
    public Partie(Joueur joueur1, Joueur joueur2, Joueur joueur3, Joueur joueur4, Plateau plateau){
    	this.joueur1 = joueur1;
    	this.joueur2 = joueur2;
    	this.joueur3 = joueur3;
    	this.joueur4 = joueur4;
    	this.plateau = plateau;
    	this.ordreJeu = new ArrayList<Joueur>();
   	 	this.ressources = new Ressource();
   	 	tour = 0;
    }
    
    /**
     * @return le nombre de joueurs de la partie
     */
    public int getNombreJoueurs(){
    	int compteur = 0;
    	if(joueur1 != null){
    		compteur++;
    	}
    	if(joueur2 != null){
    		compteur++;
    	}
    	if(joueur3 != null){
    		compteur++;
    	}
    	if(joueur4 != null){
    		compteur++;
    	}
    	return compteur;
    }
    
    public Joueur getJoueurTour(){
    	return ordreJeu.get(tour);
    }
    
    public void incrementeTour(){
    	if(joueur4 != null){
    		if(tour <= 3){
    			tour++;
    		}
    		else{
    			tour = 0;
    		}
    	}
    	else{
    		if(tour <= 2){
    			tour++;
    		}
    		else{
    			tour = 0;
    		}
    	}
    }
    
    public Joueur getJoueurByCouleur(String couleur){
    	if(this.joueur1.getCouleur()==couleur){
    		return this.joueur1;
    	}
    	else if(this.joueur2.getCouleur()==couleur){
    		return this.joueur2;
    	}
    	else if(this.joueur3.getCouleur()==couleur){
    		return this.joueur3;
    	}
    	else if(this.joueur4.getCouleur()==couleur){
    		return this.joueur4;
    	}
    	return null;
    }

	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
		this.ordreJeu.add(this.joueur1);
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
		this.ordreJeu.add(this.joueur2);
	}

	public Joueur getJoueur3() {
		return joueur3;
	}

	public void setJoueur3(Joueur joueur3) {
		this.joueur3 = joueur3;
		this.ordreJeu.add(this.joueur3);
	}

	public Joueur getJoueur4() {
		return joueur4;
	}

	public void setJoueur4(Joueur joueur4) {
		this.joueur4 = joueur4;
		this.ordreJeu.add(this.joueur4);
	}

	public Ressource getRessources() {
		return ressources;
	}

	public void setRessources(Ressource ressources) {
		this.ressources = ressources;
	}
	
	/**
	 * Arrange la liste ordreJeu par ordre d'âge des joueurs
	 */
	public void arrangerOrdreTour(){
		Comparator<Joueur> c = new Comparator<Joueur>() {
            @Override
            public int compare(Joueur j1, Joueur j2) {
                return j1.compareTo(j2);
            }
        };
        this.ordreJeu.sort(c);
	}
	
	/**
	 * @return le joueur le plus vieux de la partie
	 */
	public Joueur getJoueurLePlusVieux(){
		return ordreJeu.get(0);
	}
}
