package serveur.bdd.modeleSauvegarde;

import serveur.modele.carte.Carte;

public class CarteSauvegarde {

	String nom;
	
	String chemin;

	public CarteSauvegarde(Carte carte) {
		super();
		this.nom = carte.getNom();
		this.chemin = carte.getCheminImage();
	}
	
	public CarteSauvegarde(){}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	
	
	
}
