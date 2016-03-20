package client.modeles;

import java.util.ArrayList;

import client.view.VueVille;
import javafx.scene.shape.Circle;

public class Ville {
	
	private Point emplacement;
	
	//Comment définir les villes adjacentes à la création ?
	private Ville ville_adj1;
	private Ville ville_adj2;
	private Ville ville_adj3;
	private Route route_adj1;
	private Route route_adj2;
	private Route route_adj3;
	
	private Joueur oqp;
	
	private int gain;
	
	private boolean colonieVille;
	
	private static int nbColonieBleu = 5;
	private static int nbVilleBleu = 4;
	private static int nbColonieRouge = 5;
	private static int nbVilleRouge = 4;
	private static int nbColonieBlanc = 5;
	private static int nbVilleBlanc = 4;
	private static int nbColonieOrange = 5;
	private static int nbVilleOrange = 4;
	
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

	
	//Problème ici :
	//Une ville n'a pas forcément 3 villes adjacentes (ex : les coins du plateau)
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
	
	public boolean encoreAssezColonie(Joueur joueur){
		boolean assez = false;
		switch (joueur.getCouleur()) {
		case "Bleu": assez = (this.nbColonieBleu>0);
		case "Blanc": assez = (this.nbColonieBlanc>0);
		case "Rouge": assez = (this.nbColonieRouge>0);
		case "Orange": assez = (this.nbColonieOrange>0);
		default:
			break;
		}
		return assez;
	}

	public boolean encoreAssezVille(Joueur joueur){
		boolean assez = false;
		switch (joueur.getCouleur()) {
		case "Bleu": assez = (this.nbVilleBleu>0);
		case "Blanc": assez = (this.nbVilleBlanc>0);
		case "Rouge": assez = (this.nbVilleRouge>0);
		case "Orange": assez = (this.nbVilleOrange>0);
		default:
			break;
		}
		return assez;
	}
	
	
}
