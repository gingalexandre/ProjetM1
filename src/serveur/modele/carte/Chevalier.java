package serveur.modele.carte;

import java.rmi.RemoteException;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;

/**
 * Carte chevalier du jeu : ces cartes permettent de déplacer le voleur d'une case à l'autre.
 *
 * @author Yohann Hugo
 */
public class Chevalier extends Carte{
	
    /**
     * Constructeur
     */
    public Chevalier() throws RemoteException {
        super("Carte Chevalier","/Ressources/cartes/carte_chevalier.png",false);
    }

    /**
     * Constructeur a partir d'une sauvegarde
     * @param carte
     */
    public Chevalier(CarteSauvegarde carte) throws RemoteException {
        super(carte.getNom(), carte.getChemin(), carte.getUtilisable());
	}

}
