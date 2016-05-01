package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import serveur.modele.Joueur;
import serveur.modele.Point;
import serveur.modele.Route;

public interface RouteInterface extends Remote{

	/**
	 * @return le joueur qui occupe la route
	 * @throws RemoteException
	 */
	JoueurInterface getOqp() throws RemoteException;

	/**
	 * Définit le joueur qui occupe la route
	 * @param j - joueur qui va occuper la route
	 * @throws RemoteException
	 */
	void setOQP(JoueurInterface j) throws RemoteException;

	/**
	 * @return le point de depart de la route
	 * @throws RemoteException
	 */
	Point getDepart() throws RemoteException;

	/**
	 * @return le point d'arrive de la route
	 * @throws RemoteException
	 */
	Point getArrive() throws RemoteException;

	/**
	 * Compare deux routes
	 * @param r2 - deuxième route à comparer
	 * @return -1, 0 ou 1
	 * @throws RemoteException
	 */
	int compareTo(RouteInterface r2) throws RemoteException;

	boolean estConstructible(HashMap<Point, VilleInterface> villes, JoueurInterface joueurCourrant, HashSet<Point> pointsDeRoutes, VilleInterface villeIgnored) throws RemoteException;

	int isExtremite(HashMap<Point,VilleInterface> villes) throws RemoteException;

	ArrayList<RouteInterface> getSuccesseurs(Point propagation, JoueurInterface j, HashMap<Point, VilleInterface> villes,Set<RouteInterface> visite) throws RemoteException;

}