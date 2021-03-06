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
import serveur.modele.service.PartieInterface;
import serveur.reseau.communicationClients.gestionnaire.GestionnaireBDD;
import serveur.reseau.communicationClients.gestionnaire.GestionnairePartie;
import serveur.reseau.communicationClients.gestionnaire.GestionnaireUI;
import serveur.reseau.communicationClients.service.GestionnaireBDDInterface;
import serveur.reseau.communicationClients.service.GestionnairePartieInterface;
import serveur.reseau.communicationClients.service.GestionnaireUIInterface;
import serveur.reseau.proxy.JoueurServeur;

/**
 * Classe implémentant le serveur, qui communique avec les proxy
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
	 * Stocke la liste des couleurs
	 */
	private ArrayList<String> listeCouleurs = new ArrayList<String>();
	
	/**
	 * Indique si la partie est chargée ou non
	 */
	private boolean chargee = false;
	
	/**
	 * Compte le nombre de connexions au serveur
	 */
	private int compteurNbConnexion;
	
	/**
	 * Constructeur de la classe ServeurImpl
	 * @throws RemoteException
	 */
	public ServeurImpl() throws RemoteException{
		compteurNbConnexion = 0;
		this.listeCouleurs.add("bleu");
		this.listeCouleurs.add("vert");
		this.listeCouleurs.add("orange");
		this.listeCouleurs.add("rouge");
		gestionnaireBDD = new GestionnaireBDD();
	}
	
	/**
	 * Enregistre un joueur sur le serveur
	 * @param nouveauJoueurServeur - joueur a ajouter
	 * @param nom - nom du joueur
	 * @param date - date de naissance du joueur
	 * @return true si le joueur a été enregistré, false sinon
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	@Override
	public boolean enregistrerJoueur(JoueurServeur nouveauJoueurServeur, String nom, Date date) throws RemoteException, TooMuchPlayerException{
		if(joueurServeurs.size() < nombre_max_joueurs){
			if(this.gestionnairePartie.getPartie().isChargee()){
				return enregistrerJoueurPartieChargee(nouveauJoueurServeur, nom);	
			}
			else{
				return enregistrerJoueurPartieNonChargee(nouveauJoueurServeur, nom, date);
			}
		}
		else{
			throw new TooMuchPlayerException("Connexion impossible. Il y a deja "+nombre_max_joueurs+" joueurs connectes sur le serveur.");
		}
	}

	/**
	 * Enregistre le PREMIER joueur sur le serveur
	 * @param nouveauJoueurServeur - joueur a ajouter
	 * @param nom - nom du joueur
	 * @param date - date de naissance du joueur
	 * @param nbJoueurs - nombre de joueurs max de la partie
	 * @param difficulte - difficulté de la partie
	 * @return true si le joueur a été enregistré, false sinon
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	@Override
	public boolean enregistrerJoueur(JoueurServeur nouveauJoueurServeur, String nomJoueur, Date date, int nbJoueurs, String difficulte) throws RemoteException, TooMuchPlayerException {
		if(!chargee){
			this.nombre_max_joueurs = nbJoueurs;
			this.gestionnaireUI = new GestionnaireUI(difficulte);
			this.gestionnairePartie = new GestionnairePartie(this.gestionnaireUI.getPlateau());
		}
		return enregistrerJoueur(nouveauJoueurServeur, nomJoueur, date);
	}
	
	/**
	 * Enregistre un joueur pour une partie chargée
	 * @param nouveauJoueurServeur 
	 * @param nom - nom du joueur 
	 * @throws RemoteException
	 */
	private boolean enregistrerJoueurPartieChargee(JoueurServeur nouveauJoueurServeur, String nom) throws RemoteException {
		ArrayList<JoueurInterface> listeJoueurs = this.gestionnairePartie.getPartie().getTousLesJoueurs();
		JoueurInterface j = null;
		boolean trouve = false;
		for(JoueurInterface joueur : listeJoueurs){
			if(nom.equals(joueur.getNomUtilisateur())){
				trouve = true;
				j = joueur;
			}
		}
		if(trouve){
			joueurServeurs.add(nouveauJoueurServeur);
			nouveauJoueurServeur.setJoueur(j);
			envoyerJoueurAuGestionnaire(nouveauJoueurServeur);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Enregistre un joueur pour une partie non chargée
	 * @param nouveauJoueurServeur
	 * @param nom - nom du joueur
	 * @param date
	 * @throws RemoteException
	 */
	private boolean enregistrerJoueurPartieNonChargee(JoueurServeur nouveauJoueurServeur, String nom, Date date) throws RemoteException {
		JoueurInterface joueur = new Joueur(nom, date);
		joueurServeurs.add(nouveauJoueurServeur);
		String couleur = this.listeCouleurs.get(0);
		// Dans le cas où la couleur est rouge, seul le 4ème Joueur peut l'avoir
		if(couleur.equals("rouge")){
			if(this.listeCouleurs.size() == 4){
				joueur.setCouleur(couleur);
				this.listeCouleurs.remove(0);
			}
			else{
				joueur.setCouleur(this.listeCouleurs.get(0));
				this.listeCouleurs.remove(0);
				this.listeCouleurs.add("rouge");
			}
		}
		else{
			joueur.setCouleur(this.listeCouleurs.get(0));
			this.listeCouleurs.remove(0);
		}
		nouveauJoueurServeur.setJoueur(joueur);
		envoyerJoueurAuGestionnaire(nouveauJoueurServeur);
		return true;
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
				this.gestionnairePartie.supprimerJoueur(js);
				this.listeCouleurs.add(js.getJoueur().getCouleur());
				joueurServeurs.remove(js);
				this.getGestionnairePartie().supprimerJoueur(js);
				this.getGestionnaireUI().supprimerJoueur(js);
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
		this.listeCouleurs.add(joueur.getJoueur().getCouleur());
		this.joueurServeurs.remove(joueur);
		this.getGestionnairePartie().supprimerJoueur(joueur);
		this.getGestionnaireUI().supprimerJoueur(joueur);
	}

	/**
	 * Charge la partie 
	 * @param idPartie - id de la partie chargée
	 * @throws InterruptedException 
	 * @throws RemoteException 
	 */
	@Override
	public void chargerPartie(Integer idPartie) throws RemoteException, InterruptedException {
		chargee = true;
		this.gestionnaireBDD.chargerPartie(idPartie);
		PartieInterface partieChargee = this.gestionnairePartie.recupererPartieChargee();
		this.gestionnaireUI.setPlateau(partieChargee.getPlateau());

	}

	/** 
	 * Crée les gestionnaires
	 * @throws RemoteException 
	 */
	@Override
	public void creerGestionnaireUIetPartie() throws RemoteException {
		this.gestionnaireUI = new GestionnaireUI("Débutant");
		this.gestionnairePartie = new GestionnairePartie(this.gestionnaireUI.getPlateau());
	}

	/**
	 * Incrémente le nombre de joueurs qui se sont connectés au serveur
	 * @return
	 */
	@Override
	public int incrementerNbConnexions() throws RemoteException {
		this.compteurNbConnexion++;
		return this.compteurNbConnexion;
	}

	/**
	 * Décremente le nombre de joueurs qui se sont connectés au serveur
	 * @return
	 */
	@Override
	public int decrementeNbConnexions() throws RemoteException {
		this.compteurNbConnexion--;
		return compteurNbConnexion;
	}
}
