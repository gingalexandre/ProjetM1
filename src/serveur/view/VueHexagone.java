package serveur.view;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import serveur.modele.Hexagone;
import serveur.modele.service.HexagoneInterface;

public class VueHexagone extends Polygon {
	
	public HexagoneInterface hexagone;
	
	public final static String carriere 	= "/cases/carriere.png";
	public final static String champs 		= "/cases/champs.png";
	public final static String foret		= "/cases/foret.png";
	public final static String montagne 	= "/cases/montagne.png";
	public final static String prairie		= "/cases/plaine.png";
	public final static String dunevoleur 	= "/cases/dune.png"; 
	
	public VueHexagone(HexagoneInterface hexagone) throws RemoteException {
		super();
		this.hexagone = hexagone;
		this.getPoints().addAll(hexagone.getPoints());
		this.setFill(getPaint(hexagone.getType()));
		if(this.hexagone.getVOLEUR() == true){
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setSaturation(-1);
			this.setEffect(colorAdjust);
		}
	}
	
	public Paint getPaint(int id){
		switch(id){
		case Hexagone.CARRIERE:
			return new ImagePattern(new Image(getClass().getResource(carriere).toExternalForm()));
		case Hexagone.CHAMPS:
			return new ImagePattern(new Image(getClass().getResource(champs).toExternalForm()));
		case Hexagone.FORET:
			return new ImagePattern(new Image(getClass().getResource(foret).toExternalForm()));
		case Hexagone.MONTAGNE:
			return new ImagePattern(new Image(getClass().getResource(montagne).toExternalForm()));
		case Hexagone.PRAIRIE:
			return new ImagePattern(new Image(getClass().getResource(prairie).toExternalForm()));
		case Hexagone.DESERT:
			return new ImagePattern(new Image(getClass().getResource(dunevoleur).toExternalForm()));
		}
		return null;
		
	}
	
	public static VueHexagone[] transformVueHexagone(ArrayList<HexagoneInterface> hexagones) throws RemoteException{
		VueHexagone[] vueHexagones = new VueHexagone[hexagones.size()];
		for(int i = 0; i < hexagones.size(); i ++){
			vueHexagones[i] =  new VueHexagone(hexagones.get(i));
		}
		return vueHexagones;
	}

}
