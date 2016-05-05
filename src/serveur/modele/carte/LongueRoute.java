package serveur.modele.carte;

import java.io.Serializable;
import java.rmi.RemoteException;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;

/**
 * @author Yohann Hugo
 */
public class LongueRoute extends Carte implements Serializable{

	/**
     * Attribut : condition minimal requise.
     */
    public static int NB_ROUTE_MINIMAL = 5;

    /**
     * Constructeur
     * @throws RemoteException Exception dû a RMI
     */
    public LongueRoute() throws RemoteException {
        super( "Plus longue route","/Ressources/cartes/longue_route.png",false);
    }

    /**
     * Constructeur a partir d'une sauvegarde
     * @param carte Carte que l'on souhaite restaurer
     * @throws RemoteException Exception dû a RMI
     */
    public LongueRoute(CarteSauvegarde carte) throws RemoteException {
        super(carte.getNom(), carte.getChemin(), carte.getUtilisable());
	}

}
