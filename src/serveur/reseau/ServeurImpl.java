package serveur.reseau;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;

import exception.TooMuchPlayerException;
import serveur.bdd.Utilisateur;
import serveur.modele.Message;
import serveur.modele.Plateau;

/**
 * Classe impl�mentant le serveur, qui communique avec le proxy des joueurServeurs 
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
			throw new TooMuchPlayerException("Connexion impossible. Il y a d�j� 4 joueurServeurs connect�s sur le serveur.");
		}
	}
	
	/**
	 * Diffuse un message envoy� par un joueur � tous les autre joueurServeurs
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
	 * Envoie le plateau de jeu au joueur pass� en param�tre
	 * @param proxy
	 * @throws RemoteException 
	 */
	@Override
	public void envoyerPlateau(JoueurServeur proxy) throws RemoteException {
		proxy.envoyerPlateau(this.plateau);
	}
	
	/**
	 * Inscription l'utilisateur dans la base de donn�es
	 * @param utilisateur - utilisateur � inscrire
	 * @return true si inscription r�ussie, false sinon
	 * @throws InterruptedException 
	 */
	@Override
	public String inscriptionBDD(String nomUtilisateur, String motDePasse, LocalDate dateNaissance) throws InterruptedException, RemoteException{
		Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse, dateNaissance);
		return utilisateur.inscription();
	}
	
	/**
	 * V�rifie que l'utilisateur est dans la base de donn�es
	 * @param nomUtilisateur - nom de l'utilisateur
	 * @param motDePasse - mot de passe de l'utilisateur
	 *  @return true si connexion possible, false sinon
	 */
	@Override
	public boolean verificationConnexion(String nomUtilisateur, String motDePasse) throws InterruptedException, RemoteException{
		Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse, null);
		return utilisateur.verificationConnexion();
	}
}
