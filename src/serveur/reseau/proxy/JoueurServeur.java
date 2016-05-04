package serveur.reseau.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import serveur.modele.Message;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PlateauInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;

/**
 * Interface du proxy entre client et serveur
 * @author jerome
 */
public interface JoueurServeur extends Remote {
	
	/**
	 * @return le joueur associe au proxy
	 */
	JoueurInterface getJoueur() throws RemoteException;
	
	/**
	 * Permet d'indiquer le joueur associé au proxy
	 * @param joueur
	 */
	void setJoueur(JoueurInterface joueur) throws RemoteException;
	
	/**
	 * Reçoit le message transmit par le serveur et l'envoie au joueur et l'envoie au controller du chat
	 * @param message
	 * @throws RemoteException
	 */
	void recevoirMessage(Message message) throws RemoteException;
	
	/**
	 * Reçoit le plateau envoyé par le serveur et l'envoie au controller du plateau
	 * @param plateau
	 * @throws RemoteException
	 */
	void envoyerPlateau(PlateauInterface plateau) throws RemoteException;
	
	/**
	 * Reçoit la proposition envoyé par le serveur et l'envoie au controller
	 * @param offreDemande
	 * @param nomExpediteur
	 * @throws RemoteException
	 */
	void envoyerProposition(HashMap<String, Integer> offreDemande, String nomExpediteur) throws RemoteException;
	
	/**
	 * Permet d'envoyer la position du voleur
	 * @param depart - position de départ
	 * @param arrive - position d'arrivée
	 * @throws RemoteException
	 */
	void envoyerPositionVoleur(int depart, int arrive) throws RemoteException;
	
	/**
	 * Permet d'envoyer un vol
	 * @param ressourcesMax
	 * @throws RemoteException
	 */
	void envoyerVol(int ressourcesMax) throws RemoteException;
	
	/**
	 * Permet d'envoyer des cartes
	 * @throws RemoteException
	 */
	void envoyerNbCarte() throws RemoteException;
	
	/**
	 * Reçoit la liste des autres joueurs envoyés par le serveur et l'envoie au controller adéquat
	 * @param autresJoueurs
	 * @throws RemoteException
	 */
	void envoyerAutresJoueurs(ArrayList<JoueurInterface> autresJoueurs) throws RemoteException;
	
	/**
	 * Set les boutons d'un joueur
	 * @throws RemoteException
	 */
	void setButtons(boolean boo) throws RemoteException;

	
	/**
	 * Permet de lancer le tour du joueur
	 * @throws RemoteException
	 */
	void lancerTour() throws RemoteException;

	/**
	 * Permet de recevoir la prise d'une route 
	 * @param r - route en question
	 * @param j - joueur en question
	 * @throws RemoteException
	 */
	void recevoirPriseDeRoute(RouteInterface r, JoueurInterface j) throws RemoteException;

	/**
	 * Permet de recevoir la prise d'une ville 
	 * @param v - ville en question
	 * @param joueurCourrant - joueur en question
	 * @throws RemoteException
	 */
	void recevoirPriseDeVille(VilleInterface v, JoueurInterface joueurCourrant)throws RemoteException;

	/**
	 * Permet de recevoir le gain d'une ressource
	 * @throws RemoteException
	 */
	void recevoirGainRessource() throws RemoteException;
	
	/**
	 * Méthode permettant de supprimer un joueur
	 * @param nomJoueurASupprimer String : nom du joueur à supprimer
	 * @throws RemoteException
	 */
	void suppressionJoueur(String nomJoueurASupprimer) throws RemoteException;
		
	/**
	 * Méthode permettant de supprimer un joueur avant le début de la partie
	 * @param nomUtilisateur String : nom du joueur à supprimer
	 * @throws RemoteException
	 */
	void suppressionDepartJoueur(String nomUtilisateur) throws RemoteException;

	/**
	 * Active le bouton pour quitter la partie
	 * @throws RemoteException
	 */
	void activerQuitterPartie() throws RemoteException;

	/**
	 * Permet la mise a jour des points d'un joueur.
	 * @throws RemoteException
	 */
	public void updatePointVictoire() throws  RemoteException;

	/**
	 * Permet de récupérer toutes les ressources d'un type pour monopoliser
	 * @param ressource_visee
	 * @throws RemoteException
	 */
	public int monopole(int ressource_visee) throws  RemoteException ;

	/**
	 * Permet la mise a jour de la carte armée puissate.
	 * @throws RemoteException
	 */
	public void updateArmeePuissante() throws  RemoteException ;

	/**
	 * Permet de désactiver/activer les boutons de construction d'un joueur
	 * @param b
	 */
	void disableBoutonConstruction(boolean b) throws RemoteException;

	/**
	 * Permet la mise a jour de la carte route longue.
	 * @throws RemoteException
	 */
	public void updateRouteLongue() throws  RemoteException;

	/**
	 * Désactive/active les boutons Echange des autres joueurs
	 * @param b
	 * @throws RemoteException
	 */
	void disableBoutonEchange(boolean b) throws RemoteException;

	/**
	 * Désactive/active le bouton de lancer de dés
	 * @param b
	 * @throws RemoteException
	 */
	void setButtonLancerDes(boolean b) throws RemoteException;
}
