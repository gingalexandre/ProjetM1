/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.modele;

import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class Partie {
    
    private ArrayList <Joueur> listeJoueurs;
    private Ressource ressources;
    
    public Partie(int nbJoueurs){
        init(nbJoueurs);
    }
    
    private void init(int nbJoueurs){
        this.listeJoueurs = new ArrayList();
        for (int i=0; i<nbJoueurs; i++){
            //Il faut les noms des joueurs avant...
            //listeJoueurs.add(new JoueurServeur());
        }
        this.ressources = new Ressource();
    }
    
    
    
}
