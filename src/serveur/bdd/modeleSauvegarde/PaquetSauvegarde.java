package serveur.bdd.modeleSauvegarde;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import serveur.modele.carte.ArmeePuissante;
import serveur.modele.carte.LongueRoute;
import serveur.modele.carte.Paquet;
import serveur.modele.service.CarteInterface;

public class PaquetSauvegarde {

    /**
     * Attribut ap pour la carte spéciale armée puissante.
     */
    private ArmeePuissante ap = new ArmeePuissante();

    /**
     * Attribut lr pour la carte spéciale longue rotue.
     */
    private LongueRoute lr = new LongueRoute();

    /**
     * Attribut deck pour l'ensemble des cartes restantes.
     */
    private List<CarteInterface> deck = new LinkedList<>();

	public PaquetSauvegarde(Paquet paquet) throws RemoteException {
		super();
		this.ap = paquet.getAp();
		this.lr = paquet.getLr();
		this.deck = paquet.getDeck();
	}

	public PaquetSauvegarde() throws RemoteException {}


	public ArmeePuissante getAp() {
		return ap;
	}

	public void setAp(ArmeePuissante ap) {
		this.ap = ap;
	}

	public LongueRoute getLr() {
		return lr;
	}

	public void setLr(LongueRoute lr) {
		this.lr = lr;
	}

	public List<CarteInterface> getDeck() {
		return deck;
	}

	public void setDeck(List<CarteInterface> deck) {
		this.deck = deck;
	}
	
	
}
