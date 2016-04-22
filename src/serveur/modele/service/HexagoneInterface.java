package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import serveur.modele.Jeton;
import serveur.modele.Point;
import serveur.modele.Ressource;
import serveur.modele.Ville;

public interface HexagoneInterface extends Remote{

	/**
	 * @return le point A
	 * @throws RemoteException
	 */
	Point getA() throws RemoteException;
	
	/**
	 * @return le point B
	 * @throws RemoteException
	 */
	Point getB() throws RemoteException;

	/**
	 * @return le point C
	 * @throws RemoteException
	 */
	Point getC() throws RemoteException;

	/**
	 * @return le point D
	 * @throws RemoteException
	 */
	Point getD() throws RemoteException;
	
	/**
	 * @return le point E
	 * @throws RemoteException
	 */
	Point getE() throws RemoteException;

	/** 
	 * @return le point F
	 * @throws RemoteException
	 */
	Point getF() throws RemoteException;

	/**
	 * @return tous les points
	 * @throws RemoteException
	 */
	Double[] getPoints() throws RemoteException;

	/**
	 * @return l'index de l'hexagone
	 * @throws RemoteException
	 */
	int getIndexHexagone() throws RemoteException;

	/**
	 * @return le type de l'hexagone
	 * @throws RemoteException
	 */
	int getType() throws RemoteException;

	/**
	 * @return la ressource associe à l'hexagone
	 * @throws RemoteException
	 */
	Ressource getRessource() throws RemoteException;

	/**
	 * @return le numero de l'hexagone
	 * @throws RemoteException
	 */
	int getNumero() throws RemoteException;

	/**
	 * @return les villes adjacentres de l'hexagone sous forme de tableau
	 * @throws RemoteException
	 */
	VilleInterface[] getVilleAdj() throws RemoteException;

	/**
	 * @return le centre de l'hexagone
	 * @throws RemoteException
	 */
	Point getCentre() throws RemoteException;

	/**
	 * @return le jeton de l'hexagone
	 * @throws RemoteException
	 */
	Jeton getJeton() throws RemoteException;

	/**
	 * @return true si le voleur est sur l'hexagone, false sinon
	 * @throws RemoteException
	 */
	boolean getVOLEUR() throws RemoteException;

	/**
	 * @param VOLEUR - true si le voleur est sur l'hexagone, false sinon 
	 * @throws RemoteException
	 */
	void setVOLEUR(boolean VOLEUR) throws RemoteException;

}