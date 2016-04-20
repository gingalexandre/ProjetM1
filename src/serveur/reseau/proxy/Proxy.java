package serveur.reseau.proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.controller.ChatController;
import client.controller.EchangeController;
import client.controller.MenuController;
import client.controller.JoueursController;
import client.controller.PlateauController;
import serveur.modele.Message;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PlateauInterface;

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
	 * Joueur associe au proxy
	 */
	private JoueurInterface joueur;

	public Proxy() throws RemoteException {

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
	public void setEchangeController(EchangeController echangeController){
		this.echangeController = echangeController;
	}
	
	/**
	 * @param joueursController
	 */
	public void setJoueursController(JoueursController joueursController){
		this.joueursController = joueursController;
	}

	public JoueursController getJoueursController(){
		return joueursController;
	}

	/**
	 * Recoit le message transmit par le serveur et l'envoie au joueur et
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
	 * Re�oit le plateau envoye par le serveur et l'envoie au controller du
	 * plateau
	 * 
	 * @param plateau
	 * @throws RemoteException
	 */
	@Override
	public void envoyerPlateau(PlateauInterface plateau) throws RemoteException {
		this.plateauController.setPlateau(plateau);
	}

	/**
	 * Recoit la liste des autres joueurs envoyes par le serveur et l'envoie au controller adequat
	 * @param autresJoueurs
	 * @throws RemoteException
	 */
	@Override
	public void envoyerAutresJoueurs(ArrayList<JoueurInterface> autresJoueurs) throws RemoteException{
		this.joueursController.recevoirAutresJoueurs(autresJoueurs);
	}
	
	/**
	 * @return le joueur associe au proxy
	 */
	public JoueurInterface getJoueur(){
		return joueur;
	}

	/**
	 * Permet d'indiquer le joueur associe au proxy
	 * @param joueur
	 */
	public void setJoueur(JoueurInterface joueur) throws RemoteException {
		this.joueur = joueur;
	}

	/**
	 * Set les boutons d'un joueur
	 * @throws RemoteException
	 */
	@Override
	public void setButtons(boolean boo) throws RemoteException {
		this.menuController.setButtons(boo);
	}

	@Override
	public void lancerTour() throws RemoteException{
		this.menuController.demanderRoute() ;
	}
}
