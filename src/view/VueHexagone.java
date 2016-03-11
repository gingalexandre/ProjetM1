package view;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import modeles.Hexagone;

public class VueHexagone extends Polygon {
	
	Hexagone hexagone;
	
	
	public final static String carriere 	= "file:Ressources/carriere.jpg";
	public final static String champs 		= "file:Ressources/champs.jpg";
	public final static String foret		= "file:Ressources/foret.jpg";
	public final static String montagne 	= "file:Ressources/montagne.jpg";
	public final static String prairie		= "file:Ressources/prairie.jpg";
	public final static String mer 			= "file:Ressources/mer.png";
	public final static String dunevoleur 	= "file:Ressources/dune.png";
	public final static String load 		= "file:Ressources/loading.gif"; 
	

	public VueHexagone(Hexagone hexagone) {
		super();
		this.hexagone = hexagone;
		this.getPoints().addAll(hexagone.getPoints());
		this.setFill(getPaint(hexagone.getType()));
	}
	
	public Paint getPaint(int id){
		switch(id){
		case Hexagone.MER:
			return new ImagePattern(new Image(mer));
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
		default :
			return Color.RED;
		}
		
	}
	
	
	
	public static VueHexagone[] transformVueHexagone(ArrayList<Hexagone> hexagones){
		VueHexagone[] vueHexagones = new VueHexagone[hexagones.size()];
		for(int i = 0; i < hexagones.size(); i ++){
			vueHexagones[i] =  new VueHexagone(hexagones.get(i));
		}
		return vueHexagones;
	}
	
	
	
	

}
