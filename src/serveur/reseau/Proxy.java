package serveur.reseau;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.controller.ChatController;
import client.controller.PlateauController;
import serveur.modele.Joueur;
import serveur.modele.Message;
import serveur.modele.Plateau;

public class Proxy extends UnicastRemoteObject implements JoueurServeur {

	private static final long serialVersionUID = 1L;

	/**
	 * Controller du chat
	 */
	private ChatController chatController;

	/**
	 * Controller du plateau
	 */
	private PlateauController plateauController;

	/**
	 * Joueur associé au proxy
	 */
	private Joueur joueur;

	public Proxy() throws RemoteException {

	}

	/**
	 * @param chatController
	 */
	public void setChatController(ChatController chatController) {
		this.chatController = chatController;
	}

	/**
	 * @param plateauController
	 */
	public void setPlateauController(PlateauController plateauController) {
		this.plateauController = plateauController;
	}

	/**
	 * Reçoit le message transmit par le serveur et l'envoie au joueur et
	 * l'envoie au controller du chat
	 * 
	 * @param message
	 * @throws RemoteException
	 */
	@Override
	public void recevoirMessage(Message message) throws RemoteException {
		this.chatController.afficherMessage(message);
	}

	/**
	 * Reçoit le plateau envoyé par le serveur et l'envoie au controller du
	 * plateau
	 * 
	 * @param plateau
	 * @throws RemoteException
	 */
	@Override
	public void envoyerPlateau(Plateau plateau) throws RemoteException {
		this.plateauController.setPlateau(plateau);
	}

	/**
	 * @return le joueur associé au proxy
	 */
	public Joueur getJoueur() throws RemoteException {
		return joueur;
	}

	/**
	 * Permet d'indiquer le joueur associé au proxy
	 * @param joueur
	 */
	public void setJoueur(Joueur joueur) throws RemoteException {
		this.joueur = joueur;
	}
}
