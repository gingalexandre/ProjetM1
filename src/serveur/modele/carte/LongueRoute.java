package serveur.modele.carte;

import java.io.Serializable;
import java.rmi.RemoteException;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;

/**
 * @auhtor Yohann Hugo
 */
public class LongueRoute extends Carte implements Serializable{

	/**
     * Attribut : condition minimal requise.
     */
    public static int NB_ROUTE_MINIMAL = 5;

    /**
     * Constructeur
     */
    public LongueRoute() throws RemoteException {
        super( "Plus longue route","/Ressources/cartes/longue_route.png",false);
    }

    /**
     * Constructeur a partir d'une sauvegarde
     * @param carte
     */
    public LongueRoute(CarteSauvegarde carte) throws RemoteException {
        super(carte.getNom(), carte.getChemin(), carte.getUtilisable());
	}

}
