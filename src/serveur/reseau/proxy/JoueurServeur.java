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
	 * Re�oit le message transmit par le serveur et l'envoie au joueur et l'envoie au controller du chat
	 * @param message
	 * @throws RemoteException
	 */
	void recevoirMessage(Message message) throws RemoteException;
	
	/**
	 * Re�oit le plateau envoy� par le serveur et l'envoie au controller du plateau
	 * @param plateau
	 * @throws RemoteException
	 */
	void envoyerPlateau(PlateauInterface plateau) throws RemoteException;
	
	/**
	 * Re�oit la proposition envoy� par le serveur et l'envoie au controller
	 * @param offreDemande
	 * @param nomExpediteur
	 * @throws RemoteException
	 */
	void envoyerProposition(HashMap<String, Integer> offreDemande, String nomExpediteur) throws RemoteException;
	
	/**
	 * Set les boutons d'un joueur
	 * @throws RemoteException
	 */
	void setButtons(boolean boo) throws RemoteException;
	
	/**
	 * Re�oit la liste des autres joueurs envoy�s par le serveur et l'envoie au controller ad�quat
	 * @param autresJoueurs
	 * @throws RemoteException
	 */
	void envoyerAutresJoueurs(ArrayList<JoueurInterface> autresJoueurs) throws RemoteException;
	
	/**
	 * @return le joueur associe au proxy
	 */
	JoueurInterface getJoueur() throws RemoteException;
	
	/**
	 * Permet d'indiquer le joueur associe au proxy
	 * @param joueur
	 */
	void setJoueur(JoueurInterface joueur) throws RemoteException;

	void envoyerPositionVoleur(int depart, int arrive) throws RemoteException;
	void lancerTour() throws RemoteException;

	void recevoirPriseDeRoute(RouteInterface r, JoueurInterface j) throws RemoteException;

	void recevoirPriseDeVille(VilleInterface v, JoueurInterface joueurCourrant)throws RemoteException;

	void recevoirGainRessource() throws RemoteException;
	
	/**
	 * Méthode permettant de supprimer un joueur
	 * @param nomJoueurASupprimer String : nom du joueur à supprimer
	 * @throws RemoteException
	 */
		void suppressionJoueur(String nomJoueurASupprimer) throws RemoteException;
		/**
		 * Méthode permettant de supprimer un joueur avant le début de la partie
		 * @param nomJoueurASupprimer String : nom du joueur à supprimer
		 * @throws RemoteException
		 */
		void suppressionDepartJoueur(String nomUtilisateur) throws RemoteException;

		void envoyerNbCarte() throws RemoteException;

		void activerQuitterPartie() throws RemoteException;

		void envoyerVol(int ressourcesMax) throws RemoteException;

	/**
	 * Permet la mise a jour des points d'un joueur.
	 * @param joueur
	 * @throws RemoteException
	 */
	public void updatePointVictoire(JoueurInterface joueur) throws  RemoteException;

	/**
	 * Permet de récupérer toutes les ressources d'un type pour monopoliser
	 * @param ressource_visee
	 * @throws RemoteException
	 */
	public int monopole(int ressource_visee) throws  RemoteException ;

	/**
	 * Permet de désactiver/activer les boutons de construction d'un joueur
	 * @param b
	 */
	void disableBoutonConstruction(boolean b) throws RemoteException;

}
