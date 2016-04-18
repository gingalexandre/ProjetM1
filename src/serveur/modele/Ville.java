package serveur.modele;

import java.io.Serializable;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javafx.scene.shape.Circle;
import serveur.view.VueVille;

public class Ville implements Serializable {

	private static final long serialVersionUID = 1L;

	private Point emplacement;

	private Ville ville_adj1;

	private Ville ville_adj2;

	private Ville ville_adj3;

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

	public boolean estLibre(Joueur proprio) {
		return ((this.oqp == null)
				&& (((this.ville_adj1 != null) && (this.ville_adj1.oqp == null))
						&& ((this.ville_adj2 != null) && (this.ville_adj2.oqp == null))
						&& ((this.ville_adj3 != null) && (this.ville_adj3.oqp == null)))
				&& ((this.route_adj1.getOqp() == proprio) || (this.route_adj2.getOqp() == proprio)
						|| (this.route_adj3.getOqp() == proprio)));
	}

	public void colonieToVille(Joueur j) {
		j.setNbColonie(j.getNbColonie() + 1);
		j.setNbVille(j.getNbVille() - 1);
		this.colonieVille = false;
	}

	public void setOQP(Joueur j) {
		this.oqp = j;
	}

	public void setVillesAdj(Ville v1, Ville v2, Ville v3) {
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

	@JsonIgnore
	public Ville getVille_adj1() {
		return ville_adj1;
	}

	@JsonIgnore
	public Ville getVille_adj2() {
		return ville_adj2;
	}

	@JsonIgnore
	public Ville getVille_adj3() {
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
