package serveur.modele.service;

import serveur.modele.carte.ArmeePuissante;
import serveur.modele.carte.LongueRoute;

import java.util.List;

/**
 * Created by Yohann Hugo on 28/04/2016.
 */
public interface PaquetInterface {
    CarteInterface pioche();

    List<CarteInterface> getDeck();

    void setDeck(List<CarteInterface> deck);

    LongueRoute getLr();

    void setLr(LongueRoute lr);

    ArmeePuissante getAp();

    void setAp(ArmeePuissante ap);
}
