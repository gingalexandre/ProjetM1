package client.modeles;

import java.util.ArrayList;

import client.view.VueVille;
import javafx.scene.shape.Circle;

public class Ville {
	
	private Point emplacement;
	
	//Comment d�finir les villes adjacentes � la cr�ation ?
	private Ville ville_adj1;
	private Ville ville_adj2;
	private Ville ville_adj3;
	private Route route_adj1;
	private Route route_adj2;
	private Route route_adj3;
	
	private Joueur oqp;
	
	private int gain;
	
	private boolean colonieVille;
	
	public Ville(Point emplacement){
		this.emplacement = emplacement;
	}


	public Point getEmplacement() {
		return emplacement;
	}
	
	public static Circle[] transformVilleVueVille(ArrayList<Ville> villes){
		Circle[] vueVille = new Circle[villes.size()];
		for(int i = 0; i < villes.size(); i ++){
			vueVille[i] =  new VueVille(villes.get(i).getEmplacement()).getCircle();
		}
		return vueVille;
	}

	
	//Probl�me ici :
	//Une ville n'a pas forc�ment 3 villes adjacentes (ex : les coins du plateau)
	//Donc on a une nullPointerException
	public boolean estLibre(Joueur proprio){
		return ((this.oqp == null)&&
				((this.ville_adj1.oqp == null)&&
				(this.ville_adj2.oqp == null)&&
				(this.ville_adj3.oqp == null))
				&&
				((this.route_adj1.getOqp() == null)&&
				(this.route_adj2.getOqp() == null)&&
				(this.route_adj3.getOqp() == null)));
	}

	
	
}