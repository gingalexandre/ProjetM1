package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.modeles.Message;

/**
 * Interface permettant de communiquer
 * @author jerome
 */
public interface Communication extends Remote{
	void recevoir(Message message) throws RemoteException;
}
