package client.modele.carte;

import client.modele.Joueur;

/**
 * @auhtor Yohann Hugo
 */
public class LongueRoute {

    /**
     * Attribut : condition minimal requise.
     */
    private static int NB_ROUTE_MINIMAL = 5;


    /**
     * Constructeur
     */
    public LongueRoute(){
    }

    /**
     * Action provoquer lorsqu'un joueur reçoit la carte de type longue route.
     *
     * @param currentPlayer Joueur déclanchant l'action.
     * @param Player Joueur qui perds la carte.
     */

    public void doAction(Joueur currentPlayer, Joueur Player) {
        currentPlayer.setPointVictoire(currentPlayer.getPointVictoire()+2);
        if(Player!=null) Player.setPointVictoire(Player.getPointVictoire()-2);
    }
}
