package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface des cartes.
 *
 * @author Yohann HUGO
 */
public interface CarteInterface extends Remote {
    /**
     * Engendre l'action de la carte
     * @throws RemoteException
     */
    public void doAction() throws RemoteException;

    /**
     * Retourne le chemin de l'image de la carte.
     * @return retourne le chemin de l'image
     * @throws RemoteException
     */
    public String getCheminImage() throws RemoteException;

    /**
     * Retourne le nom de la carte
     * @return nom de la carte
     * @throws RemoteException
     */
    public String getNom() throws RemoteException;

}
