package serveur.modele.carte;

import java.io.Serializable;

/**
 * Carte chevalier du jeu : ces cartes permettent de déplacer le voleur d'une case à l'autre.
 *
 * @author Yohann Hugo
 */
public class Chevalier implements Carte, Serializable{

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
    public Chevalier(){
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de chevalier.
     */
    @Override
    public void doAction() {
        /*
        TODO nécessite la séléction d'hexagone.
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
}
