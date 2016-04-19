package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Point;
import serveur.modele.Ville;

public interface PlateauInterface extends Remote{

	void setJetons() throws RemoteException;

	ArrayList<JetonInterface> getJetons() throws RemoteException;

	ArrayList<HexagoneInterface> getHexagones() throws RemoteException;

	ArrayList<VilleInterface> getVilles() throws RemoteException;

	ArrayList<RouteInterface> getRoutes() throws RemoteException;

	void setPoints() throws RemoteException;

	void setVilles() throws RemoteException;

	void setRoutes() throws RemoteException;

	void ajoutListeRoute(RouteInterface r) throws RemoteException;

	HexagoneInterface[] getAllHexagone() throws RemoteException;

	ArrayList<Point> getPoints() throws RemoteException;

	HexagoneInterface getVoleur() throws RemoteException;

}