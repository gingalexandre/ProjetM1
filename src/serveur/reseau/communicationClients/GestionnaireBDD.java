package serveur.reseau.communicationClients;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.time.LocalDate;

import serveur.bdd.Statistiques;
import serveur.bdd.Utilisateur;
import serveur.reseau.communicationClients.service.GestionnaireBDDInterface;

/**
 * Classe qui s'occupe des échanges concernant la base de données entre les clients et le serveur
 * @author jerome
 */
public class GestionnaireBDD extends UnicastRemoteObject implements GestionnaireBDDInterface {
	
	private static final long serialVersionUID = 1L;

	public GestionnaireBDD() throws RemoteException{} ;
	
	/**
	 * Inscription l'utilisateur dans la base de données
	 * @param utilisateur - utilisateur à inscrire
	 * @return true si inscription réussie, false sinon
	 * @throws InterruptedException 
	 */
	public String inscriptionBDD(String nomUtilisateur, String motDePasse, LocalDate dateNaissance) throws InterruptedException, RemoteException{
		Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse, dateNaissance);
		return utilisateur.inscription();
	}
	
	/**
	 * Permet de récupérer les statistiques à partir du nom du Joueur
	 * @param nomUtilisateur
	 * @return un tableau de int [nombrePartieJouee, nombrePartieGagnee]
	 */
	public Integer[] getStatistiques(String nomUtilisateur) throws RemoteException{
		return Statistiques.getStatistiques(nomUtilisateur);
	}

	/**
	 * Méthode permettant de mettre à jour les stats d'un Joueur à la fin de la partie
	 * @param victoire : int 0 si perdu, 1 si victoire
	 * @param nomUtilisateur : String : pseudo du Joueur
	 */
	public void getUpdateStatistiques(int victoire, String nomUtilisateur){
		Statistiques.addStatistique(victoire, nomUtilisateur);
	}
	
	/**
	 * Vérifie que l'utilisateur est dans la base de données
	 * @param nomUtilisateur - nom de l'utilisateur
	 * @param motDePasse - mot de passe de l'utilisateur
	 *  @return true si connexion possible, false sinon
	 */
	public boolean verificationConnexion(String nomUtilisateur, String motDePasse) throws InterruptedException, RemoteException{
		Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse, null);
		return utilisateur.verificationConnexion();
	}
	
	/**
	 * Permet de récupérer la date de naissance de l'utilisateur à partir de son
	 * pseudo
	 * @throws InterruptedException 
	 */
	public Date getDateNaissanceUtilisateur(String nomUtilisateur) throws InterruptedException, RemoteException {
		return Utilisateur.getDateNaissance(nomUtilisateur);
	}
}
