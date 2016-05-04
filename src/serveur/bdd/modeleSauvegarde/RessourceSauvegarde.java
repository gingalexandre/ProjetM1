package serveur.bdd.modeleSauvegarde;

import java.util.HashMap;

import serveur.modele.Ressource;

public class RessourceSauvegarde {
	private HashMap<Integer, Integer> stockBois = new HashMap<>();
	private HashMap<Integer, Integer> stockBle = new HashMap<>();
	private HashMap<Integer, Integer> stockLaine = new HashMap<>();
	private HashMap<Integer, Integer> stockArgile = new HashMap<>();
	private HashMap<Integer, Integer> stockMineraie = new HashMap<>();

	public RessourceSauvegarde(Ressource r) {
		this.stockArgile = r.getStockArgile();
		this.stockBle = r.getStockBle();
		this.stockBois = r.getStockBois();
		this.stockLaine = r.getStockLaine();
		this.stockMineraie = r.getStockMineraie();
	}

	public RessourceSauvegarde() {
	}

	public HashMap<Integer, Integer> getStockBois() {
		return stockBois;
	}

	public void setStockBois(HashMap<Integer, Integer> stockBois) {
		this.stockBois = stockBois;
	}

	public HashMap<Integer, Integer> getStockBle() {
		return stockBle;
	}

	public void setStockBle(HashMap<Integer, Integer> stockBle) {
		this.stockBle = stockBle;
	}

	public HashMap<Integer, Integer> getStockLaine() {
		return stockLaine;
	}

	public void setStockLaine(HashMap<Integer, Integer> stockLaine) {
		this.stockLaine = stockLaine;
	}

	public HashMap<Integer, Integer> getStockArgile() {
		return stockArgile;
	}

	public void setStockArgile(HashMap<Integer, Integer> stockArgile) {
		this.stockArgile = stockArgile;
	}

	public HashMap<Integer, Integer> getStockMineraie() {
		return stockMineraie;
	}

	public void setStockMineraie(HashMap<Integer, Integer> stockMineraie) {
		this.stockMineraie = stockMineraie;
	}

	public boolean equals(Object o) {
		return o instanceof RessourceSauvegarde && this.stockArgile.equals(((RessourceSauvegarde) o).getStockArgile())
				&& this.stockBle.equals(((RessourceSauvegarde) o).getStockBle())
				&& this.stockBois.equals(((RessourceSauvegarde) o).getStockBois())
				&& this.stockArgile.equals(((RessourceSauvegarde) o).getStockArgile())
				&& this.stockLaine.equals(((RessourceSauvegarde) o).getStockLaine())
				&& this.stockMineraie.equals(((RessourceSauvegarde) o).getStockMineraie());
	}

}
