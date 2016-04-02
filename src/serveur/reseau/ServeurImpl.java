package serveur.reseau;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import exception.TooMuchPlayerException;
import serveur.modele.Joueur;
import serveur.reseau.communicationClients.GestionnaireBDD;
import serveur.reseau.communicationClients.GestionnairePartie;
import serveur.reseau.communicationClients.GestionnaireUI;

/**
 * Classe implémentant le serveur, qui communique avec les proxy
 * @author jerome
 */
public class ServeurImpl extends UnicastRemoteObject implements Serveur {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Contient la liste des joueurs connectés au serveur
	 */
	private ArrayList<JoueurServeur> joueurServeurs = new ArrayList<JoueurServeur>();
	
	/**
	 * Nombre max de joueurServeurs 
	 */
	private final static int NOMBRE_MAX_JOUEURS = 4;
	
	/**
	 * Gestionnaire de la base de données
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
	public ServeurImpl() throws RemoteException{
		this.gestionnaireBDD = new GestionnaireBDD();
		this.gestionnaireUI = new GestionnaireUI();
		this.gestionnairePartie = new GestionnairePartie(this.gestionnaireUI.getPlateau());
	}
	
	/**
	 * Enregistre un joueur sur le serveur
	 * @param nouveauJoueurServeur - joueur à ajouter
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	@Override
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur) throws RemoteException, TooMuchPlayerException{
		if(joueurServeurs.size() < NOMBRE_MAX_JOUEURS){
			Joueur joueur = new Joueur();
			joueurServeurs.add(nouveauJoueurServeur);
			switch(joueurServeurs.size()){
				case 1:
					joueur.setCouleur("rouge");
					this.gestionnairePartie.getPartie().setJoueur1(joueur);
					break;
				case 2:
					joueur.setCouleur("bleu");
					this.gestionnairePartie.getPartie().setJoueur2(joueur);
					break;
				case 3:
					joueur.setCouleur("vert");
					this.gestionnairePartie.getPartie().setJoueur3(joueur);
					break;
				case 4:
					joueur.setCouleur("orange");
					this.gestionnairePartie.getPartie().setJoueur4(joueur);
					break;
				default: 
					break;
			}
			nouveauJoueurServeur.setJoueur(joueur);
			envoyerJoueurAuGestionnaire(nouveauJoueurServeur);
		}
		else{
			throw new TooMuchPlayerException("Connexion impossible. Il y a déjà 4 joueurs connectés sur le serveur.");
		}
	}
	
	/**
	 * Permet d'ajouter un joueur à la liste de joueurs des gestionnaires
	 * @param nouveauJoueurServeur - joueur à envoyer
	 */
	private void envoyerJoueurAuGestionnaire(JoueurServeur nouveauJoueurServeur){
		this.gestionnairePartie.enregistrerJoueur(nouveauJoueurServeur);
		this.gestionnaireUI.enregistrerJoueur(nouveauJoueurServeur);
	}
	
	/**
	 * Permet d'obtenir le gestionnaire de base de données
	 * @return le gestionnaire de base de données
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
