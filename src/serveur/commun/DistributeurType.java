package serveur.commun;
import java.util.ArrayList;

import serveur.modele.Hexagone;

public class DistributeurType {
	
	/**
	 * Instance de la classe
	 */
	private static DistributeurType INSTANCE = null;
	
	/**
	 * Comporte les territoires disponibles
	 */
	private ArrayList<Integer> territoiredispo;
	
	/**
	 * Constructeur privé de la classe
	 */
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
	
	/**
	 * Permet de récupérer l'instance de la classe
	 * @return
	 */
	public static synchronized DistributeurType getInstance(){			
		if (INSTANCE == null){ 	
			INSTANCE = new DistributeurType();	
		}
		return INSTANCE;
	}
	
	/**
	 * Donne le type du territoire
	 * @return le type du territoire
	 */
	public int donnerType(){
		double d = Math.random()*territoiredispo.size();
		int i = (int) d;
		int res = territoiredispo.get(i);
		territoiredispo.remove(i);
		return res;
	}

	/**
	 * Permet de récupérer la liste des territoires disponibles
	 * @return la liste des territoires disponibles
	 */
	public ArrayList<Integer> getTerritoiredispo() {
		return territoiredispo;
	}
}
