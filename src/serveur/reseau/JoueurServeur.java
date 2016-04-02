package serveur.reseau;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
	 * @return le joueur associ� au proxy
	 */
	Joueur getJoueur() throws RemoteException;
	
	/**
	 * Permet d'indiquer le joueur associ� au proxy
	 * @param joueur
	 */
	void setJoueur(Joueur joueur) throws RemoteException;
}
