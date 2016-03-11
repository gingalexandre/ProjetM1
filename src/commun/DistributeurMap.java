package commun;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class DistributeurMap {
	public final static String carriere 	= "file:Ressources/carriere.jpg";
	public final static String champs 		= "file:Ressources/champs.jpg";
	public final static String foret		= "file:Ressources/foret.jpg";
	public final static String montagne 	= "file:Ressources/montagne.jpg";
	public final static String prairie		= "file:Ressources/prairie.jpg";
	public final static String mer 			= "file:Ressources/mer.png";
	public final static String dunevoleur 	= "file:Ressources/dune.png";
	public final static String load 		= "file:Ressources/loading.gif"; 
	private static DistributeurMap INSTANCE = null;
	private ArrayList<Image> territoiredispo;
	
	private DistributeurMap(){
		territoiredispo = new ArrayList<>();
		for (int i = 0; i<3;i++){
			territoiredispo.add(new Image(montagne));
			territoiredispo.add(new Image(carriere));
			territoiredispo.add(new Image(champs));
			territoiredispo.add(new Image(foret));
			territoiredispo.add(new Image(prairie));			
		}
		territoiredispo.add(new Image(foret));
		territoiredispo.add(new Image(prairie));
		territoiredispo.add(new Image(champs));
	}
	
	public static synchronized DistributeurMap getInstance(){			
		if (INSTANCE == null){ 	
			INSTANCE = new DistributeurMap();	
		}
		return INSTANCE;
	}
	
	public Image donnerImage(){
		double d = Math.random()*territoiredispo.size();
		int i = (int) d;
		Image res = territoiredispo.get(i);
		territoiredispo.remove(i);
		return res;
	}
	
	
}
