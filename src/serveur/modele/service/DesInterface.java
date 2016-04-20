package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DesInterface extends Remote {

	/**
	 * Lance les deux dès
	 * @return le résultat sous forme de tableau
	 * @throws RemoteException
	 */
	Integer[] lancerDes() throws RemoteException;

	/**
	 * Réalise une action en fonction du score des dès
	 * @param scoreDe - somme des résultats des deux dès
	 * @throws RemoteException
	 */
	void actionDes(int scoreDe) throws RemoteException;
}