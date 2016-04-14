package serveur.view;

import java.util.ArrayList;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import serveur.modele.Hexagone;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class VueHexagone extends Polygon {
	
	Hexagone hexagone;
	
	public final static String carriere 	= "file:Ressources/cases/carriere.png";
	public final static String champs 		= "file:Ressources/cases/champs.png";
	public final static String foret		= "file:Ressources/cases/foret.png";
	public final static String montagne 	= "file:Ressources/cases/montagne.png";
	public final static String prairie		= "file:Ressources/cases/plaine.png";
	public final static String dunevoleur 	= "file:Ressources/cases/dune.png"; 
	
	public VueHexagone(Hexagone hexagone) {
		super();
		this.hexagone = hexagone;
		this.getPoints().addAll(hexagone.getPoints());
		this.setFill(getPaint(hexagone.getType()));
		if(this.hexagone.isVOLEUR() == true){
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setSaturation(-1);
			this.setEffect(colorAdjust);
		}
	}
	
	public Paint getPaint(int id){
		switch(id){
		case Hexagone.CARRIERE:
			return new ImagePattern(new Image(carriere));
		case Hexagone.CHAMPS:
			return new ImagePattern(new Image(champs));
		case Hexagone.FORET:
			return new ImagePattern(new Image(foret));
		case Hexagone.MONTAGNE:
			return new ImagePattern(new Image(montagne));
		case Hexagone.PRAIRIE:
			return new ImagePattern(new Image(prairie));
		case Hexagone.DESERT:
			return new ImagePattern(new Image(dunevoleur));
		}
		return null;
		
	}
	
	public static VueHexagone[] transformVueHexagone(ArrayList<Hexagone> hexagones){
		VueHexagone[] vueHexagones = new VueHexagone[hexagones.size()];
		for(int i = 0; i < hexagones.size(); i ++){
			vueHexagones[i] =  new VueHexagone(hexagones.get(i));
		}
		return vueHexagones;
	}

}
