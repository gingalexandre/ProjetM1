package client.modele.carte;

import client.modele.Joueur;

/**
 * Carte de Monopole d'une ressource. Le joueur reçoit toutes les cartes d'un type choisi des autres joueurs.
 *
 * @author Yohann Hugo
 */
public class Monopole extends Carte{

    /**
     * Constructeur
     */
    public Monopole(){
        super(2);
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de Monopole.
     *
     * @param currentPlayer Joueur déclanchant l'action.
     * @param ressourceChoisie C'est la ressource que l'on souhaite augmenter.
     * @param quantite C'est la quantite que l'on possède.
     */
    public void doAction(Joueur currentPlayer, Integer ressourceChoisie, Integer quantite) {
        currentPlayer.getStockRessource().put(ressourceChoisie, currentPlayer.getStockRessource().get(ressourceChoisie)+quantite);
    }
}
