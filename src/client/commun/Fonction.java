package client.commun;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javafx.scene.paint.Color;

public class Fonction {

	/**
	 * Obtient l'index de la plus petite valeur d'un tableau
	 * @param tab - tableau sur lequel est appliqué la méthode
	 * @return l'index de la plus petite valeur d'un tableau
	 */
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
	
	/**
	 * Obtient l'index de la plus grande valeur d'un tableau
	 * @param tab - tableau sur lequel est appliqué la méthode
	 * @return l'index de la plus grande valeur d'un tableau
	 */
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
	 * Fonction permettant de vérifier les int lors de la saisie du sucre
	 * @param entree - Phrase à afficher au début et lors d'une mauvaise saisie
	 * @param borneMin - int : borne minimum de la saisie
	 * @param borneMax - int : borne maximum à ne pas dépasser pour la saisie
	 * @return l'int saisi et vérifié
	 */
	public static boolean verificationNombre(String entree, int borneMin, int borneMax) {
		int res = -1;
		Scanner sc = null;
		if (res <= borneMin || res > borneMax) {
			try {
				sc = new Scanner(entree);
				res = sc.nextInt();
				sc.close();
				return true;
			} catch (Exception e) {
				sc.close();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Fonction permettant la vérification des entrées String
	 * @param entree - String : Phrase a afficher au début et lors d'une mauvaise saisie
	 * @return String vérifié.
	 */
	public static String verificationString(String entree) {
		String res = "";
		Scanner sc = null;
		while (res.length() == 0 || res.length() > 50 || res.matches("^\\p{Digit}+$")) {
			try {
				sc = new Scanner(System.in);
				res = sc.nextLine();
				sc.close();
			} catch (Exception e) {
				sc.close();
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
				return Color.DEEPSKYBLUE;
			case "vert":
				return Color.GREEN;
			case "orange":
				return Color.ORANGE;
			default: 
				return Color.BLACK;
		}
	}
	
	/**
	 * Permet de récupérer les valeurs RGB sous forme de string d'une couleur
	 * @param couleurEnFrancais
	 * @return les valeurs RGB de la couleur en parametre
	 */
	public static String couleurEnRGB(String couleurEnFrancais){
		switch(couleurEnFrancais){
			case "rouge":
				return "rgb(255,0,0,0.5)";
			case "bleu":
				return "rgb(0,178,238,0.5)";
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
