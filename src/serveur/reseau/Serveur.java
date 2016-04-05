package serveur.reseau;

import java.rmi.Remote;
import java.rmi.RemoteException;

import exception.TooMuchPlayerException;

import serveur.reseau.communicationClients.GestionnaireBDD;
import serveur.reseau.communicationClients.GestionnairePartie;
import serveur.reseau.communicationClients.GestionnaireUI;

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
	void enregistrerJoueur(JoueurServeur joueurServeur) throws RemoteException, TooMuchPlayerException;
	
	/**
	 * Permet d'obtenir le gestionnaire de base de donn�es
	 * @return le gestionnaire de base de donn�es
	 * @throws RemoteException
	 */
	GestionnaireBDD getGestionnaireBDD() throws RemoteException;
	
	
	/**
	 * Permet d'obtenir le gestionnaire de partie
	 * @return le gestionnaire de partie
	 * @throws RemoteException
	 */
	GestionnairePartie getGestionnairePartie() throws RemoteException;
	
	/**
	 * Permet d'obtenir le gestionnaire de l'interface
	 * @return le gestionnaire de base de l'interface
	 * @throws RemoteException
	 */
	GestionnaireUI getGestionnaireUI() throws RemoteException;
}
