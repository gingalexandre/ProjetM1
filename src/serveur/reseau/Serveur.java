package serveur.reseau;

import java.rmi.Remote;
import java.rmi.RemoteException;
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
	 * Diffuse un message envoy� par un joueur � tous les autre joueurs
	 * @param message
	 * @throws RemoteException
	 */
	void diffuserMessage(Message message) throws RemoteException;
	
	/**
	 * Envoie le plateau de jeu au joueur pass� en param�tre
	 * @param proxy
	 */
	void envoyerPlateau(JoueurServeur proxy) throws RemoteException;
	
	/**
	 * Inscription l'utilisateur dans la base de donn�es
	 * @param utilisateur - utilisateur � inscrire
	 * @return true si inscription r�ussie, false sinon
	 */
	String inscriptionBDD(String nomUtilisateur, String motDePasse, LocalDate dateNaissance) throws InterruptedException, RemoteException;
	
	/**
	 * V�rifie que l'utilisateur est dans la base de donn�es
	 * @param nomUtilisateur - nom de l'utilisateur
	 * @param motDePasse - mot de passe de l'utilisateur
	 *  @return true si connexion possible, false sinon
	 */
	boolean verificationConnexion(String nomUtilisateur, String motDePasse) throws InterruptedException, RemoteException;
}
