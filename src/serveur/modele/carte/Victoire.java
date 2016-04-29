package serveur.modele.carte;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.modele.service.CarteInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Carte victoire qui permettent de gagner de façon permanente deux points de victoire au joueur jouant la classe.
 *
 * @author Yohann Hugo
 */
public class Victoire extends UnicastRemoteObject implements CarteInterface {

	private String nomCarte = "Carte Victoire";
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN = "/Ressources/cartes/armee_puissante.png";

    /**
     * Constructeur
     */
    public Victoire()throws RemoteException{
    }
    
    /**
     * Constructeur
     * @param carte 
     */
    public Victoire(CarteSauvegarde carte)throws RemoteException{
    	this.nomCarte = carte.getNom();
    	this.CHEMIN = carte.getChemin();
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de progrès victoire.
     */
    @Override
    public void doAction() throws RemoteException{
        /*
        currentPlayer.setPointVictoire(currentPlayer.getPointVictoire()+2);
         */
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
	public String getNom()throws RemoteException {
		return this.nomCarte;
	}
}
