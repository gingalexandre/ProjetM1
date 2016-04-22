package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Point;
import serveur.modele.Ville;

public interface PlateauInterface extends Remote{

	/**
	 * Place les jetons sur le plateau
	 * @throws RemoteException
	 */
	void setJetons() throws RemoteException;

	/**
	 * @return la liste des jetons du plateau
	 * @throws RemoteException
	 */
	ArrayList<JetonInterface> getJetons() throws RemoteException;

	/**
	 * @return la liste des hexagones du plateau
	 * @throws RemoteException
	 */
	ArrayList<HexagoneInterface> getHexagones() throws RemoteException;

	/**
	 * @return la liste des villes du plateau
	 * @throws RemoteException
	 */
	ArrayList<VilleInterface> getVilles() throws RemoteException;

	/**
	 * @return la liste des routes du plateau
	 * @throws RemoteException
	 */
	ArrayList<RouteInterface> getRoutes() throws RemoteException;

	/**
	 * Definit les points du plateau
	 * @throws RemoteException
	 */
	void setPoints() throws RemoteException;

	/**
	 * Definit les villes du plateau
	 * @throws RemoteException
	 */
	void setVilles() throws RemoteException;

	/**
	 * Definit les routes du plateau
	 * @throws RemoteException
	 */
	void setRoutes() throws RemoteException;

	/**
	 * Ajoute une route à la liste des routes
	 * @param r - route a ajouter
	 * @throws RemoteException
	 */
	void ajoutListeRoute(RouteInterface r) throws RemoteException;

	/**
	 * @return les hexagones du plateau sous forme de tableau
	 * @throws RemoteException
	 */
	HexagoneInterface[] getAllHexagone() throws RemoteException;

	/**
	 * @return la liste des points du plateau
	 * @throws RemoteException
	 */
	ArrayList<Point> getPoints() throws RemoteException;

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
	ArrayList<Ville> getVilleAdjacenteByCase(Integer caseConsernee) throws RemoteException;
	
	

}