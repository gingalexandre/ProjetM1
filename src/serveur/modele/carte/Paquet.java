package serveur.modele.carte;

import java.rmi.RemoteException;
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

        int i = 0;
        int random_value = 0;
        while(i<NBCARTE){
            random_value = (int) Math.round(Math.random()*5);
            switch(random_value){
                case 1:
                    if(NBCHEVALIER>0){
                        deck.add(i, new Chevalier());
                        NBCHEVALIER--;
                    }
                    break;
                case 2:
                    if(NBINVENTION>0){
                        deck.add(i, new Invention());
                        NBINVENTION--;
                    }
                    break;
                case 3:
                    if(NBMONOPOLE>0){
                        deck.add(i, new Monopole());
                        NBMONOPOLE--;
                    }
                    break;
                case 4:
                    if(NBVICTOIRE>0){
                        deck.add(i, new Victoire());
                        NBVICTOIRE--;
                    }
                    break;
                case 5:
                    if(NBROUTE>0){
                        deck.add(i, new Route());
                        NBROUTE--;
                    }
                    break;
                default:
                    break;
            }
            i = deck.size();
        }
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
