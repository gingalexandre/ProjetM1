package serveur.reseau.communicationClients.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import exception.TooMuchPlayerException;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PartieInterface;
import serveur.reseau.proxy.JoueurServeur;

/**
 * Interface qui s'occupe des échanges de la partie sur le serveur
 * 
 * @author jerome
 */
public interface GestionnairePartieInterface extends Remote {

	/**
	 * Enregistre un nouveau JoueurInterface dans la liste des joueurs
	 * 
	 * @param nouveauJoueurServeur
	 *            - JoueurInterface a enregistrer
	 */
	void enregistrerJoueur(JoueurServeur nouveauJoueurServeur) throws RemoteException;

	/**
	 * Envoie la liste des autres joueurs
	 * 
	 * @throws RemoteException
	 */
	void envoyerAutresJoueurs() throws RemoteException;

	/**
	 * Methode qui renvoie la liste des joueurs mis a part le JoueurInterface
	 * indique en parametre
	 * 
	 * @param joueurQuiAppelle
	 * @return la liste des autres joueurs connect�s sur le serveur
	 * @throws RemoteException
	 */
	ArrayList<JoueurInterface> recupererAutresJoueurs(JoueurInterface joueurQuiAppelle) throws RemoteException;

	/**
	 * Méthode qui renvoie la liste de tous les joueurs
	 * 
	 * @return la liste des joueurs connectés sur le serveur
	 * @throws RemoteException
	 */
	ArrayList<JoueurServeur> recupererTousLesJoueurs() throws RemoteException;

	/**
	 * Ajoute le JoueurInterface passe en parametre a la partie
	 * 
	 * @param nouveauJoueurInterface
	 *            - JoueurInterface a ajouter a la partie
	 * @throws TooMuchPlayerException
	 */
	void ajouterJoueurPartie(JoueurInterface nouveauJoueur) throws RemoteException;

	/**
	 * Réactive les boutons d'un joueur
	 * 
	 * @throws RemoteException
	 */
	void enableBoutons(JoueurInterface j) throws RemoteException;

	/**
	 * Met un JoueurInterface a pret
	 * 
	 * @param joueur
	 * @throws RemoteException
	 */
	void joueurPret(JoueurInterface joueur) throws RemoteException;

	/**
	 * Verifie si tous les joueurs connectes sur le serveur sont prets a jouer.
	 * Si ils le sont tous, la partie commence.
	 * 
	 * @throws RemoteException
	 */
	void verifierJoueursPrets() throws RemoteException;

	/**
	 * Finit le tour d'un joueur et renvoie le joueur suivant
	 * 
	 * @throws RemoteException
	 */
	void finirTour() throws RemoteException;

	/**
	 * Renvoi la partie
	 * 
	 * @return La partie
	 * @throws RemoteException
	 */
	PartieInterface getPartie() throws RemoteException;

	void lancerProchainTour(JoueurInterface joueurTour) throws RemoteException;

	/**
	 * Permet de savoir si on est encore dans la première phase de la partie
	 * 
	 * @return true si on est encore dans la première phase de la partie, false
	 *         sinon
	 */
	boolean isPremierePhasePartie() throws RemoteException;

	/**
	 * Permet de supprimer un Joueur dans la partie
	 * 
	 * @param joueurSupprime
	 *            JoueurInterface : joueur à supprimer
	 * @throws RemoteException
	 */
	void supprimerJoueur(JoueurInterface joueurSupprime) throws RemoteException;


}
