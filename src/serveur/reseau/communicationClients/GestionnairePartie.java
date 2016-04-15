package serveur.reseau.communicationClients;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import exception.TooMuchPlayerException;

import serveur.modele.Joueur;
import serveur.modele.Message;
import serveur.modele.Partie;
import serveur.modele.Plateau;
import serveur.reseau.JoueurServeur;

/**
 * Classe qui s'occupe des echanges concernant la partie entre les clients et le serveur
 * @author jerome
 */

/**
 * @author jerome
 *
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
	private static Partie partie;
	
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
	 * @param nouveauJoueurServeur - joueur a enregistrer
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
	 * Methode qui renvoie la liste des joueurs mis a part le joueur indique en parametre
	 * @param joueurQuiAppelle
	 * @return la liste des autres joueurs connect�s sur le serveur
	 * @throws RemoteException
	 */
	public ArrayList<Joueur> recupererAutresJoueurs(Joueur joueurQuiAppelle) throws RemoteException{
		ArrayList<Joueur> autresJoueurs = new ArrayList<Joueur>();
		for(JoueurServeur joueurServeur : joueursServeur){
			if(!joueurServeur.getJoueur().getNomUtilisateur().equals(joueurQuiAppelle.getNomUtilisateur())){
				autresJoueurs.add(joueurServeur.getJoueur());
			}
		}
		return autresJoueurs;
	}
	
	/**
	 * Ajoute le joueur passe en parametre a la partie
	 * @param nouveauJoueur - joueur a ajouter a la partie
	 * @throws TooMuchPlayerException
	 */
	public void ajouterJoueurPartie(Joueur nouveauJoueur){
		switch(this.joueursServeur.size()){
			case 1:
				partie.setJoueur1(nouveauJoueur);
				break;
			case 2:
				partie.setJoueur2(nouveauJoueur);
				break;
			case 3:
				partie.setJoueur3(nouveauJoueur);
				break;
			case 4:
				partie.setJoueur4(nouveauJoueur);
				break;
			default: 
				break;
		}
	}

	/**
	 * Réactive les boutons d'un joueur
	 * @throws RemoteException
	 */
	public void enableBoutons(Joueur j) throws RemoteException {
		for(JoueurServeur joueurServeur : joueursServeur){
			if(joueurServeur.getJoueur().getNomUtilisateur().equals(j.getNomUtilisateur())){
				joueurServeur.enableButtons();
			}
		}
	}
	
	/**
	 * Met un joueur a pret
	 * @param joueur
	 * @throws RemoteException 
	 */
	public void joueurPret(Joueur joueur) throws RemoteException{
		joueur.setPret(true);
		verifierJoueursPrets();
	}
	
	/**
	 * Verifie si tous les joueurs connectes sur le serveur sont prets a jouer. Si ils le sont tous, la partie commence.
	 * @throws RemoteException 
	 */
	public void verifierJoueursPrets() throws RemoteException{
		boolean tousJoueursPrets = true;
		// Verifie si tous les joueurs sont pret. Si un seul ne l'est pas, la partie ne peut pas commencer
		for(JoueurServeur joueurServeur : joueursServeur){
			if(!joueurServeur.getJoueur().isPret()){
				tousJoueursPrets = false;
			}
		}
		// Tous les joueurs sont prets, la partie peut debuter
		if(tousJoueursPrets && partie.getNombreJoueurs() >= 3){
			commencerPartie();
		}
	}

	/**
	 * Commence la partie
	 * @throws RemoteException 
	 */
	private void commencerPartie() throws RemoteException {
		getPartie().arrangerOrdreTour();
		Joueur joueurPlusVieux = partie.getJoueurLePlusVieux();
		for(JoueurServeur joueurServeur : joueursServeur){
			joueurServeur.recevoirMessage(new Message("La partie a commence !\nComme c'est le plus âgé, c'est à "+joueurPlusVieux.getNomUtilisateur()+" de jouer."));
		}
		
		lancerTourPremierJoueur(joueurPlusVieux);
	}

	/**
	 * Lance le tour du premier joueur, le plus vieux
	 * @throws RemoteException 
	 */
	private void lancerTourPremierJoueur(Joueur joueurPlusVieux) throws RemoteException {
		for(JoueurServeur joueurServeur : joueursServeur){
			// On compare sur le nom d'utilisateur qui est unique
			if(joueurPlusVieux.getNomUtilisateur().equals(joueurServeur.getJoueur().getNomUtilisateur())){
				joueurServeur.enableButtons();
			}
			else{
				joueurServeur.disableButtons();
			}
		}
	}
}
