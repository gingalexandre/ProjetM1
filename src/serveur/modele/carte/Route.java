package serveur.modele.carte;

import serveur.modele.service.CarteInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *Carte construction qui permettent de gagner de façon permanente deux routes au joueur jouant la classe.
 *
 * @author Yohann Hugo
 */
public class Route extends UnicastRemoteObject implements CarteInterface {
	
	private String nomCarte = "Carte Route";
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN = "/Ressources/cartes/carte_progres_route.png";

    /**
     * Constructeur
     */
    public Route()throws RemoteException {
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de progrès construction.
     */
    public void doAction() throws RemoteException{
        /*
        currentPlayer.setNbRoute(currentPlayer.getNbRoute()+2);
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
	public String getNom()throws RemoteException {
		return this.nomCarte;
	}
}
