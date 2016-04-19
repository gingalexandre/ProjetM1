package serveur.reseau.serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;

import exception.TooMuchPlayerException;
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
}
