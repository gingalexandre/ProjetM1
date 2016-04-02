/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.modele;

/**
 *
 * @author Arthur
 */
public class Partie {
    
	/**
	 * Joueurs de la partie. Le joueur 4 peut être null.s
	 */
	private Joueur joueur1, joueur2, joueur3, joueur4;
	
	/**
	 * Plateau de la partie
	 */
	private Plateau plateau;
	
    private Ressource ressources;
    
    /**
     * @param plateau - plateau de la partie
     */
    public Partie(Plateau plateau){
    	 this.ressources = new Ressource();
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
   	 	this.ressources = new Ressource();
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
   	 	this.ressources = new Ressource();
    }

	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	public Joueur getJoueur3() {
		return joueur3;
	}

	public void setJoueur3(Joueur joueur3) {
		this.joueur3 = joueur3;
	}

	public Joueur getJoueur4() {
		return joueur4;
	}

	public void setJoueur4(Joueur joueur4) {
		this.joueur4 = joueur4;
	}

	public Ressource getRessources() {
		return ressources;
	}

	public void setRessources(Ressource ressources) {
		this.ressources = ressources;
	}
}
