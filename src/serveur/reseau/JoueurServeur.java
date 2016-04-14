package serveur.reseau;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Joueur;
import serveur.modele.Message;
import serveur.modele.Plateau;

/**
 * Interface du proxy entre client et serveur
 * @author jerome
 */
public interface JoueurServeur extends Remote{
	
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
	void envoyerPlateau(Plateau plateau) throws RemoteException;
	
	/**
	 * Réactive les boutons d'un joueur
	 * @throws RemoteException
	 */
	void enableBoutons() throws RemoteException;
	
	/**
	 * Re�oit la liste des autres joueurs envoy�s par le serveur et l'envoie au controller ad�quat
	 * @param autresJoueurs
	 * @throws RemoteException
	 */
	void envoyerAutresJoueurs(ArrayList<Joueur> autresJoueurs) throws RemoteException;
	
	/**
	 * @return le joueur associ� au proxy
	 */
	Joueur getJoueur() throws RemoteException;
	
	/**
	 * Permet d'indiquer le joueur associ� au proxy
	 * @param joueur
	 */
	void setJoueur(Joueur joueur) throws RemoteException;
}
