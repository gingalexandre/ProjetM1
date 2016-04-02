package serveur.reseau;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;

import exception.TooMuchPlayerException;
import serveur.modele.Message;

/**
 * Classe principale pour les communications entre client et serveur
 * @author jerome
 */
public interface Serveur extends Remote{
	
	
	/**
	 * Enregistre un joueur au serveur
	 * @param communication
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	void enregistrerJoueur(JoueurServeur joueurServeur) throws RemoteException, TooMuchPlayerException;
	
	
	/**
	 * Diffuse un message envoyï¿½ par un joueur ï¿½ tous les autre joueurs
	 * @param message
	 * @throws RemoteException
	 */
	void diffuserMessage(Message message) throws RemoteException;
	
	/**
	 * Envoie le plateau de jeu au joueur passï¿½ en paramï¿½tre
	 * @param proxy
	 */
	void envoyerPlateau(JoueurServeur proxy) throws RemoteException;
	
	/**
	 * Inscription l'utilisateur dans la base de donnï¿½es
	 * @param utilisateur - utilisateur ï¿½ inscrire
	 * @return true si inscription rï¿½ussie, false sinon
	 */
	String inscriptionBDD(String nomUtilisateur, String motDePasse, LocalDate dateNaissance) throws InterruptedException, RemoteException;
	
	/**
	 * Vérifie que l'utilisateur est dans la base de données
	 * @param nomUtilisateur - nom de l'utilisateur
	 * @param motDePasse - mot de passe de l'utilisateur
	 *  @return true si connexion possible, false sinon
	 */
	boolean verificationConnexion(String nomUtilisateur, String motDePasse) throws InterruptedException, RemoteException;
	
	Date getDateNaissanceUtilisateur(String nomUtilisateur) throws InterruptedException, RemoteException;
}
