package serveur.modele.carte;

import serveur.modele.service.CarteInterface;

import java.io.Serializable;

/**
 * Carte de Monopole d'une ressource. Le joueur reçoit toutes les cartes d'un type choisi des autres joueurs.
 *
 * @author Yohann Hugo
 */
public class Monopole implements CarteInterface, Serializable{
	
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
    public Monopole(){
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de Monopole.
     */
    @Override
    public void doAction() {
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
    public String getCheminImage() {
        return CHEMIN;
    }

	@Override
	public String getNom() {
		return this.nomCarte;
	}
}
