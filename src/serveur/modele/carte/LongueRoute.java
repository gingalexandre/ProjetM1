package serveur.modele.carte;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.modele.service.CarteInterface;

/**
 * @auhtor Yohann Hugo
 */
public class LongueRoute extends UnicastRemoteObject implements CarteInterface {

	private String nomCarte = "Plus longue route";
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Attribut : condition minimal requise.
     */
    private static int NB_ROUTE_MINIMAL = 5;

    /**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN = "/Ressources/cartes/longue_route.png";

    /**
     * Constructeur
     */
    public LongueRoute() throws RemoteException {
    }

    public LongueRoute(CarteSauvegarde carte) throws RemoteException {
		this.nomCarte = carte.getNom();
		this.CHEMIN = carte.getChemin();
	}

	/**
     * Action provoquer lorsqu'un joueur reçoit la carte de longue route.
     */
    public void doAction() throws RemoteException {
        /*

        currentPlayer.setPointVictoire(currentPlayer.getPointVictoire()+2);
        if(Player!=null) Player.setPointVictoire(Player.getPointVictoire()-2);
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
