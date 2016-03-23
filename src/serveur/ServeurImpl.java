package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.modeles.Message;
import exception.TooMuchPlayerException;
import service.Communication;

/**
 * 
 * @author jerome
 */
public class ServeurImpl extends UnicastRemoteObject implements Serveur {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Contient la liste 
	 */
	private ArrayList<Communication> communications = new ArrayList<Communication>();
	
	
	/**
	 * Nombre max de joueurs 
	 */
	private final static int NOMBRE_MAX_JOUEURS = 4;
	
	public ServeurImpl() throws RemoteException{};
	
	/**
	 * Enregistre une communication au serveur
	 * @param communication
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	@Override
	public void enregistrerCommunication(Communication joueur) throws RemoteException{
		if(communications.size() < NOMBRE_MAX_JOUEURS){
			communications.add(joueur);
		}
	}
	
	/**
	 * Diffuse un message envoyé par un joueur à tous les autre joueurs
	 * @param message
	 * @throws RemoteException
	 */
	@Override
	public void diffuserMessage(Message message) throws RemoteException {
		for(Communication communication : communications){
			communication.recevoir(message);
		}
	}

}
