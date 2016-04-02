package serveur.reseau.communicationClients;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;

import serveur.bdd.Utilisateur;

/**
 * Classe qui s'occupe des échanges concernant la base de données entre les clients et le serveur
 * @author jerome
 */
public class GestionnaireBDD implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public GestionnaireBDD(){};
	
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
