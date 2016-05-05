package serveur.modele.carte;

import java.io.Serializable;
import java.rmi.RemoteException;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;

/**
 * Carte victoire qui permettent de gagner de façon permanente deux points de victoire au joueur jouant la classe.
 *
 * @author Yohann Hugo
 */
public class Victoire extends Carte implements Serializable {

    /**
     * Constructeur simple
     */
    public Victoire() throws RemoteException{
        super("Carte Victoire", "/Ressources/cartes/armee_puissante.png", true);
    }
    
    /**
     * Constructeur a partir d'une sauvegarde
     * @param carte 
     */
    public Victoire(CarteSauvegarde carte) throws RemoteException{
        super(carte.getNom(), carte.getChemin(), carte.getUtilisable());
    }

}
