package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.modeles.Message;

public interface Joueur extends Remote{
	void recevoir(Message message) throws RemoteException;
}
