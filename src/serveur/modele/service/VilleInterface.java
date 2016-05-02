package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Point;

public interface VilleInterface extends Remote{

	/**
	 * @return l'emplacement de la ville
	 * @throws RemoteException
	 */
	Point getEmplacement() throws RemoteException;

	/**
	 * Indique si c'est possible de construire la ville
	 * @param proprio - proprietaire de la ville
	 * @param villes - liste des autres villes
	 * @return true si il est possible de construire la ville, false sinon
	 * @throws RemoteException
	 */
	boolean estLibre(JoueurInterface proprio, ArrayList<VilleInterface> villes) throws RemoteException;

	/**
	 * @return le joueur qui possede la ville
	 * @throws RemoteException
	 */
	JoueurInterface getOqp() throws RemoteException;
	
	/**
	 * @param j - joueur qui occupera la ville
	 * @throws RemoteException
	 */
	void setOQP(JoueurInterface j) throws RemoteException;

	/**
	 * @return la première ville adjacente
	 * @throws RemoteException
	 */
	RouteInterface getRoute_adj1() throws RemoteException;

	/**
	 * @return la deuxième ville adjacente
	 * @throws RemoteException
	 */
	RouteInterface getRoute_adj2() throws RemoteException;

	/**
	 * @return la troisième ville adjacente
	 * @throws RemoteException
	 */
	RouteInterface getRoute_adj3() throws RemoteException;

	/**
	 * @return la ville adjacente 1
	 * @throws RemoteException
	 */
	int getVille_adj1() throws RemoteException;
 
	/**
	 * @return la ville adjacente 2
	 * @throws RemoteException
	 */
	int getVille_adj2() throws RemoteException;

	/**
	 * @return la ville adjacente 3
	 * @throws RemoteException
	 */
	int getVille_adj3() throws RemoteException;
	
	/**
	 * @param v1 - ville adjacente numero 1
	 * @param v2 - ville adjacente numero 2
	 * @param v3 - ville adjacente numero 3
	 * @throws RemoteException
	 */
	void setVillesAdj(int v1, int v2, int v3) throws RemoteException;

	/**
	 * @return le gain de la ville
	 * @throws RemoteException
	 */
	int getGain() throws RemoteException;

	/**
	 * @return si la colonie est une ville
	 * @throws RemoteException
	 */
	boolean isVille() throws RemoteException;
	
	/**
	 * Ajouter une route à une ville
	 * @param r - route à ajouter
	 * @throws RemoteException
	 */
	void ajouterRoute(RouteInterface r) throws RemoteException;
	
	/**
	 * Transforme une colonie en ville
	 * @param j
	 * @throws RemoteException
	 */
	void colonieToVille(JoueurInterface j) throws RemoteException;

}