package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import serveur.modele.Ressource;

public interface PartieInterface extends Remote {

	/**
	 * @return la liste de tous les joueur
	 */
	ArrayList<JoueurInterface> getTousLesJoueurs() throws RemoteException;
	
	/**
	 * Permet de récupérer un joueur participant à la partie
	 * @return le joueur 1
	 * @throws RemoteException
	 */
	JoueurInterface getJoueur1() throws RemoteException;

	/**
	 * Défini d'un joueur
	 * @param joueur1
	 * @throws RemoteException
	 */
	void setJoueur1(JoueurInterface joueur1) throws RemoteException;

	/**
	 * Permet de récupérer un joueur participant à la partie
	 * @return le joueur 2
	 * @throws RemoteException
	 */
	JoueurInterface getJoueur2() throws RemoteException;

	/**
	 * Défini d'un joueur
	 * @param joueur2
	 * @throws RemoteException
	 */
	void setJoueur2(JoueurInterface joueur2) throws RemoteException;

	/**
	 * Permet de récupérer un joueur participant à la partie
	 * @return le joueur 3
	 * @throws RemoteException
	 */
	JoueurInterface getJoueur3() throws RemoteException;

	/**
	 * Défini d'un joueur
	 * @param joueur3
	 * @throws RemoteException
	 */
	void setJoueur3(JoueurInterface joueur3) throws RemoteException;

	/**
	 * Permet de récupérer un joueur participant à la partie
	 * @return le joueur 4
	 * @throws RemoteException
	 */
	JoueurInterface getJoueur4() throws RemoteException;

	/**
	 * Défini d'un joueur
	 * @param joueur4
	 * @throws RemoteException
	 */
	void setJoueur4(JoueurInterface joueur4) throws RemoteException;

	/**
	 * Permet de savoir si la partie a été chargée ou non
	 * @return true si la partie a été chargée, false sinon
	 */
	boolean isChargee() throws RemoteException;
	
	/**
	 * Permet d'indiquer si la partie a été chargée ou non
	 * @param chargee 
	 */
	void setChargee(boolean charge) throws RemoteException;
	
	/**
	 * Getter du boolean pour savoir si la partie à commencé
	 * @return booléen
	 */
	public boolean isPartieCommence() throws RemoteException;

	/**
	 * Setter du boolean pour savoir si la partie à commencé
	 * @param partieCommence - nouveau partieCommence
	 */
	public void setPartieCommence(boolean partieCommence) throws RemoteException;
	
	/**
	 * Permet de récupérer les ressources de la partie
	 * @return les ressources
	 * @throws RemoteException
	 */
	Ressource getRessources() throws RemoteException;

	/**
	 * Définition des ressources
	 * @param ressources
	 * @throws RemoteException
	 */
	void setRessources(Ressource ressources) throws RemoteException;
	
	/**
	 * @return l'id du joueur
	 * @throws RemoteException
	 */
	int getId() throws RemoteException;

	/**
	 * Permet d'indiquer l'id du joueur
	 * @param id - nouvel id
	 * @throws RemoteException
	 */
	void setId(int id) throws RemoteException;
	
	/**
	 * Permet d'obtenir le tour de la partie
	 * @return
	 * @throws RemoteException
	 */
	int getTour() throws RemoteException;
	
	/**
	 * Permet d'indiquer l'attribut tour de la partie
	 * @param tour - nouveau tour
	 * @throws RemoteException
	 */
	void setTour(int tour) throws RemoteException;
	
	/**
	 * Récupération de la liste triée des joueurs
	 * @return liste de clients
	 * @throws RemoteException
	 */
	ArrayList<JoueurInterface> getOrdreJeu() throws RemoteException;

	/**
	 * Définition d'un nouvel ordre de jeu
	 * @param ordreJeu
	 * @throws RemoteException
	 */
	void setOrdreJeu(ArrayList<JoueurInterface> ordreJeu) throws RemoteException;

	/**
	 * Permet de récupérer le nombre de joueurs de la partie
	 * @return le nombre de joueurs
	 * @throws RemoteException
	 */
	int getNombreJoueurs() throws RemoteException;

	/**
	 * Récupération du client dont c'est le tour
	 * @return JoueurInterface
	 * @throws RemoteException
	 */
	JoueurInterface getJoueurTour() throws RemoteException;

	/**
	 * Renvoie le jeu qui joue en ce moment
	 * @return
	 * @throws RemoteException
	 */
	JoueurInterface getJoueurActuel() throws RemoteException;
	
	/**
	 * Récupération d'un joueur en fonction de sa couleur
	 * @param couleur
	 * @return le joueur grâce à sa couleur
	 * @throws RemoteException
	 */
	JoueurInterface getJoueurByCouleur(String couleur) throws RemoteException;

	/**
	 * Récupération d'un joueur en fonction de son nom
	 * @param nom
	 * @return le joueur grâce à son nom
	 * @throws RemoteException
	 */
	JoueurInterface getJoueurByName(String nom) throws RemoteException;
	
	/**
	 * Récupération du joueur le plus âgé
	 * @return le joueur le plus vieux
	 * @throws RemoteException
	 */
	JoueurInterface getJoueurLePlusVieux() throws RemoteException;

	/**
	 * @return le plateau de la partie
	 * @throws RemoteException
	 */
	PlateauInterface getPlateau() throws RemoteException;

	/**
	 * Permet de récupérer le nombre de tour qu'il y a eu dans la partie
	 * @return le nombre de tour qu'il y a eu dans la partie
	 */
	int getCompteurTourGlobal() throws RemoteException;
	
	/**
	 * Permet de voler la moitier des ressources d'un joueur qui a plus de 7 cartes
	 * @throws RemoteException
	 */
	HashMap<String, Integer> getNomJoueursVoles() throws RemoteException;

	/**
	 * Incrémente de 1 le nombre de tour ou le remet à 0 si un tour complet à été effectué
	 * @throws RemoteException
	 */
	void incrementeTour() throws RemoteException;
	
	/**
	 * Définition de l'ordre de jeu en fonction de l'âge des participants
	 * @throws RemoteException
	 */
	void arrangerOrdreTour() throws RemoteException;

	/**
	 * Méthode permettant de supprimer un Joueur
	 * @param joueurSupprime - joueur à supprimer
	 * @throws RemoteException
	 */
	void supprimerJoueur(JoueurInterface joueurSupprime) throws RemoteException;

	/**
	 * Méthode permettant d'affecter null à un joueur
	 * @param joueurSupprime - joueur à supprimer
	 * @throws RemoteException
	 */
	public void affecterNullJoueur(JoueurInterface joueurSupprime) throws RemoteException;

	/**
	 * Renvoi la première carte du deck de la partie concernée.
	 * @return Carte
	 * @throws RemoteException
     */
	public CarteInterface piocheDeck() throws RemoteException;

	PaquetInterface getDeck() throws RemoteException;
}
