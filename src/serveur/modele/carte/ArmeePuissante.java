package serveur.modele.carte;

import java.io.Serializable;
import java.rmi.RemoteException;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;

/**
 * Classe représente la Carte de l'armee la plus puissante.
 * @author Yohann Hugo
 */
public class ArmeePuissante extends Carte implements Serializable{

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
