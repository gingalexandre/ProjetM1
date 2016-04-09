package serveur.reseau;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.controller.ChatController;
import client.controller.EchangeController;
import client.controller.MenuController;
import client.controller.JoueursController;
import client.controller.MainController;
import client.controller.MenuController;
import client.controller.PlateauController;
import client.view.VuePrincipale;
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
	 * Controller du menu
	 */
	private MenuController menuController;
	
	/**
	 * Controller des échanges
	 */
	private EchangeController echangeController;
	
	/**
	 * Controller du joueur actuel
	 */
	private JoueursController joueursController;

	/**
	 * Controller du plateau
	 */
	private PlateauController plateauController;
	
	/**
	 * Joueur associ� au proxy
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
	 * @param menuController
	 */
	public void setMenuController(MenuController menuController) {
		this.menuController = menuController;
	}
	
	/**
	 * @param echangeController
	 */
	public void setEchangeController(EchangeController echangeController) {
		this.echangeController = echangeController;
	}
	
	/**
	 * @param joueursController
	 */
	public void setJoueursController(JoueursController joueursController) {
		this.joueursController = joueursController;
	}

	public JoueursController getJoueursController() {
		return joueursController;
	}

	/**
	 * Re�oit le message transmit par le serveur et l'envoie au joueur et
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
	 * Re�oit le plateau envoy� par le serveur et l'envoie au controller du
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
	 * Re�oit la liste des autres joueurs envoy�s par le serveur et l'envoie au controller ad�quat
	 * @param autresJoueurs
	 * @throws RemoteException
	 */
	@Override
	public void envoyerAutresJoueurs(ArrayList<Joueur> autresJoueurs) throws RemoteException{
		this.joueursController.recevoirAutresJoueurs(autresJoueurs);
	}
	
	/**
	 * @return le joueur associ� au proxy
	 */
	public Joueur getJoueur() throws RemoteException {
		return joueur;
	}

	/**
	 * Permet d'indiquer le joueur associ� au proxy
	 * @param joueur
	 */
	public void setJoueur(Joueur joueur) throws RemoteException {
		this.joueur = joueur;
	}
}
