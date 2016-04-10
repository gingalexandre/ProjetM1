/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.modele;

import java.io.Serializable;

/**
 *
 * @author Arthur
 */
public class Partie implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Joueurs de la partie. Le joueur 4 peut �tre null.s
	 */
	private Joueur joueur1, joueur2, joueur3, joueur4;

	private static Joueur[] joueurs;

	/**
	 * Plateau de la partie
	 */
	private Plateau plateau;

	private Ressource ressources;

	/*
	 * De 1 � 3 si 3 joueurs De 1 � 4 si 4 joueurs
	 */
	private int tour;

	/**
	 * @param plateau
	 *            - plateau de la partie
	 */
	public Partie(Plateau plateau) {
		this.plateau = plateau;
		this.ressources = new Ressource();
	}

	/**
	 * @param joueur1
	 *            - joueur 1 de la partie
	 * @param joueur2
	 *            - joueur 2 de la partie
	 * @param joueur3
	 *            - joueur 3 de la partie
	 * @param plateau
	 *            - plateau de la partie
	 */
	public Partie(Joueur joueur1, Joueur joueur2, Joueur joueur3, Plateau plateau) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.joueur3 = joueur3;
		this.plateau = plateau;
		this.ressources = new Ressource();
		joueurs[0] = joueur1;
		joueurs[1] = joueur2;
		joueurs[3] = joueur3;
		joueurs[4] = joueur4;
	}

	/**
	 * @param joueur1
	 *            - joueur 1 de la partie
	 * @param joueur2
	 *            - joueur 2 de la partie
	 * @param joueur3
	 *            - joueur 3 de la partie
	 * @param joueur4
	 *            - joueur 4 de la partie
	 * @param plateau
	 *            - plateau de la partie
	 */
	public Partie(Joueur joueur1, Joueur joueur2, Joueur joueur3, Joueur joueur4, Plateau plateau) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.joueur3 = joueur3;
		this.joueur4 = joueur4;
		this.plateau = plateau;
		this.ressources = new Ressource();
	}

	public Joueur getJoueurByCouleur(String couleur) {
		if (this.joueur1.getCouleur() == couleur) {
			return this.joueur1;
		} else if (this.joueur2.getCouleur() == couleur) {
			return this.joueur2;
		} else if (this.joueur3.getCouleur() == couleur) {
			return this.joueur3;
		} else if (this.joueur4.getCouleur() == couleur) {
			return this.joueur4;
		}
		return null;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public static Joueur[] getJoueurs() {
		return joueurs;
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
