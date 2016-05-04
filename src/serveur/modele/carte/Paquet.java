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
     * Attribut NBCARTE pour le nombre de carte totale dans le paquet.
     */
    private static int NBCARTE = 25;

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
     */
    public Paquet() throws RemoteException {
        ap = new ArmeePuissante();
        lr = new LongueRoute();
        int random_value = 0;
        while(i<NBCARTE){
            random_value = (int) Math.round(Math.random()* 25);
            switch(random_value){
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    if(NBCHEVALIER>0){
                        deck.add(i, new Chevalier());
                        NBCHEVALIER--;
                    }
                    break;
                case 14:
                case 15:
                    if(NBINVENTION>0){
                        deck.add(i, new Invention());
                        NBINVENTION--;
                    }
                    break;
                case 16:
                case 17:
                    if(NBMONOPOLE>0){
                        deck.add(i, new Monopole());
                        NBMONOPOLE--;
                    }
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                    if(NBVICTOIRE>0){
                        deck.add(i, new Victoire());
                        NBVICTOIRE--;
                    }
                    break;
                case 23:
                case 24:
                    if(NBROUTE>0){
                        deck.add(i, new Route());
                        NBROUTE--;
                    }
                    break;
                default:
                    break;
            }
         
        }
        for (int i =0;i<NBVICTOIRE;i++) deck.add(new Victoire());
        for (int i =0;i<NBROUTE;i++) deck.add(new Route());
        for (int i =0;i<NBMONOPOLE;i++) deck.add(new Monopole());
        Collections.shuffle(deck);
    }

    /**
     * Méthode permettant de piocher la première carte du paquet.
     * @return piochee une carte.
     */
    @Override
    public CarteInterface pioche() throws RemoteException {
        if(deck.isEmpty()) return null;
        CarteInterface piochee = deck.get(0);
        deck.remove(0);
        return piochee;
    }

    /**
     * Getter deck
     * @retun deck la liste des cartes
     */
    @Override
    public List<CarteInterface> getDeck() throws RemoteException {
        return deck;
    }

    /**
     * Setter deck
     */
    @Override
    public void setDeck(List<CarteInterface> deck) throws RemoteException {
        this.deck = deck;
    }

    /**
     * Getter carte spécial longue route
     * @retun lr carte sépciale longue route
     */
    @Override
    public LongueRoute getLr() throws RemoteException {
        return lr;
    }

    /**
     * Setter carte spécial longue route
     */
    @Override
    public void setLr(LongueRoute lr) throws RemoteException {
        this.lr = lr;
    }

    /**
     * Getter carte spécial armée puissante
     * @retun lr carte sépciale armée puissante
     */
    @Override
    public ArmeePuissante getAp() throws RemoteException {
        return ap;
    }

    /**
     * Setter carte spécial armée puissante
     */
    @Override
    public void setAp(ArmeePuissante ap) throws RemoteException {
        this.ap = ap;
    }
}
