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

    /**
     * Retourne si la carte est utilisable
     * @return boolean d'utilisation possible
     * @throws RemoteException
     */
    public boolean getUtilisable() throws RemoteException;

    /**
     * Change la valeur d'une carte pour la rendre ou non utilisable
     * @param value boolean d'utilisation possible oui ou non.
     * @throws RemoteException
     */
    public void setUtilisable(boolean value) throws RemoteException;

}
