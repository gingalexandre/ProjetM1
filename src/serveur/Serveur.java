package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.modeles.Message;
import exception.TooMuchPlayerException;
import service.Communication;

/**
 * Classe principale pour les communications entre client et serveur
 * @author jerome
 */
public interface Serveur extends Remote{
	
	
	/**
	 * Enregistre une communication au serveur
	 * @param communication
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	void enregistrerCommunication(Communication communication) throws RemoteException;
	
	
	/**
	 * Diffuse un message envoyé par un joueur à tous les autre joueurs
	 * @param message
	 * @throws RemoteException
	 */
	void diffuserMessage(Message message) throws RemoteException;
}
