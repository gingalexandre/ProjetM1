package serveur.modele.carte;

import java.rmi.RemoteException;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;

/**
 *Carte construction qui permettent de gagner de façon permanente deux routes au joueur jouant la classe.
 *
 * @author Yohann Hugo
 */
public class Route extends Carte {

    /**
     * Constructeur
     */
    public Route()throws RemoteException {
        super("Carte Route", "/Ressources/cartes/carte_progres_route.png", false);
    }

    /**
     * Constructeur a partir d'une sauvegarde
     * @param carte
     */
    public Route(CarteSauvegarde carte) throws RemoteException {
        super(carte.getNom(), carte.getChemin(), carte.getUtilisable());
	}

}
