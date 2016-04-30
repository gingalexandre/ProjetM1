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
public class Monopole extends UnicastRemoteObject implements CarteInterface{
	
	private String nomCarte = "Carte Monopole";
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN = "/Ressources/cartes/carte_progres_monopole.png";

    /**
     * Constructeur
     */
    public Monopole() throws RemoteException {
    }

    public Monopole(CarteSauvegarde carte) throws RemoteException {
		this.nomCarte = carte.getChemin();
		this.CHEMIN = carte.getChemin();
	}

	/**
     * Action provoquer lorsqu'un joueur utilise la carte de type de Monopole.
     */
    public void doAction() throws RemoteException {
        /*
         currentPlayer.getStockRessource().put(ressourceChoisie, currentPlayer.getStockRessource().get(ressourceChoisie)+quantite);
         */
    }

    /**
     * Méthode de l'interface Carte qui permet de récupérer le chemin de la ressources image d'une carte.
     *
     * @return chemin de la ressource de l'image.
     */
    @Override
    public String getCheminImage() throws RemoteException {
        return CHEMIN;
    }

	@Override
	public String getNom() throws RemoteException {
		return this.nomCarte;
	}
}
