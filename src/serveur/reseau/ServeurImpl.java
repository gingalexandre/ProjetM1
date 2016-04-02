package serveur.reseau;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import exception.TooMuchPlayerException;
import serveur.bdd.Utilisateur;
import serveur.modele.Joueur;
import serveur.modele.Message;
import serveur.modele.Partie;
import serveur.modele.Plateau;

/**
 * Classe implémentant le serveur, qui communique avec les proxy
 * @author jerome
 */
public class ServeurImpl extends UnicastRemoteObject implements Serveur {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Contient la liste 
	 */
	private ArrayList<JoueurServeur> joueurServeurs = new ArrayList<JoueurServeur>();
	
	/**
	 * Nombre max de joueurServeurs 
	 */
	private final static int NOMBRE_MAX_JOUEURS = 4;
	
	/**
	 * Plateau de jeu 
	 */
	private Plateau plateau;
	
	/**
	 * Partie sur laquelle les joueurs jouent
	 */
	private Partie partie;
	
	public ServeurImpl() throws RemoteException{
		this.plateau = Plateau.getInstance();
		this.partie = new Partie(this.plateau);
	}
	
	/**
	 * Enregistre un joueur sur le serveur
	 * @param nouveauJoueurServeur - joueur à ajouter
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	@Override
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur) throws RemoteException, TooMuchPlayerException{
		if(joueurServeurs.size() < NOMBRE_MAX_JOUEURS){
			Joueur joueur = new Joueur();
			joueurServeurs.add(nouveauJoueurServeur);
			switch(joueurServeurs.size()){
				case 1:
					joueur.setCouleur("rouge");
					this.partie.setJoueur1(joueur);
					break;
				case 2:
					joueur.setCouleur("bleu");
					this.partie.setJoueur2(joueur);
					break;
				case 3:
					joueur.setCouleur("vert");
					this.partie.setJoueur3(joueur);
					break;
				case 4:
					joueur.setCouleur("orange");
					this.partie.setJoueur4(joueur);
					break;
				default: 
					break;
			}
			nouveauJoueurServeur.setJoueur(joueur);
		}
		else{
			throw new TooMuchPlayerException("Connexion impossible. Il y a déjà 4 joueurs connectés sur le serveur.");
		}
	}
	
	/**
	 * Diffuse un message envoyé par un joueur à tous les autre joueurServeurs
	 * @param message
	 * @throws RemoteException
	 */
	@Override
	public void diffuserMessage(Message message) throws RemoteException {
		for(JoueurServeur joueurServeur : joueurServeurs){
			joueurServeur.recevoirMessage(message);
		}
	}

	/**
	 * Envoie le plateau de jeu au joueur passé en paramètre
	 * @param proxy
	 * @throws RemoteException 
	 */
	@Override
	public void envoyerPlateau(JoueurServeur proxy) throws RemoteException {
		proxy.envoyerPlateau(this.plateau);
	}
	
	/**
	 * Inscription l'utilisateur dans la base de donnï¿½es
	 * @param utilisateur - utilisateur à inscrire
	 * @return true si inscription réussie, false sinon
	 * @throws InterruptedException 
	 */
	@Override
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
	@Override
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
