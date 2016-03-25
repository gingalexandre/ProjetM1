package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.modele.Message;
import client.modele.Plateau;

/**
 * Interface du proxy entre client et serveur
 * @author jerome
 */
public interface Joueur extends Remote{
	
	/**
	 * Reçoit le message transmit par le serveur et l'envoie au joueur et l'envoie au controller du chat
	 * @param message
	 * @throws RemoteException
	 */
	void recevoirMessage(Message message) throws RemoteException;
	
	/**
	 * Reçoit le plateau envoyé par le serveur et l'envoie au controller du plateau
	 * @param plateau
	 * @throws RemoteException
	 */
	void envoyerPlateau(Plateau plateau) throws RemoteException;
}
