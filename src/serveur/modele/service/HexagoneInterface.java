package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import serveur.modele.Jeton;
import serveur.modele.Point;
import serveur.modele.Ressource;
import serveur.modele.Ville;

public interface HexagoneInterface extends Remote{

	Point getB() throws RemoteException;

	Point getC() throws RemoteException;

	Point getE() throws RemoteException;

	Point getF() throws RemoteException;

	Point getA() throws RemoteException;

	Point getD() throws RemoteException;

	Double[] getPoints() throws RemoteException;

	int getIndexHexagone() throws RemoteException;

	int getType() throws RemoteException;

	Ressource getRessource() throws RemoteException;

	int getNumero() throws RemoteException;

	Ville[] getVilleAdj() throws RemoteException;

	Point getCentre() throws RemoteException;

	Jeton getJeton() throws RemoteException;

	boolean isVOLEUR() throws RemoteException;

	void setVOLEUR(boolean VOLEUR) throws RemoteException;

}