package serveur.modele.carte;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.modele.service.CarteInterface;

/**
 * Carte d'innovation : ces cartes permettent d'obtenir deux d'une ressource.
 *
 * @author Yohann Hugo
 */
public class Invention extends Carte {

    /**
     * Constructeur
     */
    public Invention() throws RemoteException {
        super("Carte Invention","/Ressources/cartes/carte_progres_invention.png",false);
    }

    /**
     * Constructeur a partir d'une sauvegarde
     * @param carte
     */
    public Invention(CarteSauvegarde carte) throws RemoteException {
        super(carte.getNom(), carte.getChemin(), carte.getUtilisable());
	}

}
