package client.controller.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.controller.application.ChatController;
import client.controller.application.PlateauController;
import client.modele.Message;
import client.modele.Plateau;
import javafx.scene.layout.Pane;

public class Joueur extends UnicastRemoteObject implements service.Joueur{

	private static final long serialVersionUID = 1L;

	/**
	 * 	Controller du chat
	 */
	private ChatController chatController;
	
	/**
	 * Controller du plateau
	 */
	private PlateauController plateauController;
	
	public Joueur() throws RemoteException{
		
	}
	
	/**
	 * @param chatController
	 */
	public void setChatController(ChatController chatController){
		this.chatController = chatController;
	}
	
	/**
	 * @param plateauController
	 */
	public void setPlateauController(PlateauController plateauController){
		this.plateauController = plateauController;
	}
	
	/**
	 * Reçoit le message transmit par le serveur et l'envoie au joueur et l'envoie au controller du chat
	 * @param message
	 * @throws RemoteException
	 */
	@Override
	public void recevoirMessage(Message message) throws RemoteException {
		this.chatController.afficherMessage(message);
	}

	/**
	 * Reçoit le plateau envoyé par le serveur et l'envoie au controller du plateau
	 * @param plateau
	 * @throws RemoteException
	 */
	@Override
	public void envoyerPlateau(Plateau plateau) throws RemoteException {
		this.plateauController.setPlateau(plateau);
	}
}
