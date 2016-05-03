package serveur.reseau.proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import client.controller.*;
import serveur.modele.Message;
import serveur.modele.service.*;

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
	 * Controller des règles
	 */
	private ReglesController reglesController;
	
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
	 * Controller des propositions
	 */
	private CarteController carteController;
	
	/**
	 * Controller des vols
	 */
	private VolController volController;
	
	/**
	 * Joueur associe au proxy
	 */
	private JoueurInterface joueur;

	public Proxy() throws RemoteException {}

	/**
	 * @return le joueur associe au proxy
	 */
	public JoueurInterface getJoueur() throws RemoteException {
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
	
	/**
	 * Set les boutons de sauvegarde
	 * @param boo
	 * @throws RemoteException
	 */
	public void setButtonsSauvegarde(boolean boo) throws RemoteException{
		this.reglesController.setButtonsSauvegarde(boo);
	}
	
	/**
	 * @param volController
	 * @throws RemoteException
	 */
	public void setVolController(VolController volController) throws RemoteException{
		this.volController = volController;
		
	}
	
	/**
	 * @param chatController
	 * @throws RemoteException
	 */
	public void setChatController(ChatController chatController)throws RemoteException {
		this.chatController = chatController;
	}

	/**
	 * @param plateauController
	 * @throws RemoteException
	 */
	public void setPlateauController(PlateauController plateauController) throws RemoteException {
		this.plateauController = plateauController;
	}
	
	/**
	 * @param menuController
	 * @throws RemoteException
	 */
	public void setMenuController(MenuController menuController) throws RemoteException {
		this.menuController = menuController;
	}
	
	/**
	 * @param echangeController
	 * @throws RemoteException
	 */
	public void setEchangeController(EchangeController echangeController) throws RemoteException {
		this.echangeController = echangeController;
	}
	
	/**
	 * @param joueursController
	 * @throws RemoteException
	 */
	public void setJoueursController(JoueursController joueursController) throws RemoteException {
		this.joueursController = joueursController;
	}

	/**
	 * @param propositionController
	 * @throws RemoteException
	 */
	public void setPropositionController(PropositionController propositionController)throws RemoteException  {
		this.propostionController = propositionController;
	}
	
	/**
	 * @param reglesController
	 * @throws RemoteException
	 */
	public void setReglesController(ReglesController reglesController)throws RemoteException  {
		this.reglesController = reglesController;
	}

	/**
	 * @param carteController
	 * @throws RemoteException
	 */
	public void setCarteController(CarteController carteController) throws RemoteException {
		this.carteController = carteController;
	}
	
	/**
	 * @return le joueur controller du joueur
	 * @throws RemoteException
	 */
	public JoueursController getJoueursController() throws RemoteException {
		return joueursController;
	}

	/**
	 * Reçoit le plateau envoye par le serveur et l'envoie au controller du plateau
	 * @param plateau - plateau reçu
	 * @throws RemoteException
	 */
	@Override
	public void envoyerPlateau(PlateauInterface plateau) throws RemoteException {
		this.plateauController.setPlateau(plateau);
	}

	/**
	 * Reçoit la proposition envoyé par le serveur et l'envoie au controller
	 * @param offreDemande
	 * @param nomExpediteur
	 * @throws RemoteException
	 */
	@Override
	public void envoyerProposition(HashMap<String, Integer> offreDemande, String nomExpediteur) throws RemoteException {
		this.menuController.ouvrirProposition(nomExpediteur, offreDemande);
	}
	
	/**
	 * Permet d'envoyer la position du voleur
	 * @param depart - position de départ
	 * @param arrive - position d'arrivée
	 * @throws RemoteException
	 */
	public void envoyerPositionVoleur(int depart, int arrive) throws RemoteException {
		this.plateauController.deplaceVoleur(depart,arrive);
	}
	
	/**
	 * Permet d'envoyer un vol
	 * @param ressourcesMax
	 * @throws RemoteException
	 */
	@Override
	public void envoyerVol(int ressourcesMax) throws RemoteException {
		this.menuController.ouvrirVol(ressourcesMax);
	}
	
	/**
	 * Permet d'envoyer des cartes
	 * @throws RemoteException
	 */
	@Override
	public void envoyerNbCarte() throws RemoteException {
		this.joueursController.majNbCarte();
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
	 * Permet de recevoir la prise d'une route 
	 * @param r - route en question
	 * @param j - joueur en question
	 * @throws RemoteException
	 */
	@Override
	public void recevoirPriseDeRoute(RouteInterface r, JoueurInterface j) throws RemoteException {
		this.menuController.dessinerRoute(r,j);
	}

	/**
	 * Permet de recevoir la prise d'une ville 
	 * @param v - ville en question
	 * @param joueurCourrant - joueur en question
	 * @throws RemoteException
	 */
	@Override
	public void recevoirPriseDeVille(VilleInterface v, JoueurInterface joueurCourrant) throws RemoteException {
		this.menuController.dessinerVille(v, joueurCourrant);
	}

	/**
	 * Permet de recevoir le gain d'une ressource
	 * @throws RemoteException
	 */
	@Override
	public void recevoirGainRessource() throws RemoteException {
		this.joueursController.majRessource();
	}
	
	/**
	 * Recoit le message transmit par le serveur et l'envoie au joueur et l'envoie au controller du chat
	 * @param message - message reçu
	 * @throws RemoteException
	 */
	@Override
	public void recevoirMessage(Message message) throws RemoteException {
		this.chatController.afficherMessage(message);
	}

	/**
	 * Permet de lancer le tour du joueur
	 * @throws RemoteException
	 */
	@Override
	public void lancerTour() throws RemoteException{
		if (menuController.isInitTurn()){
			this.menuController.demanderColonie(true);
		}
		else {
			this.menuController.demanderRoute(false, null);
		}
	}

	/**
	 * Active le bouton pour quitter la partie
	 * @throws RemoteException
	 */
	@Override
	public void activerQuitterPartie() throws RemoteException{
		this.menuController.activerQuitterPartie();
	}

	/**
	 * Permet la mise a jour des points d'un joueur.
	 * @throws RemoteException
	 */
	public void updatePointVictoire() throws  RemoteException {
		this.joueursController.majPointVictoire();
	}
/**
	 * Permet de récupérer toutes les ressources d'un type pour monopoliser
	 * @param ressource_visee
	 * @throws RemoteException
	 */
	public int monopole(int ressource_visee) throws  RemoteException {
		int value = joueur.getStockRessource().get(ressource_visee);
		joueur.supprimerRessource(ressource_visee,value);
		this.joueursController.majRessource();
		return value;
	}

	/**
	 * Permet la mise a jour de la carte armée puissate.
	 * @throws RemoteException
	 */
	public void updateArmeePuissante() throws  RemoteException {
		this.joueursController.updateArmeePuissante();
	}

	/**
	 * Permet la mise a jour de la carte armée puissate.
	 * @throws RemoteException
	 */
	public void updateRouteLongue() throws  RemoteException {
		this.joueursController.updateRouteLongue();
	}



	/**
	 * Permet de désactiver/activer les boutons de construction d'un joueur
	 * @param boo
	 */
	public void disableBoutonConstruction(boolean boo) throws RemoteException{
		this.menuController.disableBoutonConstruction(boo);
	}
	
	/**
	 * Méthode permettant de supprimer un joueur
	 * @param nomJoueurASupprimer String : nom du joueur à supprimer
	 * @throws RemoteException
	 */
	public void suppressionJoueur(String nomJoueurASupprimer) throws RemoteException {
		this.joueursController.suppressionJoueur(nomJoueurASupprimer);
		
	}

	/**
	 * Méthode permettant de supprimer un joueur avant le début de la partie
	 * @param nomUtilisateur String : nom du joueur à supprimer
	 * @throws RemoteException
	 */
	public void suppressionDepartJoueur(String nomUtilisateur) throws RemoteException {
		this.joueursController.suppressionDepartJoueur(nomUtilisateur);
	}
	
	public void init() throws RemoteException {
		if (this.menuController.isInitTurn()) this.menuController.demanderColonie(true);
	}

}
