package serveur.reseau.communicationClients;

import java.io.Serializable;
import java.util.ArrayList;

import serveur.modele.Partie;
import serveur.modele.Plateau;
import serveur.reseau.JoueurServeur;

/**
 * Classe qui s'occupe des échanges concernant la partie entre les clients et le serveur
 * @author jerome
 */
public class GestionnairePartie implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Contient la liste des joueurs connectés au serveur
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
		return partie;
	}
	
	/**
	 * Enregistre un nouveau joueur dans la liste des joueurs
	 * @param nouveauJoueurServeur - joueur à enregistrer
	 */
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur){
		joueurServeurs.add(nouveauJoueurServeur);
	}
}
