package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import serveur.modele.carte.ArmeePuissante;
import serveur.modele.carte.LongueRoute;

/**
 * Created by Marinthe Jérôme on 28/04/2016.
 */
public interface PaquetInterface extends Remote {
	
	/**
     * Méthode permettant de piocher la première carte du paquet.
     * @return piochee une carte.
     */
    CarteInterface pioche() throws RemoteException;

    /**
     * Getter deck
     * @retun deck la liste des cartes
     */
    List<CarteInterface> getDeck() throws RemoteException;

    /**
     * Setter deck
     */
    void setDeck(List<CarteInterface> deck) throws RemoteException;

    /**
     * Getter carte spécial longue route
     * @retun lr carte sépciale longue route
     */
    CarteInterface getLr() throws RemoteException;

    /**
     * Setter carte spécial longue route
     */
    void setLr(LongueRoute lr) throws RemoteException;

    /**
     * Getter carte spécial armée puissante
     * @retun lr carte sépciale armée puissante
     */
    CarteInterface getAp() throws RemoteException;

    /**
     * Setter carte spécial armée puissante
     */
    void setAp(ArmeePuissante ap) throws RemoteException;
}
