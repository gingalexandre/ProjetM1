package serveur.reseau.communicationClients.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Interface qui s'occupe des échanges de la BDD sur le serveur
 * 
 * @author jerome
 */
public interface GestionnaireBDDInterface extends Remote {

	/**
	 * Inscription l'utilisateur dans la base de données
	 * 
	 * @param nomUtilisateur Nom de l'utilisateur à inscrire
	 * @param motDePasse Mot de passe de l'utilisateur à inscrire
	 * @param dateNaissance Date de naissance de l'utilisateur à inscrire
	 * @return Si inscription réussie
	 * @throws InterruptedException
	 * @throws RemoteException
	 */
	String inscriptionBDD(String nomUtilisateur, String motDePasse, LocalDate dateNaissance)
			throws InterruptedException, RemoteException;

	/**
	 * Permet de récupérer les statistiques à partir du nom du Joueur
	 * @param nomUtilisateur Nom de l'utilisateur dont on souhaite avoir les statistiques
	 * @return Un tableau avec en position 0, le nombre de partie jouée et en 1 le nombre de partie gagnée
	 */
	Integer[] getStatistiques(String nomUtilisateur) throws RemoteException;

	/**
	 * Méthode permettant de mettre à jour les stats d'un Joueur à la fin de la
	 * partie
	 * 
	 * @param victoire 0 si perdu, 1 si victoire
	 * @param nomUtilisateur pseudo du Joueur dont on met les statistiques à jour
	 * @throws RemoteException Exception dû a RMI
	 */
	void getUpdateStatistiques(int victoire, String nomUtilisateur) throws RemoteException;

	/**
	 * Vérifie que l'utilisateur est dans la base de données
	 * 
	 * @param nomUtilisateur nom de l'utilisateur
	 * @param motDePasse mot de passe de l'utilisateur
	 * @return si le couple nom d'utilisateur et mot de passe est correct
	 * @throws RemoteException Exception dû a RMI
	 */
	boolean verificationConnexion(String nomUtilisateur, String motDePasse)
			throws InterruptedException, RemoteException;

	/**
	 * Permet de récupérer la date de naissance de l'utilisateur à partir de son
	 * pseudo
	 * 
	 * @throws InterruptedException
	 * @throws RemoteException Exception dû a RMI
	 */
	Date getDateNaissanceUtilisateur(String nomUtilisateur) throws InterruptedException, RemoteException;

	/**
	 * Permet de récupérer les partie à partir du nom du Joueur
	 * 
	 * @param nom String : nom du Joueur
	 * @return ArrayList<Integer> : liste des id de la partie
	 */
	ArrayList<Integer> recupererPartieByName(String nom) throws InterruptedException, RemoteException;

	/**
	 * Permet de charger la Partie à partir de son id
	 * @param id Id de la partie à charger
	 * @throws InterruptedException
	 * @throws RemoteException
	 */
	void chargerPartie(int id) throws InterruptedException, RemoteException;
}
