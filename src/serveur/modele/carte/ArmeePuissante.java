package serveur.modele.carte;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.modele.service.CarteInterface;

/**
 * Classe représente la Carte de l'armee la plus puissante.
 * @author Yohann Hugo
 */
public class ArmeePuissante extends Carte {

	/**
     * Attribut : condition minimal requise.
     */
    public static int NB_CHEVALIER_MINIMAL = 3;

    /**
     * Constructeur
     */
    public ArmeePuissante() throws RemoteException {
        super("Armée la plus puissante","/Ressources/cartes/armee_puissante.png",false);
    }

    /**
     * Constructeur a partir d'une sauvegarde
     * @param carte
     */
    public ArmeePuissante(CarteSauvegarde carte) throws RemoteException {
        super(carte.getNom(), carte.getChemin(), carte.getUtilisable());
	}

}
