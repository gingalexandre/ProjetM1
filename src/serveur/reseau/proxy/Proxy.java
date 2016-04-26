package serveur.reseau.proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

import client.controller.ChatController;
import client.controller.EchangeController;
import client.controller.JoueursController;
import client.controller.MenuController;
import client.controller.PlateauController;
import client.controller.PropositionController;
import serveur.modele.Message;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PlateauInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;

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
	 * Controller des propositions
	 */
	private PropositionController propostionController;
	
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
	
	public void setPropositionController(PropositionController propositionController) {
		this.propostionController = propositionController;
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

	@Override
	public void envoyerProposition(HashMap<String, Integer> offreDemande, String nomExpediteur) throws RemoteException {
		this.menuController.ouvrirProposition(nomExpediteur, offreDemande);
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

	public void envoyerPositionVoleur(int depart, int arrive) throws RemoteException {
		this.plateauController.deplaceVoleur(depart,arrive);
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
		this.menuController.demanderColonie(true);
	}

	@Override
	public void recevoirPriseDeRoute(RouteInterface r, JoueurInterface j) throws RemoteException {
		this.menuController.dessinerRoute(r,j);
	}

	@Override
	public void recevoirPriseDeVille(VilleInterface v, JoueurInterface joueurCourrant) throws RemoteException {
		this.menuController.dessinerVille(v, joueurCourrant);
	}

	@Override
	public void recevoirGainRessource() throws RemoteException {
		this.joueursController.majRessource();
	}

	/**
	 * Permet de supprimer du menu un joueur ayant quitter la partie
	 */
	public void suppressionJoueur(String nomJoueurASupprimer) throws RemoteException {
		this.joueursController.suppressionJoueur(nomJoueurASupprimer);
		
	}

	/**
	 * Permet de supprimer du menu un joueur ayant quitter avant le début de la partie
	 */
	public void suppressionDepartJoueur(String nomUtilisateur) throws RemoteException {
		this.joueursController.suppressionDepartJoueur(nomUtilisateur);
	}
}
