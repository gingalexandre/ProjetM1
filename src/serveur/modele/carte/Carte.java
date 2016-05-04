package serveur.modele.carte;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveur.modele.service.CarteInterface;

/**
 * Created by Yohann Hugo on 03/05/2016.
 */
public class Carte extends UnicastRemoteObject implements CarteInterface, Serializable {

    /**
     * Attribut du nom de la carte.
     */
    private String nomCarte;

    /**
     * Attribut serialVersionUid pour unicast remote object
     */
    private static final long serialVersionUID = 1L;

    /**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN;

    /**
     * Attribut si la carte est utilisable
     */
    private static boolean utilisable;

    /**
     * Constructeur par rapport a un nom et une valeur.
     * @param nom
     * @param chemin
     * @throws RemoteException
     */

    public Carte(String nom, String chemin, boolean value) throws RemoteException{
        nomCarte=nom;
        CHEMIN=chemin;
        utilisable=value;
    }


    /**
     * Retourne le chemin de l'image de la carte.
     * @return retourne le chemin de l'image
     * @throws RemoteException
     */
    @Override
    public String getCheminImage() throws RemoteException {
        return CHEMIN;
    }

    /**
     * Retourne le nom de la carte
     * @return nom de la carte
     * @throws RemoteException
     */
    @Override
    public String getNom() throws RemoteException {
        return nomCarte;
    }

    /**
     * Retourne si la carte est utilisable
     * @return boolean d'utilisation possible
     * @throws RemoteException
     */
    @Override
    public boolean getUtilisable() throws RemoteException {
        return utilisable;
    }


    /**
     * Change la valeur d'une carte pour la rendre ou non utilisable
     * @param value boolean d'utilisation possible oui ou non.
     * @throws RemoteException
     */
    @Override
    public void setUtilisable(boolean value) throws RemoteException {
        utilisable = value;
    }
}
