package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import serveur.modele.Point;

public interface JetonInterface extends Remote{

	int getNumeroJeton() throws RemoteException;

	Point getEmplacement() throws RemoteException;

	Double[] getPoints() throws RemoteException;

}