package serveur.reseau;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;

import exception.TooMuchPlayerException;
import serveur.reseau.communicationClients.service.GestionnaireBDDInterface;
import serveur.reseau.communicationClients.service.GestionnairePartieInterface;
import serveur.reseau.communicationClients.service.GestionnaireUIInterface;

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
