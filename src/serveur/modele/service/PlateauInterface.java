package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Point;

public interface PlateauInterface extends Remote{

	/**
	 * @return la liste des jetons du plateau
	 * @throws RemoteException
	 */
	ArrayList<JetonInterface> getJetons() throws RemoteException;
	
	/**
	 * Place les jetons sur le plateau
	 * @throws RemoteException
	 */
	void setJetons() throws RemoteException;
	
	/**
	 * @return la liste des points du plateau
	 * @throws RemoteException
	 */
	ArrayList<Point> getPoints() throws RemoteException;
	
	/**
	 * Definit les points du plateau
	 * @throws RemoteException
	 */
	void setPoints() throws RemoteException;
	
	/**
	 * @return la liste des routes du plateau
	 * @throws RemoteException
	 */
	ArrayList<RouteInterface> getRoutes() throws RemoteException;

	/**
	 * Definit les routes du plateau
	 * @throws RemoteException
	 */
	void setRoutes() throws RemoteException;

	/**
	 * @return la liste des villes du plateau
	 * @throws RemoteException
	 */
	ArrayList<VilleInterface> getVilles() throws RemoteException;
	
	/**
	 * Definit les villes du plateau
	 * @throws RemoteException
	 */
	void setVilles() throws RemoteException;
	
	/**
	 * @return la liste des hexagones du plateau
	 * @throws RemoteException
	 */
	ArrayList<HexagoneInterface> getHexagones() throws RemoteException;

	/**
	 * @return les hexagones du plateau sous forme de tableau
	 * @throws RemoteException
	 */
	HexagoneInterface[] getAllHexagone() throws RemoteException;

	/**
	 * @return l'hexagone sur lequel se trouve le voleur
	 * @throws RemoteException
	 */
	HexagoneInterface getVoleur() throws RemoteException;

	/**
	 * @param le score de dés
	 * @return l'entier correspondant à la ressource de la case concernée
	 */
	int getRessourceCase(int caseConcernee) throws RemoteException;

	
	/**
	 * @param caseConsernee
	 * @return la liste des villes adjacente à la case consernée
	 */
	ArrayList<VilleInterface> getVilleAdjacenteByCase(Integer caseConsernee) throws RemoteException;
	
	/**
	 * Methode pour calculer la route la plus longue du joueur donné en parametre
	 * @param j Joueur dont on veux la route la plus longue
	 * @return La taille de la route la plus longue
	 * @throws RemoteException
	 */
	int calculerRouteLaPlusLongue(JoueurInterface j) throws RemoteException;

}