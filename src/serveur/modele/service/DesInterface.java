package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DesInterface extends Remote {

	Integer[] lancerDes() throws RemoteException;

	void actionDes(int scoreDe) throws RemoteException;
}