package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import serveur.modele.Joueur;
import serveur.modele.Point;

public interface RouteInterface extends Remote{

	/**
	 * @return le joueur qui occupe la route
	 * @throws RemoteException
	 */
	Joueur getOqp() throws RemoteException;

	/**
	 * Définit le joueur qui occupe la route
	 * @param j - joueur qui va occuper la route
	 * @throws RemoteException
	 */
	void setOQP(Joueur j) throws RemoteException;

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
}