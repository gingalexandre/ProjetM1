package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Ressource;

public interface PartieInterface extends Remote{
	
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
	 * incrémente de 1 le nombre de tour ou le remet à 0 si un tour complet à été effectué
	 * @throws RemoteException
	 */
	void incrementeTour() throws RemoteException;
	
	/**
	 * Récupération d'un joueur en fonction de sa couleur
	 * @param couleur
	 * @return
	 * @throws RemoteException
	 */
	JoueurInterface getJoueurByCouleur(String couleur) throws RemoteException;
	
	/**
	 * Définition de l'ordre de jeu en fonction de l'âge des participants
	 * @throws RemoteException
	 */
	void arrangerOrdreTour() throws RemoteException;
	
	/**
	 * Récupération du joueur le plus âgé
	 * @return
	 * @throws RemoteException
	 */
	JoueurInterface getJoueurLePlusVieux() throws RemoteException;
	
	int getId() throws RemoteException;
	
	void setId(int id) throws RemoteException;
}
