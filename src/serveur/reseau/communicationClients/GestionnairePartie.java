package serveur.reseau.communicationClients;

import java.io.Serializable;
import java.util.ArrayList;

import exception.TooMuchPlayerException;
import serveur.modele.Joueur;
import serveur.modele.Partie;
import serveur.modele.Plateau;
import serveur.reseau.JoueurServeur;

/**
 * Classe qui s'occupe des �changes concernant la partie entre les clients et le serveur
 * @author jerome
 */
public class GestionnairePartie implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Contient la liste des joueurs connect�s au serveur
	 */
	private ArrayList<JoueurServeur> joueurServeurs = new ArrayList<JoueurServeur>();
	
	/**
	 * Partie sur laquelle les joueurs vont jouer
	 */
	private Partie partie;
	
	/**
	 * Constructeur de la classe GestionnairePartie
	 * @param plateau - plateau de jeu
	 */
	public GestionnairePartie(Plateau plateau){
		this.partie = new Partie(plateau);
	}

	/**
	 * Permet d'obtenir la partie
	 * @return la partie
	 */
	public Partie getPartie() {
		return this.partie;
	}
	
	/**
	 * Enregistre un nouveau joueur dans la liste des joueurs
	 * @param nouveauJoueurServeur - joueur � enregistrer
	 */
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur){
		joueurServeurs.add(nouveauJoueurServeur);
	}
	
	/**
	 * Ajoute le joueur pass� en param�tre � la partie
	 * @param nouveauJoueur - joueur � ajouter � la partie
	 * @throws TooMuchPlayerException
	 */
	public void ajouterJoueurPartie(Joueur nouveauJoueur){
		switch(this.joueurServeurs.size()){
			case 1:
				this.partie.setJoueur1(nouveauJoueur);
				break;
			case 2:
				this.partie.setJoueur2(nouveauJoueur);
				break;
			case 3:
				this.partie.setJoueur3(nouveauJoueur);
				break;
			case 4:
				this.partie.setJoueur4(nouveauJoueur);
				break;
			default: 
				break;
		}
	}
}
