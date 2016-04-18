package serveur.reseau;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;

import exception.TooMuchPlayerException;
import serveur.modele.Joueur;
import serveur.reseau.communicationClients.GestionnaireBDD;
import serveur.reseau.communicationClients.GestionnairePartie;
import serveur.reseau.communicationClients.GestionnaireUI;

/**
 * Classe impl�mentant le serveur, qui communique avec les proxy
 * @author jerome
 */
public class ServeurImpl extends UnicastRemoteObject implements Serveur {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Contient la liste des joueurs connect�s au serveur
	 */
	private ArrayList<JoueurServeur> joueurServeurs = new ArrayList<JoueurServeur>();
	
	/**
	 * Nombre max de joueurServeurs 
	 */
	private int nombre_max_joueurs;
	
	/**
	 * Gestionnaire de la base de donnees
	 */
	private GestionnaireBDD gestionnaireBDD;
	
	/**
	 * Gestionnaire de la partie
	 */
	private GestionnairePartie gestionnairePartie;
	
	/**
	 * Gestionnaire de l'interface
	 */
	private GestionnaireUI gestionnaireUI;
	
	/**
	 * Constructeur de la classe ServeurImpl
	 * @throws RemoteException
	 */
	public ServeurImpl(int nombre_max_joueurs) throws RemoteException{
		this.nombre_max_joueurs = nombre_max_joueurs;
		this.gestionnaireBDD = new GestionnaireBDD();
		this.gestionnaireUI = new GestionnaireUI();
		this.gestionnairePartie = new GestionnairePartie(this.gestionnaireUI.getPlateau());
	}
	
	/**
	 * Enregistre un joueur sur le serveur
	 * @param nouveauJoueurServeur - joueur a ajouter
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	@Override
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur, String nom, Date date) throws RemoteException, TooMuchPlayerException{
		if(joueurServeurs.size() < nombre_max_joueurs){
			Joueur joueur = new Joueur(nom, date);
			joueurServeurs.add(nouveauJoueurServeur);
			switch(joueurServeurs.size()){
				case 1:
					joueur.setCouleur("rouge");
					break;
				case 2:
					joueur.setCouleur("bleu");
					break;
				case 3:
					joueur.setCouleur("vert");
					break;
				case 4:
					joueur.setCouleur("orange");
					break;
				default: 
					break;
			}
			nouveauJoueurServeur.setJoueur(joueur);
			envoyerJoueurAuGestionnaire(nouveauJoueurServeur);
		}
		else{
			throw new TooMuchPlayerException("Connexion impossible. Il y a deja "+nombre_max_joueurs+" joueurs connectes sur le serveur.");
		}
	}
	
	/**
	 * Permet d'ajouter un joueur a la liste de joueurs des gestionnaires
	 * @param nouveauJoueurServeur - joueur a envoyer
	 * @throws RemoteException 
	 */
	private void envoyerJoueurAuGestionnaire(JoueurServeur nouveauJoueurServeur) throws RemoteException{
		this.gestionnairePartie.enregistrerJoueur(nouveauJoueurServeur);
		this.gestionnairePartie.ajouterJoueurPartie(nouveauJoueurServeur.getJoueur());
		this.gestionnaireUI.enregistrerJoueur(nouveauJoueurServeur);
	}
	
	/**
	 * Permet d'obtenir le gestionnaire de base de donnees
	 * @return le gestionnaire de base de donnees
	 * @throws RemoteException
	 */
	@Override
	public GestionnaireBDD getGestionnaireBDD() throws RemoteException {
		return this.gestionnaireBDD;
	}

	/**
	 * Permet d'obtenir le gestionnaire de partie
	 * @return le gestionnaire de partie
	 * @throws RemoteException
	 */
	@Override
	public GestionnairePartie getGestionnairePartie() throws RemoteException {
		return this.gestionnairePartie;
	}

	/**
	 * Permet d'obtenir le gestionnaire de l'interface
	 * @return le gestionnaire de base de l'interface
	 * @throws RemoteException
	 */
	@Override
	public GestionnaireUI getGestionnaireUI() throws RemoteException {
		return this.gestionnaireUI;
	}
}
