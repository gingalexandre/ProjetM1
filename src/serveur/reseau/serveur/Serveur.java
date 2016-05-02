package serveur.reseau.serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import exception.TooMuchPlayerException;
import serveur.modele.service.JoueurInterface;
import serveur.reseau.communicationClients.service.GestionnaireBDDInterface;
import serveur.reseau.communicationClients.service.GestionnairePartieInterface;
import serveur.reseau.communicationClients.service.GestionnaireUIInterface;
import serveur.reseau.proxy.JoueurServeur;

/**
 * Classe principale pour les communications entre client et serveur
 * @author jerome
 */
public interface Serveur extends Remote{
	
	/**
	 * Permet d'obtenir la liste des proxys
	 * @return la liste des proxy connectés sur le serveur
	 */
	ArrayList<JoueurServeur> getListeJoueurs() throws RemoteException;
	
	/**
	 * Permet d'obtenir un proxy grâce au nom du joueur
	 * @return le proxy grâce à son nom
	 * @throws RemoteException
	 */
	JoueurServeur getJoueur(String nomJoueur) throws RemoteException;
	
	/**
	 * Enregistre un joueur au serveur
	 * @param communication
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	void enregistrerJoueur(JoueurServeur joueurServeur, String nom, Date date) throws RemoteException, TooMuchPlayerException;
	
	/**
	 * Enregistre la partie
	 * @throws RemoteException
	 */
	void enregistrerPartie()  throws RemoteException;
	
	/**
	 * Appel le GestionnaireUI pour envoyer un message indiquant le nombre de joueurs connectés aux joueurs 
	 * @throws RemoteException 
	 */
	void envoyerNombreJoueursConnectes() throws RemoteException;
	
	/**
	 * Permet d'obtenir le gestionnaire de base de données
	 * @return le gestionnaire de base de données
	 * @throws RemoteException
	 */
	GestionnaireBDDInterface getGestionnaireBDD() throws RemoteException;
	
	
	/**
	 * Permet d'obtenir le gestionnaire de partie
	 * @return le gestionnaire de partie
	 * @throws RemoteException
	 */
	GestionnairePartieInterface getGestionnairePartie() throws RemoteException;
	
	/**
	 * Permet d'obtenir le gestionnaire de l'interface
	 * @return le gestionnaire de base de l'interface
	 * @throws RemoteException
	 */
	GestionnaireUIInterface getGestionnaireUI() throws RemoteException;
	
	/**
	 * Permet de supprimer un Joueur du serveur
	 * @param joueurASupprimer
	 * @throws RemoteException
	 */
	void supprimerJoueur(JoueurInterface joueurASupprimer)  throws RemoteException;

    /**
     * Permet à un joueur de quitter la partie
     * @param nomJoueur
     * @throws RemoteException
     */
    void quitterPartie(JoueurInterface nomJoueur) throws RemoteException;
}
