package serveur.modele.service;

import serveur.modele.carte.ArmeePuissante;
import serveur.modele.carte.LongueRoute;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Yohann Hugo on 28/04/2016.
 */
public interface PaquetInterface {
    CarteInterface pioche() throws RemoteException;

    List<CarteInterface> getDeck() throws RemoteException;

    void setDeck(List<CarteInterface> deck) throws RemoteException;

    LongueRoute getLr() throws RemoteException;

    void setLr(LongueRoute lr) throws RemoteException;

    ArmeePuissante getAp() throws RemoteException;

    void setAp(ArmeePuissante ap) throws RemoteException;
}
