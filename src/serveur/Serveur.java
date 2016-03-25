package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.modele.Message;
import exception.TooMuchPlayerException;
import service.JoueurServeur;

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
	 * Diffuse un message envoy� par un joueur � tous les autre joueurs
	 * @param message
	 * @throws RemoteException
	 */
	void diffuserMessage(Message message) throws RemoteException;
	
	/**
	 * Envoie le plateau de jeu au joueur pass� en param�tre
	 * @param proxy
	 */
	void envoyerPlateau(JoueurServeur proxy) throws RemoteException;
}
