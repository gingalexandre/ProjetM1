package serveur.reseau.communicationClients.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import serveur.reseau.proxy.JoueurServeur;

/**
 * Interface qui s'occupe des échanges de la BDD sur le serveur
 * 
 * @author jerome
 */
public interface GestionnaireBDDInterface extends Remote {

	/**
	 * Inscription l'utilisateur dans la base de données
	 * 
	 * @param utilisateur
	 *            - utilisateur à inscrire
	 * @return true si inscription réussie, false sinon
	 * @throws InterruptedException
	 */
	String inscriptionBDD(String nomUtilisateur, String motDePasse, LocalDate dateNaissance)
			throws InterruptedException, RemoteException;

	/**
	 * Permet de récupérer les statistiques à partir du nom du Joueur
	 * 
	 * @param nomUtilisateur
	 * @return un tableau de int [nombrePartieJouee, nombrePartieGagnee]
	 */
	Integer[] getStatistiques(String nomUtilisateur) throws RemoteException;

	/**
	 * Méthode permettant de mettre à jour les stats d'un Joueur à la fin de la
	 * partie
	 * 
	 * @param victoire
	 *            : int 0 si perdu, 1 si victoire
	 * @param nomUtilisateur
	 *            : String : pseudo du Joueur
	 */
	void getUpdateStatistiques(int victoire, String nomUtilisateur) throws RemoteException;

	/**
	 * Vérifie que l'utilisateur est dans la base de données
	 * 
	 * @param nomUtilisateur
	 *            - nom de l'utilisateur
	 * @param motDePasse
	 *            - mot de passe de l'utilisateur
	 * @return true si connexion possible, false sinon
	 */
	boolean verificationConnexion(String nomUtilisateur, String motDePasse)
			throws InterruptedException, RemoteException;

	/**
	 * Permet de récupérer la date de naissance de l'utilisateur à partir de son
	 * pseudo
	 * 
	 * @throws InterruptedException
	 */
	Date getDateNaissanceUtilisateur(String nomUtilisateur) throws InterruptedException, RemoteException;

	/**
	 * Permet de récupérer les partie à partir du nom du Joueur
	 * 
	 * @param nom
	 *            : String : nom du Joueur
	 * @return ArrayList<Integer> : liste des id de la partie
	 */
	ArrayList<Integer> recupererPartieByName(String nom) throws InterruptedException, RemoteException;

	/**
	 * Permet de charger la Partie à partir de son id
	 * @param id int : id de la partie à charger
	 * @throws InterruptedException
	 * @throws RemoteException
	 */
	void chargerPartie(int id) throws InterruptedException, RemoteException;
}
