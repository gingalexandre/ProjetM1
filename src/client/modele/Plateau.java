package client.modele;

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
		hexagones = new ArrayList<Hexagone>(Arrays.asList(this.getAllHexagone()));
		for(Hexagone hex : hexagones){
			setPoints(hex);
		}
		setRoutes();
		setVilles();
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

	 public void setRoutes() {
		 routes = new ArrayList<Route>();
		 	
	     // Construction des lignes sur diagonales
	     // Première série d'hexagone
	     routes.add(new Route(villes.get(2).getEmplacement(), villes.get(5).getEmplacement()));
	     routes.add(new Route(villes.get(2).getEmplacement(), villes.get(7).getEmplacement()));
	     routes.add(new Route(villes.get(7).getEmplacement(), villes.get(3).getEmplacement()));
	     routes.add(new Route(villes.get(3).getEmplacement(), villes.get(9).getEmplacement()));
	     routes.add(new Route(villes.get(9).getEmplacement(), villes.get(4).getEmplacement()));
	     routes.add(new Route(villes.get(4).getEmplacement(), villes.get(11).getEmplacement()));
	        
	     // Seconde série d'hexagone
	     routes.add(new Route(villes.get(13).getEmplacement(), villes.get(6).getEmplacement()));
	     routes.add(new Route(villes.get(6).getEmplacement(),  villes.get(15).getEmplacement()));
	     routes.add(new Route(villes.get(15).getEmplacement(), villes.get(8).getEmplacement()));
	     routes.add(new Route(villes.get(8).getEmplacement(),  villes.get(17).getEmplacement()));
	     routes.add(new Route(villes.get(17).getEmplacement(), villes.get(10).getEmplacement()));
	     routes.add(new Route(villes.get(10).getEmplacement(), villes.get(19).getEmplacement()));
	     routes.add(new Route(villes.get(19).getEmplacement(), villes.get(12).getEmplacement()));
	     routes.add(new Route(villes.get(12).getEmplacement(), villes.get(21).getEmplacement()));

	     // Troisième série d'hexagone
	     routes.add(new Route(villes.get(14).getEmplacement(), villes.get(23).getEmplacement()));
	     routes.add(new Route(villes.get(23).getEmplacement(), villes.get(16).getEmplacement()));
	     routes.add(new Route(villes.get(16).getEmplacement(), villes.get(25).getEmplacement()));
	     routes.add(new Route(villes.get(25).getEmplacement(), villes.get(18).getEmplacement()));
	     routes.add(new Route(villes.get(18).getEmplacement(), villes.get(27).getEmplacement()));
	     routes.add(new Route(villes.get(27).getEmplacement(), villes.get(20).getEmplacement()));
	     routes.add(new Route(villes.get(20).getEmplacement(), villes.get(29).getEmplacement()));
	     routes.add(new Route(villes.get(29).getEmplacement(), villes.get(22).getEmplacement()));
	     
	     // Quatrième série d'hexagone
	     routes.add(new Route(villes.get(24).getEmplacement(), villes.get(31).getEmplacement()));
	     routes.add(new Route(villes.get(31).getEmplacement(), villes.get(26).getEmplacement()));
	     routes.add(new Route(villes.get(26).getEmplacement(), villes.get(32).getEmplacement()));
	     routes.add(new Route(villes.get(32).getEmplacement(), villes.get(28).getEmplacement()));
	     routes.add(new Route(villes.get(28).getEmplacement(), villes.get(33).getEmplacement()));
	     routes.add(new Route(villes.get(33).getEmplacement(), villes.get(30).getEmplacement()));
 
	     // Construction des routes verticales
	     routes.add(new Route(villes.get(0).getEmplacement(), villes.get(7).getEmplacement()));
	     routes.add(new Route(villes.get(1).getEmplacement(), villes.get(9).getEmplacement()));
	     routes.add(new Route(villes.get(2).getEmplacement(), villes.get(15).getEmplacement()));
	     routes.add(new Route(villes.get(3).getEmplacement(), villes.get(17).getEmplacement()));
	     routes.add(new Route(villes.get(4).getEmplacement(), villes.get(19).getEmplacement()));
	     routes.add(new Route(villes.get(6).getEmplacement(), villes.get(6).getEmplacement()));
	     routes.add(new Route(villes.get(8).getEmplacement(), villes.get(25).getEmplacement()));
	     routes.add(new Route(villes.get(10).getEmplacement(), villes.get(27).getEmplacement()));
	     routes.add(new Route(villes.get(12).getEmplacement(), villes.get(29).getEmplacement()));
	     routes.add(new Route(villes.get(16).getEmplacement(), villes.get(31).getEmplacement()));
	     routes.add(new Route(villes.get(18).getEmplacement(), villes.get(32).getEmplacement()));
	     routes.add(new Route(villes.get(20).getEmplacement(), villes.get(33).getEmplacement()));
	     routes.add(new Route(villes.get(26).getEmplacement(), villes.get(34).getEmplacement()));
	     routes.add(new Route(villes.get(28).getEmplacement(),villes.get(35).getEmplacement()));
	       
	    }
	 

	public void setPoints(Hexagone hex) {
	        if ((hex.getIndexHexagone() == 1) || (hex.getIndexHexagone() == 2) || (hex.getIndexHexagone() == 5) || (hex.getIndexHexagone() == 6) || (hex.getIndexHexagone() == 7)) {
	            points.add((new Point(hex.getA().getX(),hex.getA().getY())));
	        } else if ((hex.getIndexHexagone() == 29) || (hex.getIndexHexagone() == 30) || (hex.getIndexHexagone() == 31) || (hex.getIndexHexagone() == 34) || (hex.getIndexHexagone() == 35)) {
	        	points.add((new Point(hex.getD().getX(),hex.getD().getY())));
	        } else if ((hex.getIndexHexagone() == 0) || (hex.getIndexHexagone() == 3) || (hex.getIndexHexagone() == 4) || (hex.getIndexHexagone() == 8) || (hex.getIndexHexagone() == 9) || (hex.getIndexHexagone() == 14) || (hex.getIndexHexagone() == 15) || (hex.getIndexHexagone() == 21) || (hex.getIndexHexagone() == 22) || (hex.getIndexHexagone() == 27) || (hex.getIndexHexagone() == 28) || (hex.getIndexHexagone() == 32) || (hex.getIndexHexagone() == 33) || (hex.getIndexHexagone() == 36)) {

	        } else {
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
        Hexagone[] res = new Hexagone[37];
        /* CREATION DES HEXAGONES */
        int ligne = 0;
        int index = 0;
        double decalage = SIZE * 2 * Math.sqrt(3);
        for (int i = 0; i < 37; i++) {  
            Hexagone hex = new Hexagone(7.5 * SIZE + Math.sqrt(3) * index * SIZE - decalage, 1.5 * SIZE + ligne * 1.5 * SIZE, SIZE, i);
            res[i] = hex;
            if (i == 3 || i == 8 || i == 14) {
                ligne++;
                index = 0;
                decalage += SIZE * Math.sqrt(3) / 2;
                continue;
            }
            if (i == 21 || i == 27 || i == 32) {
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
