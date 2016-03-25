package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.modele.Message;
import client.modele.Plateau;

/**
 * Interface du proxy entre client et serveur
 * @author jerome
 */
public interface JoueurServeur extends Remote{
	
	/**
	 * Re�oit le message transmit par le serveur et l'envoie au joueur et l'envoie au controller du chat
	 * @param message
	 * @throws RemoteException
	 */
	void recevoirMessage(Message message) throws RemoteException;
	
	/**
	 * Re�oit le plateau envoy� par le serveur et l'envoie au controller du plateau
	 * @param plateau
	 * @throws RemoteException
	 */
	void envoyerPlateau(Plateau plateau) throws RemoteException;
	
	/**
	 * Indique la couleur de jeu du joueur
	 * @param color
	 */
	void setCouleur(String couleur) throws RemoteException;
}
