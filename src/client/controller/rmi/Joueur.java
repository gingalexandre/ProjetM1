package client.controller.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.controller.application.ChatApplicationController;
import client.modeles.Message;

public class Joueur extends UnicastRemoteObject implements service.Joueur{

	private static final long serialVersionUID = 1L;

	private ChatApplicationController chat;
	
	public Joueur() throws RemoteException{
		
	}
	
	public void setChatApplicationController(ChatApplicationController chatApplicationController){
		this.chat = chatApplicationController;
	}
	
	@Override
	public void recevoir(Message message) throws RemoteException {
		chat.afficherMessage(message);
	}

}
