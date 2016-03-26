package client.modele.carte;

import client.modele.Joueur;

/**
 * @auhtor Yohann Hugo
 */
public class LongueRoute implements Carte {

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
    public LongueRoute(){
    }

    /**
     * Action provoquer lorsqu'un joueur reçoit la carte de longue route.
     */
    @Override
    public void doAction() {
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
    public String getCheminImage() {
        return CHEMIN;
    }
}
