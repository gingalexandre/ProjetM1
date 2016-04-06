package serveur.reseau.communicationClients;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
	private ArrayList<JoueurServeur> joueursServeur = new ArrayList<JoueurServeur>();
	
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
	 * @param nouveauJoueurServeur - joueur � enregistrer
	 */
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur){
		joueursServeur.add(nouveauJoueurServeur);
	}
	
	public void envoyerAutresJoueurs() throws RemoteException{
		ArrayList<Joueur> autresJoueurs = new ArrayList<Joueur>();
		for(JoueurServeur joueurServeur : joueursServeur){
			autresJoueurs = recupererAutresJoueurs(joueurServeur.getJoueur());
			joueurServeur.envoyerAutresJoueurs(autresJoueurs);
			
			autresJoueurs.clear();
		}
	}
	
	/**
	 * M�thode qui renvoie la liste des joueurs mis � part le joueur indiqu� en param�tre
	 * @param joueurQuiAppelle
	 * @return la liste des autres joueurs connect�s sur le serveur
	 * @throws RemoteException
	 */
	public ArrayList<Joueur> recupererAutresJoueurs(Joueur joueurQuiAppelle) throws RemoteException{
		ArrayList<Joueur> autresJoueurs = new ArrayList<Joueur>();
		for(JoueurServeur joueurServeur : joueursServeur){
			// Le nom d'utilisateur �tant unique, on fait la v�rification dessus
			Joueur joueur = joueurServeur.getJoueur();
			if(!joueurServeur.getJoueur().getNomUtilisateur().equals(joueurQuiAppelle.getNomUtilisateur())){
				autresJoueurs.add(joueurServeur.getJoueur());
			}
		}
		return autresJoueurs;
	}
}
