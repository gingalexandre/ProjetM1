package client.modeles;

import java.util.ArrayList;
import java.util.Arrays;

import client.commun.Fonction;
import client.view.VueHexagone;

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
		for(Hexagone hex : hexagones){
			setPoints(hex);
		}
		setVilles();
		//setRoutes();
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
	
	public void setVilles(){
		villes = new ArrayList<Ville>();
		for (Point p : points){
			villes.add(new Ville(p));
		}
		Ville port = new Ville(null);
		
		//Affectation des villes adjacentes
		villes.get(0).setVillesAdj(port, villes.get(5), villes.get(4));
	}


	public void setPoints(Hexagone hex) {
	    if((hex.getIndexHexagone() == 0)||(hex.getIndexHexagone() == 1)){
	    	points.add((new Point(hex.getA().getX(),hex.getA().getY())));
	    	points.add((new Point(hex.getC().getX(),hex.getC().getY())));
	    }
	    else if (hex.getIndexHexagone() == 2){
	    	points.add((new Point(hex.getA().getX(),hex.getA().getY())));
	    }
	    else if((hex.getIndexHexagone() == 16)||(hex.getIndexHexagone() == 17)){
	    	points.add((new Point(hex.getD().getX(),hex.getD().getY())));
	    	points.add((new Point(hex.getB().getX(),hex.getB().getY())));
	    }
	    else if (hex.getIndexHexagone() == 18){
	    	points.add((new Point(hex.getD().getX(),hex.getD().getY())));
	    }
	    else{
	    	points.add((new Point(hex.getD().getX(),hex.getD().getY())));
			points.add((new Point(hex.getA().getX(),hex.getA().getY())));
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
