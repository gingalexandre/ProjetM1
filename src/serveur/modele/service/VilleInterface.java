package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Joueur;
import serveur.modele.Point;
import serveur.modele.Route;
import serveur.modele.Ville;

public interface VilleInterface extends Remote{

	Point getEmplacement() throws RemoteException;

	boolean estLibre(JoueurInterface proprio, ArrayList<VilleInterface> villes) throws RemoteException;

	void colonieToVille(JoueurInterface j) throws RemoteException;

	void setOQP(JoueurInterface j) throws RemoteException;

	void setVillesAdj(int v1, int v2, int v3) throws RemoteException;

	RouteInterface getRoute_adj1() throws RemoteException;

	RouteInterface getRoute_adj2() throws RemoteException;

	RouteInterface getRoute_adj3() throws RemoteException;
	
	void ajouterRoute(RouteInterface r) throws RemoteException;

	int getVille_adj1() throws RemoteException;
 
	int getVille_adj2() throws RemoteException;

	int getVille_adj3() throws RemoteException;

	JoueurInterface getOqp() throws RemoteException;

	int getGain() throws RemoteException;

	boolean isColonieVille() throws RemoteException;

}