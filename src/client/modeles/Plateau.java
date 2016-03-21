package client.modeles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

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
        System.out.println(points.size());
        
        //Points qui posent problème à cause du type Double de Mathieu
        /*points.remove(0);
        points.remove(1);
        points.remove(4);
        points.remove(10);
        points.remove(12);
        points.remove(20);
        points.remove(21);
        points.remove(28);
        points.remove(4);*/
        
        Comparator c = new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return p1.compareTo(p2);
            }
        };
        points.sort(c);
        System.out.println(points.toString());
	}
	
	public void setVilles(){
		villes = new ArrayList<Ville>();
		for (Point p : points){
			villes.add(new Ville(p));
		}
		
		//Affectation des villes adjacentes
	}
	
	public void setRoutes(){
		//A implémenter
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
