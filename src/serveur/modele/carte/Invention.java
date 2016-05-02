package serveur.modele.carte;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.modele.service.CarteInterface;

/**
 * Carte d'innovation : ces cartes permettent d'obtenir deux d'une ressource.
 *
 * @author Yohann Hugo
 */
public class Invention extends UnicastRemoteObject implements CarteInterface {

	private String nomCarte = "Carte Invention";

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN = "/Ressources/cartes/carte_progres_invention.png";


    /**
     * Constructeur
     */
    public Invention() throws RemoteException {
    }

    public Invention(CarteSauvegarde carte) throws RemoteException {
		this.nomCarte = carte.getNom();
		this.CHEMIN = carte.getChemin();
	}

	/**
     * Action provoquer lorsqu'un joueur utilise la carte de type de progrès ressource.
     */
    public void doAction() throws RemoteException {
        /*
         currentPlayer.getStockRessource().put(ressourceChoisie, currentPlayer.getStockRessource().get(ressourceChoisie)+2);
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
