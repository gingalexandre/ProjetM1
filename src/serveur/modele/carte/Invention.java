package serveur.modele.carte;

import java.rmi.RemoteException;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;

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
        super("Carte Invention","/cartes/carte_progres_invention.png",false);
    }

    /**
     * Constructeur a partir d'une sauvegarde
     * @param carte
     */
    public Invention(CarteSauvegarde carte) throws RemoteException {
        super(carte.getNom(), carte.getChemin(), carte.getUtilisable());
	}

}
