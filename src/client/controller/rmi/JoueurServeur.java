package client.controller.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.controller.application.ChatController;
import client.controller.application.PlateauController;
import client.modele.Message;
import client.modele.Plateau;

public class JoueurServeur extends UnicastRemoteObject implements service.JoueurServeur{

	private static final long serialVersionUID = 1L;

	/**
	 * Couleur de jeu du joueur
	 */
	private String couleur;
	
	/**
	 * 	Controller du chat
	 */
	private ChatController chatController;
	
	/**
	 * Controller du plateau
	 */
	private PlateauController plateauController;
	
	public JoueurServeur() throws RemoteException{
		
	}
	
	/**
	 * Recupère la couleur de jeu du joueur
	 * @param color
	 */
	public String getCouleur(){
		return this.couleur;
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
	
	/**
	 * Indique la couleur de jeu du joueur
	 * @param color
	 */
	@Override
	public void setCouleur(String couleur) throws RemoteException{
		this.couleur = couleur;
	}
}
