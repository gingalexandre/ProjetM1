package serveur.modele.carte;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.modele.service.CarteInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Carte chevalier du jeu : ces cartes permettent de déplacer le voleur d'une case à l'autre.
 *
 * @author Yohann Hugo
 */
public class Chevalier extends UnicastRemoteObject implements CarteInterface{
	
	private String nomCarte = "Carte Chevalier";
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN = "/Ressources/cartes/carte_chevalier.png";

    /**
     * Constructeur
     */
    public Chevalier() throws RemoteException {
    }

    public Chevalier(CarteSauvegarde carte) throws RemoteException {
		this.nomCarte = carte.getNom();
		this.CHEMIN = carte.getChemin();
	}

	/**
     * Action provoquer lorsqu'un joueur utilise la carte de type de chevalier.
     */
    @Override
    public void doAction() throws RemoteException {

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
