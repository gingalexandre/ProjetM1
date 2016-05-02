package serveur.reseau.serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;

import exception.TooMuchPlayerException;
import serveur.bdd.modeleBDD.Sauvegarde;
import serveur.modele.Joueur;
import serveur.modele.Message;
import serveur.modele.service.JoueurInterface;
import serveur.reseau.communicationClients.gestionnaire.GestionnaireBDD;
import serveur.reseau.communicationClients.gestionnaire.GestionnairePartie;
import serveur.reseau.communicationClients.gestionnaire.GestionnaireUI;
import serveur.reseau.communicationClients.service.GestionnaireBDDInterface;
import serveur.reseau.communicationClients.service.GestionnairePartieInterface;
import serveur.reseau.communicationClients.service.GestionnaireUIInterface;
import serveur.reseau.proxy.JoueurServeur;

/**
 * Classe impl�mentant le serveur, qui communique avec les proxy
 * @author jerome
 */
public class ServeurImpl extends UnicastRemoteObject implements Serveur {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Contient la liste des joueurs connect�s au serveur
	 */
	private ArrayList<JoueurServeur> joueurServeurs = new ArrayList<JoueurServeur>();
	
	/**
	 * Nombre max de joueurServeurs 
	 */
	private int nombre_max_joueurs;
	
	/**
	 * Gestionnaire de la base de donnees
	 */
	private GestionnaireBDDInterface gestionnaireBDD;
	
	/**
	 * Gestionnaire de la partie
	 */
	private GestionnairePartieInterface gestionnairePartie;
	
	/**
	 * Gestionnaire de l'interface
	 */
	private GestionnaireUIInterface gestionnaireUI;
	
	/**
	 * Constructeur de la classe ServeurImpl
	 * @throws RemoteException
	 */
	public ServeurImpl(int nombre_max_joueurs) throws RemoteException{
		this.nombre_max_joueurs = nombre_max_joueurs;
		gestionnaireBDD = new GestionnaireBDD();
		gestionnaireUI = new GestionnaireUI();
		gestionnairePartie = new GestionnairePartie(this.gestionnaireUI.getPlateau());
	}
	
	/**
	 * Enregistre un joueur sur le serveur
	 * @param nouveauJoueurServeur - joueur a ajouter
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	@Override
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur, String nom, Date date) throws RemoteException, TooMuchPlayerException{
		if(joueurServeurs.size() < nombre_max_joueurs){
			JoueurInterface joueur = new Joueur(nom, date);
			joueurServeurs.add(nouveauJoueurServeur);
			switch(joueurServeurs.size()){
				case 1:
					joueur.setCouleur("rouge");
					break;
				case 2:
					joueur.setCouleur("bleu");
					break;
				case 3:
					joueur.setCouleur("vert");
					break;
				case 4:
					joueur.setCouleur("orange");
					break;
				default: 
					break;
			}
			nouveauJoueurServeur.setJoueur(joueur);
			envoyerJoueurAuGestionnaire(nouveauJoueurServeur);
		}
		else{
			throw new TooMuchPlayerException("Connexion impossible. Il y a deja "+nombre_max_joueurs+" joueurs connectes sur le serveur.");
		}
	}
	
	/**
	 * Permet d'obtenir la liste des proxys
	 * @return la liste des proxy connectés sur le serveur
	 */
	@Override
	public ArrayList<JoueurServeur> getListeJoueurs() throws RemoteException{
		return this.joueurServeurs;
	}
	
	/**
	 * Permet d'obtenir un proxy grâce au nom du joueur
	 * @return le proxy grâce à son nom
	 * @throws RemoteException
	 */
	@Override
	public JoueurServeur getJoueur(String nomJoueur) throws RemoteException {
		for (JoueurServeur j : joueurServeurs){
			if(j.getJoueur().getNomUtilisateur().equals(nomJoueur)){
				return j;
			}
		}
		return null;
	}
	
	/**
	 * Permet d'obtenir le gestionnaire de base de donnees
	 * @return le gestionnaire de base de donnees
	 * @throws RemoteException
	 */
	@Override
	public GestionnaireBDDInterface getGestionnaireBDD() throws RemoteException {
		return gestionnaireBDD;
	}

	/**
	 * Permet d'obtenir le gestionnaire de partie
	 * @return le gestionnaire de partie
	 * @throws RemoteException
	 */
	@Override
	public GestionnairePartieInterface getGestionnairePartie() throws RemoteException {
		return this.gestionnairePartie;
	}

	/**
	 * Permet d'obtenir le gestionnaire de l'interface
	 * @return le gestionnaire de base de l'interface
	 * @throws RemoteException
	 */
	@Override
	public GestionnaireUIInterface getGestionnaireUI() throws RemoteException {
		return gestionnaireUI;
	}

	/**
	 * Permet d'ajouter un joueur a la liste de joueurs des gestionnaires
	 * @param nouveauJoueurServeur - joueur a envoyer
	 * @throws RemoteException 
	 */
	private void envoyerJoueurAuGestionnaire(JoueurServeur nouveauJoueurServeur) throws RemoteException{
		gestionnairePartie.enregistrerJoueur(nouveauJoueurServeur);
		gestionnairePartie.ajouterJoueurPartie(nouveauJoueurServeur.getJoueur());
		gestionnaireUI.enregistrerJoueur(nouveauJoueurServeur);
	}
	
	/**
	 * Appel le GestionnaireUI pour envoyer un message indiquant le nombre de joueurs connectés aux joueurs 
	 * @throws RemoteException 
	 */
	@Override
	public void envoyerNombreJoueursConnectes() throws RemoteException {
		String contenu = "Nombre de joueurs connectés : "+this.joueurServeurs.size()+"/"+this.nombre_max_joueurs;
		Message message = new Message(contenu);
		gestionnaireUI.diffuserMessage(message);
	}

	/**
	 * Permet de sauvegarder la partie
	 */
	@Override
	public void enregistrerPartie() throws RemoteException {
		new Sauvegarde();
	}
	
	public void supprimerJoueur(JoueurInterface joueurASupprimer) throws RemoteException{
		for(JoueurServeur js : joueurServeurs){
			if(js.getJoueur().getNomUtilisateur().equals(joueurASupprimer.getNomUtilisateur())){
				joueurServeurs.remove(js);
				break;
			}
		}
	}

	 /**
     * Permet à un joueur de quitter la partie
     * @param nomJoueur
     * @throws RemoteException
     */
	@Override
	public synchronized void quitterPartie(JoueurInterface joueur) throws RemoteException{
		JoueurServeur aSupprimer = null;
		for (JoueurServeur j : joueurServeurs){
			if(j.getJoueur().getNomUtilisateur().equals(joueur.getNomUtilisateur())){
				aSupprimer = j;
			}
		}
		if(aSupprimer != null){
			supprimerJoueurQuitterPartie(aSupprimer);
			this.getGestionnaireUI().diffuserMessage(new Message(joueur.getNomUtilisateur()+ " s'est déconnecté."));
		}
		if(joueurServeurs.size() == 0){
			System.exit(0);
		}
	}
	
	/**
	 * Supprime un joueur qui a quitté la partie
	 * @param joueur
	 * @throws RemoteException
	 */
	private void supprimerJoueurQuitterPartie(JoueurServeur joueur) throws RemoteException{
		this.joueurServeurs.remove(joueur);
		this.getGestionnairePartie().supprimerJoueur(joueur);
		this.getGestionnaireUI().supprimerJoueur(joueur);
	}
}
