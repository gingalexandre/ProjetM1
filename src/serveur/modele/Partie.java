/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.modele;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import serveur.modele.service.JoueurInterface;

/**
 *
 * @author Arthur
 */
public class Partie implements Serializable{
    
	private static final long serialVersionUID = 1L;

	/**
	 * Joueurs de la partie. Le JoueurInterface 4 peut etre null.
	 */
	private JoueurInterface joueur1, joueur2, joueur3, joueur4;
	
	/**
	 * Plateau de la partie
	 */
	private Plateau plateau;
	
    private Ressource ressources;
    
    private ArrayList<JoueurInterface> ordreJeu;
    
    /** 
     * De 1 à 3 si 3 joueurs
     * De 1 à 4 si 4 joueurs
     */
    private int tour;
    
    /**
     * @param plateau - plateau de la partie
     */
    public Partie(Plateau plateau){
    	 this.plateau = plateau;
    	 this.ordreJeu = new ArrayList<>();
    	 this.ressources = new Ressource();
    	 this.tour = 1;
    }
    
    /**
     * @param joueur1 - JoueurInterface 1 de la partie 
     * @param joueur2 - JoueurInterface 2 de la partie 
     * @param joueur3 - JoueurInterface 3 de la partie 
     * @param plateau - plateau de la partie
     */
    public Partie(JoueurInterface joueur1, JoueurInterface joueur2, JoueurInterface joueur3, Plateau plateau){
    	this.joueur1 = joueur1;
    	this.joueur2 = joueur2;
    	this.joueur3 = joueur3;
    	this.plateau = plateau;
    	this.ordreJeu = new ArrayList<>();
   	 	this.ressources = new Ressource();
   	 	tour = 1;
    }
    
    
    /**
     * @param joueur1 - JoueurInterface 1 de la partie 
     * @param joueur2 - JoueurInterface 2 de la partie 
     * @param joueur3 - JoueurInterface 3 de la partie 
     * @param joueur4 - JoueurInterface 4 de la partie 
     * @param plateau - plateau de la partie
     */
    public Partie(JoueurInterface joueur1, JoueurInterface joueur2, JoueurInterface joueur3, JoueurInterface joueur4, Plateau plateau){
    	this.joueur1 = joueur1;
    	this.joueur2 = joueur2;
    	this.joueur3 = joueur3;
    	this.joueur4 = joueur4;
    	this.plateau = plateau;
    	this.ordreJeu = new ArrayList<>();
   	 	this.ressources = new Ressource();
   	 	tour = 1;
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
    
    public JoueurInterface getJoueurTour(){
    	return ordreJeu.get(tour-1);
    }
    
    public ArrayList<JoueurInterface> getOrdreJeu() {
		return ordreJeu;
	}

	public void setOrdreJeu(ArrayList<JoueurInterface> ordreJeu) {
		this.ordreJeu = ordreJeu;
	}

	public void incrementeTour(){
		System.out.println(tour);
    	if(joueur4 != null){
    		if(tour <= 3){
    			tour++;
    		}
    		else{
    			tour = 1;
    		}
    	}
    	else{
    		if(tour <= 2){
    			tour++;
    		}
    		else{
    			tour = 1;
    		}
    	}
    }
    
    public JoueurInterface getJoueurByCouleur(String couleur) throws RemoteException{
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

	public JoueurInterface getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(JoueurInterface joueur1) {
		this.joueur1 = joueur1;
		this.ordreJeu.add(this.joueur1);
	}

	public JoueurInterface getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(JoueurInterface joueur2) {
		this.joueur2 = joueur2;
		this.ordreJeu.add(this.joueur2);
	}

	public JoueurInterface getJoueur3() {
		return joueur3;
	}

	public void setJoueur3(JoueurInterface joueur3) {
		this.joueur3 = joueur3;
		this.ordreJeu.add(this.joueur3);
	}

	public JoueurInterface getJoueur4() {
		return joueur4;
	}

	public void setJoueur4(JoueurInterface joueur4) {
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
		Comparator<JoueurInterface> c = new Comparator<JoueurInterface>() {
            @Override
            public int compare(JoueurInterface j1, JoueurInterface j2) {
                try {
					return j1.compareTo(j2);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
            }
        };
        ordreJeu.sort(c);
	}
	
	/**
	 * @return le JoueurInterface le plus vieux de la partie
	 */
	public JoueurInterface getJoueurLePlusVieux(){
		return ordreJeu.get(0);
	}
}
