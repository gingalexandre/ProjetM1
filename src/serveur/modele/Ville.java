package serveur.modele;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.scene.shape.Circle;
import serveur.view.VueVille;

public class Ville implements Serializable {

	private static final long serialVersionUID = 1L;

	private Point emplacement;

	private int ville_adj1;

	private int ville_adj2;

	private int ville_adj3;

	private Route route_adj1;

	private Route route_adj2;

	private Route route_adj3;

	private Joueur oqp;

	private int gain;

	// Colonie : True & Ville : False
	private boolean colonieVille;

	public Ville(Point emplacement) {
		this.emplacement = emplacement;
	}

	public Point getEmplacement() {
		return emplacement;
	}

	public static Circle[] transformVilleVueVille(ArrayList<Ville> villes) {
		Circle[] vueVille = new Circle[villes.size()];
		for (int i = 0; i < villes.size(); i++) {
			vueVille[i] = new VueVille(villes.get(i).getEmplacement()).getCircle();
		}
		return vueVille;
	}


	public boolean estLibre(Joueur proprio, ArrayList<Ville> villes) {
		return ((this.oqp == null)
				&& (((this.ville_adj1 != -1) && (villes.get(this.ville_adj1).oqp == null))
						&& ((this.ville_adj2 != -1) && (villes.get(this.ville_adj2).oqp == null))
						&& ((this.ville_adj3 != -1) && (villes.get(this.ville_adj3).oqp == null)))
				&& ((this.route_adj1.getOqp() == proprio) || (this.route_adj2.getOqp() == proprio)
						|| (this.route_adj3.getOqp() == proprio)));
	}

	public void colonieToVille(Joueur j) throws RemoteException {
		j.setNbColonie(j.getNbColonie() + 1);
		j.setNbVille(j.getNbVille() - 1);

		this.colonieVille = false;
	}

	public void setOQP(Joueur j) {
		this.oqp = j;
	}

	public void setVillesAdj(int v1, int v2, int v3) {
		this.ville_adj1 = v1;
		this.ville_adj2 = v2;
		this.ville_adj3 = v3;
	}

	public Route getRoute_adj1() {
		return route_adj1;
	}

	public Route getRoute_adj2() {
		return route_adj2;
	}

	public Route getRoute_adj3() {
		return route_adj3;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getVille_adj1() {
		return ville_adj1;
	}


	public int getVille_adj2() {
		return ville_adj2;
	}


	public int getVille_adj3() {
		return ville_adj3;
	}

	public Joueur getOqp() {
		return oqp;
	}

	public int getGain() {
		return gain;
	}

	public boolean isColonieVille() {
		return colonieVille;
	}

	@Override
	public String toString() {
		return "Ville [emplacement=" + emplacement + ", oqp=" + oqp + ", gain=" + gain + ", colonieVille="
				+ colonieVille + "]";
	}

}
