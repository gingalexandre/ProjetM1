package modeles;

import java.util.ArrayList;

import javafx.scene.shape.Circle;
import view.VueVille;

public class Ville {
	
	private Point emplacement;
	
	
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



	
	
}
