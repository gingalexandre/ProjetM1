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
	    points.add((new Point(hex.getD().getX(),hex.getD().getY())));
	    points.add((new Point(hex.getA().getX(),hex.getA().getY())));
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
