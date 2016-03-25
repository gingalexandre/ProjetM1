package client.modele.carte;

import client.modele.Hexagone;

/**
 * Carte chevalier du jeu : ces cartes permettent de déplacer le voleur d'une case à l'autre.
 *
 * @author Yohann Hugo
 */
public class Chevalier extends Carte{

    /**
     * Constructeur
     */
    public Chevalier(){
        super(14);
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de chevalier.
     *
     * @param params Todo
     */

    public void doAction(Object... params) {
        // TODO: 21/03/2016 Quand voleur implémenter.

        //Voleur.hexagone = position_voleur;
    }

}
