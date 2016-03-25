package client.modele.carte;

import client.modele.Joueur;

/**
 *Carte construction qui permettent de gagner de façon permanente deux routes au joueur jouant la classe.
 *
 * @author Yohann Hugo
 */
public class Route extends Carte{

    /**
     * Constructeur
     */
    public Route(){
        super(2);
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de progrès construction.
     *
     * @param currentPlayer Joueur déclanchant l'action.
     */
    public void doAction(Joueur currentPlayer) {
        currentPlayer.setNbRoute(currentPlayer.getNbRoute()+2);
    }
}
