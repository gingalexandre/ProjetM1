package serveur.bdd.modeleSauvegarde;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import serveur.commun.Fonctions;
import serveur.modele.carte.Paquet;

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

	public PaquetSauvegarde(Paquet paquet) throws RemoteException {
		super();
		this.ap = new CarteSauvegarde(paquet.getAp());
		this.lr = new CarteSauvegarde(paquet.getLr());
		this.deck = Fonctions.transformArrayCarte(paquet.getDeck());
	}

	public PaquetSauvegarde() {
	}

	public CarteSauvegarde getAp() {
		return ap;
	}

	public void setAp(CarteSauvegarde ap) {
		this.ap = ap;
	}

	public CarteSauvegarde getLr() {
		return lr;
	}

	public void setLr(CarteSauvegarde lr) {
		this.lr = lr;
	}

	public List<CarteSauvegarde> getDeck() {
		return deck;
	}

	public void setDeck(List<CarteSauvegarde> deck) {
		this.deck = deck;
	}

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
