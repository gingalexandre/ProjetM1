package serveur.modele.carte;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.modele.service.CarteInterface;

/**
 * Carte de Monopole d'une ressource. Le joueur reçoit toutes les cartes d'un type choisi des autres joueurs.
 *
 * @author Yohann Hugo
 */
public class Monopole extends Carte{

    /**
     * Constructeur
     */
    public Monopole() throws RemoteException {
        super("Carte Monopole","/Ressources/cartes/carte_progres_monopole.png",false);
    }

    /**
     * Constructeur a partir d'une sauvegarde
     * @param carte
     */
    public Monopole(CarteSauvegarde carte) throws RemoteException {
        super(carte.getNom(), carte.getChemin(), carte.getUtilisable());
	}

}
