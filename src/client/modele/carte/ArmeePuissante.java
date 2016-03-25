package client.modele.carte;

import client.modele.Joueur;

/**
 * Classe représente la Carte de l'armee la plus puissante.
 * @author Yohann Hugo
 */
public class ArmeePuissante{

    /**
     * Attribut : condition minimal requise.
     */
    private static int NB_CHEVALIER_MINIMAL = 3;


    /**
     * Constructeur
     */
    public ArmeePuissante(){
    }

    /**
     * Action provoquer lorsqu'un joueur reçoit la carte d'armée la plus puissant.
     *
     * @param currentPlayer Joueur déclanchant l'action.
     * @param Player Joueur qui perds la carte. Peut être null si aucun joueur ne la possède auparavant.
     */

    public void doAction(Joueur currentPlayer, Joueur Player) {
        currentPlayer.setPointVictoire(currentPlayer.getPointVictoire()+2);
        if(Player!=null) Player.setPointVictoire(Player.getPointVictoire()-2);
    }
}
