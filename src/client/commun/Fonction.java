package client.commun;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	 * Obtient la couleur FXML ad�quate en fonction d'une chaine de caract�re
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
	
	public static String couleurEnRGB(String couleurEnFrancais){
		switch(couleurEnFrancais){
			case "rouge":
				return "rgb(255,0,0,0.5)";
			case "bleu":
				return "rgb(0,0,255,0.5)";
			case "vert":
				return "rgb(0,255,0,0.5)";
			case "orange":
				return "rgb(255,150,0,0.5)";
			default: 
				return "BLACK";
		}
	}
	
	/**
	 * Méthode permettant de crypter un String en Entrée
	 * 
	 * @param entree
	 * @return String 
	 */
	public static String crypte(String entree) {
		MessageDigest md = null;
		try {
			// Utilisation du SHA-1 comme GIT
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new String(md.digest(entree.getBytes()));
	}
}
