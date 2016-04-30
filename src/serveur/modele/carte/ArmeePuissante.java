package serveur.modele.carte;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.modele.service.CarteInterface;

/**
 * Classe représente la Carte de l'armee la plus puissante.
 * @author Yohann Hugo
 */
public class ArmeePuissante extends UnicastRemoteObject implements CarteInterface {
	
	private String nomCarte = "Armée la plus puissante";
	
	private static final long serialVersionUID = 1L;

	/**
     * Attribut : condition minimal requise.
     */
    private static int NB_CHEVALIER_MINIMAL = 3;

    /**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN = "/Ressources/cartes/armee_puissante.png";

    /**
     * Constructeur
     */
    public ArmeePuissante() throws RemoteException {
    }

    public ArmeePuissante(CarteSauvegarde carte) throws RemoteException {
		this.nomCarte = carte.getNom();
		this.CHEMIN = carte.getChemin();
	}

	/**
     * Action provoquer lorsqu'un joueur reçoit la carte d'armée la plus puissant.
     */

    public void doAction() throws RemoteException {
    /*
        currentPlayer.setPointVictoire(currentPlayer.getPointVictoire()+2);
        f(Player!=null) Player.setPointVictoire(Player.getPointVictoire()-2);*/
    }

    /**
     * Méthode de l'interface Carte qui permet de récupérer le chemin de la ressources image d'une carte.
     *
     * @return chemin de la ressource de l'image.
     */
    @Override
    public String getCheminImage()throws RemoteException {
        return CHEMIN;
    }

	@Override
	public String getNom() throws RemoteException {
		return this.nomCarte;
	}
}
