package serveur.modele.carte;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import serveur.modele.service.CarteInterface;

/**
 * Classe symbolisant le paquet de carte pour une partie.
 *
 *
 * @author Yohann HUGO
 */
public class Paquet implements serveur.modele.service.PaquetInterface {

    /**
     * Attribut NBCHEVALIER pour le nombre de carte de type chevalier dans le paquet.
     */
    private static int NBCHEVALIER = 14;

    /**
     * Attribut NBINVENTION pour le nombre de carte de type invention dans le paquet.
     */
    private static int NBINVENTION = 2;

    /**
     * Attribut NBMONOPOLE pour le nombre de carte de type Monopole dans le paquet.
     */
    private static int NBMONOPOLE = 2;


    /**
     * Attribut NBVICTOIRE pour le nombre de carte de type Victoire dans le paquet.
     */
    private static int NBVICTOIRE = 5;

    /**
     * Attribut NBROUTE pour le nombre de carte de type Route dans le paquet.
     */
    private static int NBROUTE = 2;

    /**
     * Attribut ap pour la carte spéciale armée puissante.
     */
    private ArmeePuissante ap;

    /**
     * Attribut lr pour la carte spéciale longue rotue.
     */
    private LongueRoute lr;

    /**
     * Attribut deck pour l'ensemble des cartes restantes.
     */
    private List<CarteInterface> deck = new LinkedList<>();

    /**
     * Constructeur du paquet.
     * @throws RemoteException
     */
    public Paquet() throws RemoteException {
        ap = new ArmeePuissante();
        lr = new LongueRoute();
        for (int i=0;i<NBVICTOIRE;i++) deck.add(new Victoire());
        for (int i=0;i<NBROUTE;i++) deck.add(new Route());
        for (int i=0;i<NBMONOPOLE;i++) deck.add(new Monopole());
        for (int i=0;i<NBCHEVALIER;i++) deck.add(new Chevalier());
        for (int i=0;i<NBINVENTION;i++) deck.add(new Invention());
        Collections.shuffle(deck);
    }

    /**
     * Méthode permettant de piocher la première carte du paquet.
     * @return piochee une carte.
     * @throws RemoteException
     */
    @Override
    public CarteInterface pioche() throws RemoteException {
        if(deck.isEmpty()) return null;
        CarteInterface piochee = deck.get(0);
        deck.remove(0);
        return piochee;
    }

    /**
     * Getter de l'attibut deck
     * @return La liste des cartes
     * @throws RemoteException
     */
    @Override
    public List<CarteInterface> getDeck() throws RemoteException {
        return deck;
    }

    /**
     * Setter deck
     * @throws RemoteException
     */
    @Override
    public void setDeck(List<CarteInterface> deck) throws RemoteException {
        this.deck = deck;
    }

    /**
     * Getter de la carte "Route la plus longue"
     * @return La carte spéciale "Route la plus longue"
     * @throws RemoteException
     */
    @Override
    public LongueRoute getLr() throws RemoteException {
        return lr;
    }

    /**
     * Setter carte spécial longue route
     * @throws RemoteException
     */
    @Override
    public void setLr(LongueRoute lr) throws RemoteException {
        this.lr = lr;
    }

    /**
     * Getter carte spécial armée puissante
     * @return lr carte sépciale armée puissante
     * @throws RemoteException
     */
    @Override
    public ArmeePuissante getAp() throws RemoteException {
        return ap;
    }

    /**
     * Setter carte spécial armée puissante
     * @param ap ArmeePuissante 
     * @throws RemoteException
     */
    @Override
    public void setAp(ArmeePuissante ap) throws RemoteException {
        this.ap = ap;
    }
}
