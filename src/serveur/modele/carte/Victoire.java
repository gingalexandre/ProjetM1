package serveur.modele.carte;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.modele.service.CarteInterface;

/**
 * Carte victoire qui permettent de gagner de façon permanente deux points de victoire au joueur jouant la classe.
 *
 * @author Yohann Hugo
 */
public class Victoire extends Carte {

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
