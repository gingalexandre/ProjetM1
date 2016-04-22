package serveur.reseau.communicationClients.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
	 * Diffuse un message envoyé par un joueur a tous les autre joueurServeurs
	 * @param message
	 * @throws RemoteException
	 */
	void diffuserMessage(Message message) throws RemoteException;

	/**
	 * Permet de diffuser les mouvements du voleur
	 * @param depart case de départ
	 * @param arrive case d'arrivée
	 * @throws RemoteException
     */
	void diffuserVoleur(int depart, int arrive) throws RemoteException;

	
	void diffuserPriseDeRoute(RouteInterface r, JoueurInterface j) throws RemoteException;

	void diffuserPriseDeVille(VilleInterface v, JoueurInterface joueurCourrant) throws RemoteException;

}
