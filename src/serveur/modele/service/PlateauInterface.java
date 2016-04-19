package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Hexagone;
import serveur.modele.Jeton;
import serveur.modele.Point;
import serveur.modele.Route;
import serveur.modele.Ville;

public interface PlateauInterface extends Remote{

	void setJetons() throws RemoteException;

	ArrayList<Jeton> getJetons() throws RemoteException;

	ArrayList<Hexagone> getHexagones() throws RemoteException;

	ArrayList<Ville> getVilles() throws RemoteException;

	ArrayList<Route> getRoutes() throws RemoteException;

	void setPoints() throws RemoteException;

	void setVilles() throws RemoteException;

	void setRoutes() throws RemoteException;

	void ajoutListeRoute(Route r) throws RemoteException;

	Hexagone[] getAllHexagone() throws RemoteException;

	ArrayList<Point> getPoints() throws RemoteException;

	Hexagone getVoleur() throws RemoteException;

}