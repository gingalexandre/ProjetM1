package client.modele.carte;

import client.modele.Joueur;

/**
 * Carte victoire qui permettent de gagner de façon permanente deux points de victoire au joueur jouant la classe.
 *
 * @author Yohann Hugo
 */
public class Victoire extends Carte{

    /**
     * Constructeur
     */
    public Victoire(){
        super(5);
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de progrès victoire.
     *
     * @param currentPlayer Joueur déclanchant l'action.
     */

    public void doAction(Joueur currentPlayer ) {
        currentPlayer.setPointVictoire(currentPlayer.getPointVictoire()+2);
    }
}
