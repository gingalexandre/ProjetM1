package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.modele.Message;
import client.modele.Plateau;
import exception.TooMuchPlayerException;
import serveur.bdd.Utilisateur;
import service.JoueurServeur;

/**
 * Classe implémentant le serveur, qui communique avec le proxy des joueurServeurs 
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
	public void enregistrerJoueur(JoueurServeur joueurServeur) throws RemoteException, TooMuchPlayerException{
		if(joueurServeurs.size() < NOMBRE_MAX_JOUEURS){
			joueurServeurs.add(joueurServeur);
			switch(joueurServeurs.size()){
				case 1:
					joueurServeur.setCouleur("rouge");
					break;
				case 2:
					joueurServeur.setCouleur("bleu");
					break;
				case 3:
					joueurServeur.setCouleur("vert");
					break;
				case 4:
					joueurServeur.setCouleur("orange");
					break;
				default: 
					break;
			}
		}
		else{
			throw new TooMuchPlayerException("Connexion impossible. Il y a déjà 4 joueurServeurs connectés sur le serveur.");
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
	 * Inscription l'utilisateur dans la base de données
	 * @param utilisateur - utilisateur à inscrire
	 * @return true si inscription réussie, false sinon
	 * @throws InterruptedException 
	 */
	@Override
	public boolean inscriptionBDD(String nomUtilisateur, String motDePasse) throws InterruptedException, RemoteException{
		Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse);
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
		Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse);
		return utilisateur.verificationConnexion();
	}
}
