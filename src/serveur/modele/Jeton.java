package serveur.modele;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.shape.Circle;
import serveur.commun.DistributeurJeton;
import serveur.view.VueJeton;

public class Jeton implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static int NUMERO1 = 1;
	public final static int NUMERO2 = 2;
	public final static int NUMERO3 = 3;
	public final static int NUMERO4 = 4;
	public final static int NUMERO5 = 5;
	public final static int NUMERO6 = 6;
	public final static int NUMERO8 = 8;
	public final static int NUMERO9 = 9;
	public final static int NUMERO10 = 10;
	public final static int NUMERO11 = 11;
	public final static int NUMERO12 = 12;

	private Point emplacement;

	private int numeroJeton;

	public Jeton(Hexagone hexagone) {
		numeroJeton = DistributeurJeton.getInstance().donnerJeton();
		emplacement = hexagone.getCentre();
	}

	public int getNumeroJeton() {
		return this.numeroJeton;
	}

	public Point getEmplacement() {
		return this.emplacement;
	}

	public Double[] getPoints() {
		Double[] res = { emplacement.getX(), emplacement.getY() };
		return res;
	}

	public static Circle[] transformVueJeton(ArrayList<Jeton> jetons) {
		Circle[] vueJetons = new Circle[jetons.size()-1];
		jetons.remove(null);
		for (int i = 0; i < jetons.size(); i++) {
			if (jetons.get(i) != null) {
				vueJetons[i] = new VueJeton(jetons.get(i)).getCircle();
			}
		}
		return vueJetons;
	}

}
