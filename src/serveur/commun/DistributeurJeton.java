package serveur.commun;

import java.util.ArrayList;

public class DistributeurJeton {
	private static DistributeurJeton INSTANCE = null;
	private ArrayList<Integer> jetonsDispo;
	
	private DistributeurJeton(){
		
		// 1 : 2 - 12
		// 2 : reste sauf 7 (ou y en a pas)
		jetonsDispo = new ArrayList<>();
		jetonsDispo.add(2);
		jetonsDispo.add(12);
		for(int i =3; i < 12; i++){
			if(i!= 7){
				jetonsDispo.add(i);
				jetonsDispo.add(i);
			}
		}
		
	}
	
	public static synchronized DistributeurJeton getInstance(){			
		if (INSTANCE == null){ 	
			INSTANCE = new DistributeurJeton();	
		}
		return INSTANCE;
	}
	
	public int donnerJeton(){
		double d = Math.random()*jetonsDispo.size();
		int i = (int) d;
		int res = jetonsDispo.get(i);
		jetonsDispo.remove(i);
		return res;
	}
	
	
}
