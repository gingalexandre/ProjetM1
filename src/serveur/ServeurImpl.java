package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.modeles.Message;
import exception.TooMuchPlayerException;
import service.Joueur;

/**
 * 
 * @author jerome
 */
public class ServeurImpl extends UnicastRemoteObject implements Serveur {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Contient la liste 
	 */
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	
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
	public void enregistrerCommunication(Joueur joueur) throws RemoteException{
		if(joueurs.size() < NOMBRE_MAX_JOUEURS){
			joueurs.add(joueur);
		}
	}
	
	/**
	 * Diffuse un message envoyé par un joueur à tous les autre joueurs
	 * @param message
	 * @throws RemoteException
	 */
	@Override
	public void diffuserMessage(Message message) throws RemoteException {
		for(Joueur joueur : joueurs){
			joueur.recevoir(message);
		}
	}

}
