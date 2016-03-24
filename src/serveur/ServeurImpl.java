package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.modele.Message;
import client.modele.Plateau;
import exception.TooMuchPlayerException;
import service.Joueur;

/**
 * Classe implémentant le serveur, qui communique avec le proxy des joueurs 
 * @author jerome
 */
public class ServeurImpl extends UnicastRemoteObject implements Serveur {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Contient la liste 
	 */
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	
	/**
	 * Nombre max de joueurs 
	 */
	private final static int NOMBRE_MAX_JOUEURS = 4;
	
	/**
	 * Plateau de jeu 
	 */
	private Plateau plateau;
	
	public ServeurImpl() throws RemoteException{
		this.plateau = Plateau.getInstance();
	}
	
	/**
	 * Enregistre une communication au serveur
	 * @param communication
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	@Override
	public void enregistrerJoueur(Joueur joueur) throws RemoteException, TooMuchPlayerException{
		if(joueurs.size() < NOMBRE_MAX_JOUEURS){
			joueurs.add(joueur);
		}
		else{
			throw new TooMuchPlayerException("Connexion impossible. Il y a déjà 4 joueurs connectés sur le serveur. ");
		}
	}
	
	/**
	 * Diffuse un message envoyé par un joueur à tous les autre joueurs
	 * @param message
	 * @throws RemoteException
	 */
	@Override
	public void diffuserMessage(Message message) throws RemoteException {
		for(Joueur joueur : joueurs){
			joueur.recevoirMessage(message);
		}
	}

	/**
	 * Envoie le plateau de jeu au joueur passé en paramètre
	 * @param proxy
	 * @throws RemoteException 
	 */
	@Override
	public void envoyerPlateau(Joueur proxy) throws RemoteException {
		proxy.envoyerPlateau(this.plateau);
	}
}
