package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import serveur.modele.Joueur;
import serveur.modele.Point;

public interface RouteInterface extends Remote{

	Joueur getOqp() throws RemoteException;

	void setOQP(Joueur j) throws RemoteException;

	Point getDepart() throws RemoteException;

	Point getArrive() throws RemoteException;

	int compareTo(RouteInterface r2) throws RemoteException;

}