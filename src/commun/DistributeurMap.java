package commun;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class DistributeurMap {
	public final static String carriere 	= "file:Ressources/cases/carriere.png";
	public final static String champs 		= "file:Ressources/cases/champs.png";
	public final static String foret		= "file:Ressources/cases/foret.png";
	public final static String montagne 	= "file:Ressources/cases/montagne.png";
	public final static String prairie		= "file:Ressources/cases/plaine.png";
	public final static String mer 			= "file:Ressources/cases/mer.png";
	public final static String dunevoleur 	= "file:Ressources/cases/dune.png";
	public final static String load 		= "file:Ressources/cases/loading.gif"; 
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
