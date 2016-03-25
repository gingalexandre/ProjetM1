package service;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

/**
 * Enumération faisant le lien entre le nom d'une couleur et la couleur en JavaFX
 * @author jerome
 */
public enum StringCouleur{
	ROUGE("rouge", Color.RED),
	BLEU("bleu", Color.BLUE),
	VERT("vert", Color.GREEN),
	ORANGE("orange", Color.ORANGE);
	
	private final Color couleur;
	private final String representation;
	
	StringCouleur(String representation, Color couleur){
	    this.couleur = couleur;
	    this.representation = representation;
	}
	
	private static Map<String, StringCouleur> couleurs = new HashMap<String, StringCouleur>();

	/**
	 * Retourne la couleur
	 * @return la couleur
	 */
	public Color getCouleur(){
		return this.couleur;
	}
	
	/**
	 * Donne le StringCouleur correspondant à la représentation
	 * @param representation
	 * @return l'objet StringCouleur correspondant
	 */
	public static StringCouleur couleur(String representation) {
	    return couleurs.get(representation);
	}
	
	static {
	    for (StringCouleur stringCouleur : StringCouleur.values()) {
	    	couleurs.put(stringCouleur.representation, stringCouleur);
	    }
	}
}
