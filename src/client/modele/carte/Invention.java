package client.modele.carte;

import client.modele.Joueur;

/**
 * Carte d'innovation : ces cartes permettent d'obtenir deux d'une ressource.
 *
 * @author Yohann Hugo
 */
public class Invention implements Carte {


    /**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN = "/Ressources/cartes/carte_progres_invention.png";


    /**
     * Constructeur
     */
    public Invention(){
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de progrès ressource.
     */
    @Override
    public void doAction() {
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
    public String getCheminImage() {
        return CHEMIN;
    }

}
