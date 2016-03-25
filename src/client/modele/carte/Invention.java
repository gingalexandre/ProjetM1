package client.modele.carte;

import client.modele.Joueur;

/**
 * Carte d'innovation : ces cartes permettent d'obtenir deux d'une ressource.
 *
 * @author Yohann Hugo
 */
public class Invention extends Carte {

    /**
     * Constructeur
     */
    public Invention(){
        super(2);
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de progrès ressource.
     *
     * @param currentPlayer Joueur déclanchant l'action.
     * @param ressourceChoisie c'est la ressource que l'on souhaite augmenter.
     */
    public void doAction(Joueur currentPlayer, Integer ressourceChoisie) {
        currentPlayer.getStockRessource().put(ressourceChoisie, currentPlayer.getStockRessource().get(ressourceChoisie)+2);
    }



}
