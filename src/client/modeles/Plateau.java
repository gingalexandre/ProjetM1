package client.modeles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Plateau {
	
	private static ArrayList<Hexagone> hexagones ;
	private static ArrayList<Point> points;
	private static ArrayList<Ville> villes ;
	private static ArrayList<Route> routes ;
	private static Plateau INSTANCE = null;
	
	private static final int SIZE = 60;
	
	private Plateau(){
		points = new ArrayList<Point>();
		hexagones = new ArrayList<Hexagone>(Arrays.asList(this.getAllHexagone()));
		setPoints();
		setVilles();
		setRoutes();
	}
	
	 public static ArrayList<Hexagone> getHexagones() {
		return hexagones;
	}

	public static ArrayList<Ville> getVilles() {
		return villes;
	}


	public static ArrayList<Route> getRoutes() {
		return routes;
	}

	public void setPoints() {
		points = new ArrayList<Point>();
		Set<Point> set = new HashSet<Point>() ;
		for (Hexagone hex : hexagones){
			set.add(hex.getA());
			set.add(hex.getB());
			set.add(hex.getC());
			set.add(hex.getD());
			set.add(hex.getE());
			set.add(hex.getF());
		}
		
        points = new ArrayList(set) ;
        
        Comparator c = new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return p1.compareTo(p2);
            }
        };
        points.sort(c);
        Collections.reverse(points);
	}
	
	public void setVilles(){
		villes = new ArrayList<Ville>();
		for (Point p : points){
			villes.add(new Ville(p));
		}
		
		//Affectation des villes adjacentes
		//Ligne 1 et 2
		villes.get(0).setVillesAdj(null, villes.get(4), villes.get(3));
		villes.get(1).setVillesAdj(null, villes.get(5), villes.get(4));
		villes.get(2).setVillesAdj(null, villes.get(6), villes.get(5));
		villes.get(3).setVillesAdj(null, villes.get(0), villes.get(7));
		villes.get(4).setVillesAdj(villes.get(0), villes.get(1), villes.get(8));
		villes.get(5).setVillesAdj(villes.get(1), villes.get(2), villes.get(9));
		villes.get(6).setVillesAdj(villes.get(2), null, villes.get(10));
		
		//Ligne 3 et 4
		
	}
	
	public void setRoutes(){
		routes = new ArrayList<Route>();
		for(Ville v : villes){
			if(v.getVilleAdj1() !=  null){
				routes.add(new Route(v.getEmplacement(),v.getVilleAdj1().getEmplacement()));
			}
			if(v.getVilleAdj2() !=  null){
				routes.add(new Route(v.getEmplacement(),v.getVilleAdj2().getEmplacement()));
			}
			if(v.getVilleAdj3() !=  null){
				routes.add(new Route(v.getEmplacement(),v.getVilleAdj3().getEmplacement()));
			}
		}
	}
	
	public synchronized static Plateau getInstance(){
		if (INSTANCE == null){ 	
			INSTANCE = new Plateau();	
		}
		return INSTANCE;
	}
	
	public Hexagone[] getAllHexagone() {
        Hexagone[] res = new Hexagone[19];
        /* CREATION DES HEXAGONES */
        int ligne = 0;
        int index = 0;
        double decalage = SIZE * 2 * Math.sqrt(3);
        for (int i = 0; i < 19; i++) {  
            Hexagone hex = new Hexagone(7.5 * SIZE + Math.sqrt(3) * index * SIZE - decalage, 1.5 * SIZE + ligne * 1.5 * SIZE, SIZE, i);
            res[i] = hex;
            if (i == 2 || i == 6) {
                ligne++;
                index = 0;
                decalage += SIZE * Math.sqrt(3) / 2;
                continue;
            }
            if (i == 11 || i == 15) {
                ligne++;
                index = 0;
                decalage -= SIZE * Math.sqrt(3) / 2;
                continue;
            }
            index++;

        }
        
        return res;
    }

}
