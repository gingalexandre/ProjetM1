package client.commun;

import javafx.scene.paint.Color;

public class Fonction {

	public static int getIndexMinValue(Double[] tab){
		if(tab == null || tab.length == 0) {return -1;} 
		int res = 0;
		for(int i=1;i<tab.length;i++){
			if(tab[i]<tab[res]){
				res = i;
			}
		}
		return res;
	}
	
	public static int getIndexMaxValue(Double[] tab){
		if (tab == null || tab.length == 0) { return -1;}
		int res = 0;
		for(int i=1;i<tab.length;i++){
			if(tab[i]>tab[res]){
				res = i;
			}
		}
		return res;
	}
	
	/**
	 * Obtient la couleur FXML adéquate en fonction d'une chaine de caractère
	 * @param couleur
	 * @return la couleur FXML correspondante
	 */
	public static Color getCouleurFromString(String couleur){
		switch(couleur){
			case "rouge":
				return Color.RED;
			case "bleu":
				return Color.BLUE;
			case "vert":
				return Color.GREEN;
			case "orange":
				return Color.ORANGE;
			default: 
				return Color.BLACK;
		}
	}
	
	public static String couleurEnAnglais(String couleurEnFrancais){
		switch(couleurEnFrancais){
			case "rouge":
				return "RED";
			case "bleu":
				return "BLUE";
			case "vert":
				return "GREEN";
			case "orange":
				return "ORANGE";
			default: 
				return "BLACK";
		}
	}
}
