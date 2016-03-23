package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.modeles.Message;
import service.Communication;

/**
 * 
 * @author jerome
 */
public class ServeurImpl extends UnicastRemoteObject implements Serveur {
	
	private static final long serialVersionUID = 1L;

	
	private ArrayList<Communication> communications = new ArrayList<Communication>();
	private final static int NOMBRE_MAX_JOUEURS = 4;
	
	public ServeurImpl() throws RemoteException{};
	
	@Override
	public void diffuserMessage(Message message) throws RemoteException {
		for(Communication communication : communications){
			communication.recevoir(message);
		}
	}

	@Override
	public void enregistrerCommunication(Communication joueur) throws RemoteException{
		if(communications.size() < NOMBRE_MAX_JOUEURS){
			communications.add(joueur);
		}
	}
}
