package serveur.reseau.communicationClients.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

import serveur.modele.Message;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PlateauInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;
import serveur.reseau.proxy.JoueurServeur;

/**
 * Interface qui s'occupe des échanges de l'UI sur le serveur 
 * @author jerome
 */
public interface GestionnaireUIInterface extends Remote{
	
	/**
	 * @return le plateau de jeu
	 */
	PlateauInterface getPlateau() throws RemoteException;
	
	/**
	 * Permet d'indiquer un nouveau plateau
	 * @param plateau - nouveau plateau
	 */
	void setPlateau(PlateauInterface plateau) throws RemoteException;
	
	/**
	 * Enregistre un nouveau joueur dans la liste des joueurs
	 * @param nouveauJoueurServeur - joueur a enregistrer
	 */
	void enregistrerJoueur(JoueurServeur nouveauJoueurServeur) throws RemoteException;
	
	/**
	 * Envoie le plateau de jeu au joueur pass� en parametre
	 * @param proxy
	 * @throws RemoteException 
	 */
	void envoyerPlateau(JoueurServeur proxy) throws RemoteException;
	
	/**
	 * Permet d'envoyer le plateau à tous les joueurs
	 * @throws RemoteException
	 */
	void envoyerPlateauATousLesJoueurs() throws RemoteException;
	
	/**
	 * Diffuse un message envoyé par un joueur a tous les autre joueurServeurs
	 * @param message
	 * @throws RemoteException
	 */
	void diffuserMessage(Message message) throws RemoteException;

	/**
	 * Permet de diffuser les mouvements du voleur
	 * @param depart - case de départ
	 * @param arrive - case d'arrivée
	 * @throws RemoteException
     */
	void diffuserVoleur(int depart, int arrive) throws RemoteException;
	
	/**
	 * Diffuse la prise d'une route 
	 * @param r - route en question
	 * @param j - joueur en question
	 * @throws RemoteException
	 */
	void diffuserPriseDeRoute(RouteInterface r, JoueurInterface j) throws RemoteException;

	/**
	 * Diffuse la prise d'une ville 
	 * @param v - ville en question
	 * @param joueurCourrant - joueur en question
	 * @throws RemoteException
	 */
	void diffuserPriseDeVille(VilleInterface v, JoueurInterface joueurCourrant) throws RemoteException;

	/**
	 * Permet de diffuser le gain d'une ressource
	 * @throws RemoteException
	 */
	void diffuserGainRessource() throws RemoteException;
	
	/**
	 * Permet de diffuser le fait qu'un joueur est parti
	 * @param joueurASupprimer JoueurInterface : joueur ayant quitté le jeu
	 * @throws RemoteException
	 */
	void diffuserDepartJoueur(JoueurInterface joueurASupprimer) throws RemoteException;

	/**
	 * Diffuse une proposition
	 * @param j - joueur à qui on diffuse
	 * @param offreDemande - HashMap contenant les ressources
	 * @param nomExpediteur - nom de l'expediteur
	 * @throws RemoteException
	 */
	void diffuserProposition(JoueurServeur j, HashMap<String, Integer> offreDemande, String nomExpediteur)throws RemoteException;

	/**
	 * Permet de diffuser le gain d'une carte ressource
	 * @throws RemoteException
	 */
	void diffuserGainCarteRessource() throws RemoteException;

	/**
	 * Permet de supprimer un joueur
	 * @param joueur - joueur à supprimer
	 * @throws RemoteException
	 */
	void supprimerJoueur(JoueurServeur joueur) throws RemoteException;

	/**
	 * Permet d'envoyer un vol
	 * @param ressourcesMax
	 * @param j
	 * @throws RemoteException
	 */
	void envoyerVol(int ressourcesMax, JoueurServeur j) throws RemoteException;

	/**
	 * Permet la mise a jour des points d'un joueur.
	 * @param joueur
	 * @throws RemoteException
	 */
	public void updatePointVictoire(JoueurInterface joueur) throws  RemoteException ;

	/**
	 * Permet de monopoliser une ressource
	 * @param ressource_visee
	 * @throws RemoteException
	 */
	public int monopole(int ressource_visee) throws  RemoteException ;
}
