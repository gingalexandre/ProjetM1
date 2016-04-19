package serveur.commun;
import java.util.ArrayList;

import serveur.modele.Hexagone;

public class DistributeurType {
	
	
	private static DistributeurType INSTANCE = null;
	private ArrayList<Integer> territoiredispo;
	
	private DistributeurType(){
		territoiredispo = new ArrayList<>();
		for (int i = 0; i<3;i++){
			territoiredispo.add(Hexagone.MONTAGNE);
			territoiredispo.add(Hexagone.CARRIERE);
			territoiredispo.add(Hexagone.CHAMPS);
			territoiredispo.add(Hexagone.FORET);
			territoiredispo.add(Hexagone.PRAIRIE);			
		}
		territoiredispo.add(Hexagone.CHAMPS);
		territoiredispo.add(Hexagone.FORET);
		territoiredispo.add(Hexagone.PRAIRIE);
	}
	
	public static synchronized DistributeurType getInstance(){			
		if (INSTANCE == null){ 	
			INSTANCE = new DistributeurType();	
		}
		return INSTANCE;
	}
	
	public int donnerType(){
		double d = Math.random()*territoiredispo.size();
		int i = (int) d;
		int res = territoiredispo.get(i);
		territoiredispo.remove(i);
		return res;
	}

	public ArrayList<Integer> getTerritoiredispo() {
		return territoiredispo;
	}
	
	
}
