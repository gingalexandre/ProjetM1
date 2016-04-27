package serveur.modele.carte;

import java.io.Serializable;

/**
 * Interface des cartes.
 *
 * @author Yohann HUGO
 */
public interface Carte extends Serializable{

    public void doAction();
    public String getCheminImage();
    
    public String getNom();

}
