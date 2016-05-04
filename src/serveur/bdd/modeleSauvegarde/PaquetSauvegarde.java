package serveur.bdd.modeleSauvegarde;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import serveur.commun.Fonctions;
import serveur.modele.carte.Paquet;
import serveur.modele.service.CarteInterface;
import serveur.modele.service.PaquetInterface;

public class PaquetSauvegarde {

	/**
	 * Attribut ap pour la carte spéciale armée puissante.
	 */
	private CarteSauvegarde ap;

	/**
	 * Attribut lr pour la carte spéciale longue rotue.
	 */
	private CarteSauvegarde lr;

	/**
	 * Attribut deck pour l'ensemble des cartes restantes.
	 */
	private List<CarteSauvegarde> deck = new LinkedList<>();

	/**
	 * Constructeur de la classe PaquetSauvegarde
	 * @param paquet
	 * @throws RemoteException
	 */
	public PaquetSauvegarde(Paquet paquet) throws RemoteException {
		super();
		this.ap = new CarteSauvegarde(paquet.getAp());
		this.lr = new CarteSauvegarde(paquet.getLr());
		this.deck = Fonctions.transformArrayCarte(paquet.getDeck());
	}

	public PaquetSauvegarde() {}

	public PaquetSauvegarde(PaquetInterface paquet) throws RemoteException {
		this.ap = new CarteSauvegarde(paquet.getAp());
		this.lr = new CarteSauvegarde(paquet.getLr());
		this.deck = Fonctions.transformArrayCarte(paquet.getDeck());
	}

	/**
	 * Getter de l'armée la plus puissante
	 * @return l'armée la plus puissante
	 */
	public CarteSauvegarde getAp() {
		return ap;
	}

	/**
	 * Setter de l'armée la plus puissante
	 * @param ap - nouvelle armée plus puissante
	 */
	public void setAp(CarteSauvegarde ap) {
		this.ap = ap;
	}

	/**
	 * Getter de la route la plus longue
	 * @return la route la plus longue
	 */
	public CarteSauvegarde getLr() {
		return lr;
	}

	/**
	 * Setter de la route la plus longue
	 * @param lr - nouvelle route la plus longue
	 */
	public void setLr(CarteSauvegarde lr) {
		this.lr = lr;
	}

	/**
	 * Getter du deck de carte
	 * @return le deck de carte
	 */
	public List<CarteSauvegarde> getDeck() {
		return deck;
	}

	/**
	 * Setter du deck de cartes
	 * @param deck - nouveau deck
	 */
	public void setDeck(List<CarteSauvegarde> deck) {
		this.deck = deck;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		boolean res = true;
		int i = 0;
		for (CarteSauvegarde p : ((PaquetSauvegarde) o).getDeck()) {
			res = res && p.equals(this.getDeck().get((i)));
			i++;
		}
		return res && o instanceof PaquetSauvegarde && ((PaquetSauvegarde) o).getAp().equals(this.ap)
				&& ((PaquetSauvegarde) o).getLr().equals(this.lr);
	}
}
