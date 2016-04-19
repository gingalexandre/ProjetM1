package serveur.reseau;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;

import exception.TooMuchPlayerException;
import serveur.bdd.Sauvegarde;
import serveur.modele.Joueur;
import serveur.modele.service.JoueurInterface;
import serveur.reseau.communicationClients.GestionnaireBDD;
import serveur.reseau.communicationClients.GestionnairePartie;
import serveur.reseau.communicationClients.GestionnaireUI;
import serveur.reseau.communicationClients.service.GestionnaireBDDInterface;
import serveur.reseau.communicationClients.service.GestionnairePartieInterface;
import serveur.reseau.communicationClients.service.GestionnaireUIInterface;

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
<<<<<<< HEAD
	 * Gestionnaire de la base de donn�es
=======
	 * Gestionnaire de la base de donnees
>>>>>>> master
	 */
	private GestionnaireBDDInterface gestionnaireBDD;
	
	/**
	 * Gestionnaire de la partie
	 */
	private GestionnairePartieInterface gestionnairePartie;
	
	/**
	 * Gestionnaire de l'interface
	 */
	private GestionnaireUIInterface gestionnaireUI;
	
	/**
	 * Constructeur de la classe ServeurImpl
	 * @throws RemoteException
	 */
	public ServeurImpl(int nombre_max_joueurs) throws RemoteException{
		this.nombre_max_joueurs = nombre_max_joueurs;
		gestionnaireBDD = new GestionnaireBDD();
		gestionnaireUI = new GestionnaireUI();
		gestionnairePartie = new GestionnairePartie(gestionnaireUI.getPlateau());
	}
	
	/**
	 * Enregistre un joueur sur le serveur
<<<<<<< HEAD
	 * @param nouveauJoueurServeur - joueur � ajouter
=======
	 * @param nouveauJoueurServeur - joueur a ajouter
>>>>>>> master
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	@Override
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur, String nom, Date date) throws RemoteException, TooMuchPlayerException{
		if(joueurServeurs.size() < nombre_max_joueurs){
			JoueurInterface joueur = new Joueur(nom, date);
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
<<<<<<< HEAD
	 * Permet d'ajouter un joueur � la liste de joueurs des gestionnaires
	 * @param nouveauJoueurServeur - joueur � envoyer
=======
	 * Permet d'ajouter un joueur a la liste de joueurs des gestionnaires
	 * @param nouveauJoueurServeur - joueur a envoyer
>>>>>>> master
	 * @throws RemoteException 
	 */
	private void envoyerJoueurAuGestionnaire(JoueurServeur nouveauJoueurServeur) throws RemoteException{
		gestionnairePartie.enregistrerJoueur(nouveauJoueurServeur);
		gestionnairePartie.ajouterJoueurPartie(nouveauJoueurServeur.getJoueur());
		gestionnaireUI.enregistrerJoueur(nouveauJoueurServeur);
	}
	
	/**
	 * Permet d'obtenir le gestionnaire de base de donnees
	 * @return le gestionnaire de base de donnees
	 * @throws RemoteException
	 */
	@Override
	public GestionnaireBDDInterface getGestionnaireBDD() throws RemoteException {
		return gestionnaireBDD;
	}

	/**
	 * Permet d'obtenir le gestionnaire de partie
	 * @return le gestionnaire de partie
	 * @throws RemoteException
	 */
	@Override
	public GestionnairePartieInterface getGestionnairePartie() throws RemoteException {
		return this.gestionnairePartie;
	}

	/**
	 * Permet d'obtenir le gestionnaire de l'interface
	 * @return le gestionnaire de base de l'interface
	 * @throws RemoteException
	 */
	@Override
	public GestionnaireUIInterface getGestionnaireUI() throws RemoteException {
		return gestionnaireUI;
	}

	@Override
	public void enregistrerJoueur(JoueurServeur joueurServeur, Date date) throws RemoteException, TooMuchPlayerException {
		System.out.println("Yoo");
	}

	/**
	 * Permet de sauvegarder la partie
	 */
	@Override
	public void enregistrerPartie() throws RemoteException {
		new Sauvegarde();
	}
}
