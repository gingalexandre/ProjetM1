package serveur.modele.carte;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.modele.service.CarteInterface;

/**
 * @auhtor Yohann Hugo
 */
public class LongueRoute extends Carte {

	/**
     * Attribut : condition minimal requise.
     */
    private static int NB_ROUTE_MINIMAL = 5;

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
