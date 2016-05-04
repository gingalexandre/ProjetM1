package serveur.modele.carte;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.modele.service.CarteInterface;

/**
 * @author Yohann Hugo
 */
public class LongueRoute extends Carte {

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
