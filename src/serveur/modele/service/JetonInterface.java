package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import serveur.modele.Point;

public interface JetonInterface extends Remote{

	/**
	 * @return le numero du jeton
	 * @throws RemoteException
	 */
	int getNumeroJeton() throws RemoteException;

	/**
	 * @return l'emplacement du jeton
	 * @throws RemoteException
	 */
	Point getEmplacement() throws RemoteException;

	/**
	 * @return les points du jeton sous forme de tableau
	 * @throws RemoteException
	 */
	Double[] getPoints() throws RemoteException;

}