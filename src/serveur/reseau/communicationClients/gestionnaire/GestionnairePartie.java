package serveur.reseau.communicationClients.gestionnaire;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import exception.TooMuchPlayerException;
import serveur.modele.Message;
import serveur.modele.Partie;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PartieInterface;
import serveur.modele.service.PlateauInterface;
import serveur.reseau.communicationClients.service.GestionnairePartieInterface;
import serveur.reseau.proxy.JoueurServeur;

/**
 * Classe qui s'occupe des echanges concernant la partie entre les clients et le serveur
 * @author jerome
 */
public class GestionnairePartie extends UnicastRemoteObject implements GestionnairePartieInterface{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Contient la liste des joueurs connect�s au serveur
	 */
	private ArrayList<JoueurServeur> joueursServeur = new ArrayList<JoueurServeur>();
	
	/**
	 * Partie sur laquelle les joueurs vont jouer
	 */
	private Partie partie;
	
	private boolean premierePhasePartie;
	
	/**
	 * Constructeur de la classe GestionnairePartie
	 * @param plateauInterface 
	 * @param plateau - plateau de jeu
	 */
	public GestionnairePartie(PlateauInterface plateauInterface) throws RemoteException{
		this.partie = new Partie(plateauInterface);
		this.premierePhasePartie = true;
	}

	/**
	 * Permet d'obtenir la partie
	 * @return la partie
	 */
	public PartieInterface getPartie() throws RemoteException{
		return this.partie;
	}
	
	/**
	 * Enregistre un nouveau JoueurInterface dans la liste des joueurs
	 * @param nouveauJoueurServeur - JoueurInterface a enregistrer
	 */
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur){
		joueursServeur.add(nouveauJoueurServeur);
	}
	
	/** 
	 * Envoie la liste des autres joueurs
	 */
	public void envoyerAutresJoueurs() throws RemoteException{
		ArrayList<JoueurInterface> autresJoueurs = new ArrayList<JoueurInterface>();
		for(JoueurServeur joueurServeur : joueursServeur){
			autresJoueurs = recupererAutresJoueurs(joueurServeur.getJoueur());
			joueurServeur.envoyerAutresJoueurs(autresJoueurs);
			
			autresJoueurs.clear();
		}
	}
	
	/**
	 * Methode qui renvoie la liste des joueurs mis a part le JoueurInterface indique en parametre
	 * @param joueurQuiAppelle
	 * @return la liste des autres joueurs connect�s sur le serveur
	 * @throws RemoteException
	 */
	public ArrayList<JoueurInterface> recupererAutresJoueurs(JoueurInterface joueurQuiAppelle) throws RemoteException{
		ArrayList<JoueurInterface> autresJoueurs = new ArrayList<JoueurInterface>();
		for(JoueurServeur joueurServeur : joueursServeur){
			if(!joueurServeur.getJoueur().getNomUtilisateur().equals(joueurQuiAppelle.getNomUtilisateur())){
				autresJoueurs.add(joueurServeur.getJoueur());
			}
		}
		return autresJoueurs;
	}
	
	/**
	 * Ajoute le JoueurInterface passe en parametre a la partie
	 * @param nouveauJoueurInterface - JoueurInterface a ajouter a la partie
	 * @throws TooMuchPlayerException
	 */
	public void ajouterJoueurPartie(JoueurInterface nouveauJoueur) throws RemoteException{
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
	public void enableBoutons(JoueurInterface j) throws RemoteException {
		for(JoueurServeur joueurServeur : joueursServeur){
			if(joueurServeur.getJoueur().getNomUtilisateur().equals(j.getNomUtilisateur())){
				joueurServeur.setButtons(false);
			}
		}
	}
	
	/**
	 * Met un JoueurInterface a pret
	 * @param joueur
	 * @throws RemoteException 
	 */
	public void joueurPret(JoueurInterface joueur) throws RemoteException{
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
			if(!joueurServeur.getJoueur().getPret()){
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
		JoueurInterface joueurPlusVieux = partie.getJoueurLePlusVieux();
		for(JoueurServeur joueurServeur : joueursServeur){
			joueurServeur.recevoirMessage(new Message("La partie a commence !\nComme c'est le plus âgé, c'est à "+joueurPlusVieux.getNomUtilisateur()+" de jouer."));
		}
		
		lancerTourPremierJoueur(joueurPlusVieux);
	}

	/**
	 * Lance le tour du premier joueur, le plus vieux
	 * @throws RemoteException 
	 */
	private void lancerTourPremierJoueur(JoueurInterface joueurPlusVieux) throws RemoteException {
		partie.setPartieCommence(true);
		for(JoueurServeur joueurServeur : joueursServeur){
			// On compare sur le nom d'utilisateur qui est unique
			if(joueurPlusVieux.getNomUtilisateur().equals(joueurServeur.getJoueur().getNomUtilisateur())){
				joueurServeur.setButtons(false);
				joueurServeur.lancerTour();
			}
			else{
				joueurServeur.setButtons(true);
			}
		}
	}

	/**
	 * Finit le tour d'un joueur et renvoie le joueur suivant
	 * @throws RemoteException
	 */
	public JoueurInterface finirTour() throws RemoteException {
		partie.incrementeTour();
		
		JoueurInterface joueurTour = this.partie.getJoueurTour();
		enableBoutons(joueurTour);
		return joueurTour;
	}

	/**
	 * Méthode renvoyant une arrayList des joueurs du serveur
	 */
	public ArrayList<JoueurServeur> recupererTousLesJoueurs() throws RemoteException {	
		return joueursServeur;
	}

	@Override
	public void lancerProchainTour(JoueurInterface joueurTour) throws RemoteException {
		for(JoueurServeur joueurServeur : joueursServeur) {
			if(joueurServeur.getJoueur().getNomUtilisateur().equals(joueurTour.getNomUtilisateur())){
				joueurServeur.lancerTour();
			}
		}
		// On vérifie si on est toujours dans la première "phase" de la partie
		if(this.partie.getCompteurTourGlobal() == this.partie.getNombreJoueurs()*2){
			this.premierePhasePartie = false;
		}
	}

	/** 
	 * Permet de savoir  si on est encore dans la première phase de la partie
	 * @return true si on est encore dans la première phase de la partie, false sinon
	 */
	@Override
	public boolean isPremierePhasePartie() throws RemoteException{
		return this.premierePhasePartie;
	}

	/**
	 * Méthode permettant de supprimer un Joueur
	 */
	public void supprimerJoueur(JoueurInterface joueurSupprime) throws RemoteException {
		for(JoueurServeur js : joueursServeur){
			if(js.getJoueur().getNomUtilisateur().equals(joueurSupprime.getNomUtilisateur())){
				joueursServeur.remove(js);
				break;
			}
		}
		
	}

	

}
