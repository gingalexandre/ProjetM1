package modeles;

import java.util.ArrayList;
import java.util.Arrays;

import commun.Fonction;
import view.VueHexagone;

public class Plateau {
	
	private ArrayList<Hexagone> hexagones ;
	private ArrayList<Ville> villes ;
	private ArrayList<Route> routes ;
	private static Plateau INSTANCE = null;
	
	private static final int SIZE = 60;
	
	
	private Plateau(){	
		hexagones = new ArrayList<Hexagone>(Arrays.asList(this.getAllHexagone()));
		villes = new ArrayList<Ville>();
		for(Hexagone hex : hexagones){
			this.addConstructPoint(hex);
		}
		routes = new ArrayList<Route>();
		routes = getAllConstructRoute();
	}
	
	
	
	 public ArrayList<Hexagone> getHexagones() {
		return hexagones;
	}

	 


	public ArrayList<Ville> getVilles() {
		return villes;
	}



	public ArrayList<Route> getRoutes() {
		return routes;
	}

	 public ArrayList<Route> getAllConstructRoute() {

	        // Construction des lignes sur diagonales
	            // Première série d'hexagone
	        this.routes.add(new Route(villes.get(2).getEmplacement(), villes.get(5).getEmplacement()));
	        this.routes.add(new Route(villes.get(2).getEmplacement(), villes.get(7).getEmplacement()));
	        this.routes.add(new Route(villes.get(7).getEmplacement(), villes.get(3).getEmplacement()));
	        this.routes.add(new Route(villes.get(3).getEmplacement(), villes.get(9).getEmplacement()));
	        this.routes.add(new Route(villes.get(9).getEmplacement(), villes.get(4).getEmplacement()));
	        this.routes.add(new Route(villes.get(4).getEmplacement(), villes.get(11).getEmplacement()));
	        
	        // Seconde série d'hexagone
	        this.routes.add(new Route(villes.get(13).getEmplacement(), villes.get(6).getEmplacement()));
	        this.routes.add(new Route(villes.get(6).getEmplacement(),  villes.get(15).getEmplacement()));
	        this.routes.add(new Route(villes.get(15).getEmplacement(), villes.get(8).getEmplacement()));
	        this.routes.add(new Route(villes.get(8).getEmplacement(),  villes.get(17).getEmplacement()));
	        this.routes.add(new Route(villes.get(17).getEmplacement(), villes.get(10).getEmplacement()));
	        this.routes.add(new Route(villes.get(10).getEmplacement(), villes.get(19).getEmplacement()));
	        this.routes.add(new Route(villes.get(19).getEmplacement(), villes.get(12).getEmplacement()));
	        this.routes.add(new Route(villes.get(12).getEmplacement(), villes.get(21).getEmplacement()));

	        // Troisième série d'hexagone
	        this.routes.add(new Route(villes.get(14).getEmplacement(), villes.get(23).getEmplacement()));
	        this.routes.add(new Route(villes.get(23).getEmplacement(), villes.get(16).getEmplacement()));
	        this.routes.add(new Route(villes.get(16).getEmplacement(), villes.get(25).getEmplacement()));
	        this.routes.add(new Route(villes.get(25).getEmplacement(), villes.get(18).getEmplacement()));
	        this.routes.add(new Route(villes.get(18).getEmplacement(), villes.get(27).getEmplacement()));
	        this.routes.add(new Route(villes.get(27).getEmplacement(), villes.get(20).getEmplacement()));
	        this.routes.add(new Route(villes.get(20).getEmplacement(), villes.get(29).getEmplacement()));
	        this.routes.add(new Route(villes.get(29).getEmplacement(), villes.get(22).getEmplacement()));
	        // Quatrième série d'hexagone
	        this.routes.add(new Route(villes.get(24).getEmplacement(), villes.get(31).getEmplacement()));
	        this.routes.add(new Route(villes.get(31).getEmplacement(), villes.get(26).getEmplacement()));
	        this.routes.add(new Route(villes.get(26).getEmplacement(), villes.get(32).getEmplacement()));
	        this.routes.add(new Route(villes.get(32).getEmplacement(), villes.get(28).getEmplacement()));
	        this.routes.add(new Route(villes.get(28).getEmplacement(), villes.get(33).getEmplacement()));
	        this.routes.add(new Route(villes.get(33).getEmplacement(), villes.get(30).getEmplacement()));

	        // Construction des routes verticales
	        this.routes.add(new Route(villes.get(0).getEmplacement(), villes.get(7).getEmplacement()));
	        this.routes.add(new Route(villes.get(1).getEmplacement(), villes.get(9).getEmplacement()));
	        this.routes.add(new Route(villes.get(2).getEmplacement(), villes.get(15).getEmplacement()));
	        this.routes.add(new Route(villes.get(3).getEmplacement(), villes.get(17).getEmplacement()));
	        this.routes.add(new Route(villes.get(4).getEmplacement(), villes.get(19).getEmplacement()));
	        this.routes.add(new Route(villes.get(6).getEmplacement(), villes.get(6).getEmplacement()));
	        this.routes.add(new Route(villes.get(8).getEmplacement(), villes.get(25).getEmplacement()));
	        this.routes.add(new Route(villes.get(10).getEmplacement(), villes.get(27).getEmplacement()));
	        this.routes.add(new Route(villes.get(12).getEmplacement(), villes.get(29).getEmplacement()));
	        this.routes.add(new Route(villes.get(16).getEmplacement(), villes.get(31).getEmplacement()));
	        this.routes.add(new Route(villes.get(18).getEmplacement(), villes.get(32).getEmplacement()));
	        this.routes.add(new Route(villes.get(20).getEmplacement(), villes.get(33).getEmplacement()));
	        this.routes.add(new Route(villes.get(26).getEmplacement(), villes.get(34).getEmplacement()));
	        this.routes.add(new Route(villes.get(28).getEmplacement(),villes.get(35).getEmplacement()));
	        
	        return this.routes;
	    }


	public void addConstructPoint(Hexagone hex) {
	        if ((hex.getIndexHexagone() == 1) || (hex.getIndexHexagone() == 2) || (hex.getIndexHexagone() == 5) || (hex.getIndexHexagone() == 6) || (hex.getIndexHexagone() == 7)) {
	            villes.add(new Ville(new Point(hex.getA().getX(),hex.getA().getY())));
	        } else if ((hex.getIndexHexagone() == 29) || (hex.getIndexHexagone() == 30) || (hex.getIndexHexagone() == 31) || (hex.getIndexHexagone() == 34) || (hex.getIndexHexagone() == 35)) {
	        	villes.add(new Ville(new Point(hex.getD().getX(),hex.getD().getY())));
	        } else if ((hex.getIndexHexagone() == 0) || (hex.getIndexHexagone() == 3) || (hex.getIndexHexagone() == 4) || (hex.getIndexHexagone() == 8) || (hex.getIndexHexagone() == 9) || (hex.getIndexHexagone() == 14) || (hex.getIndexHexagone() == 15) || (hex.getIndexHexagone() == 21) || (hex.getIndexHexagone() == 22) || (hex.getIndexHexagone() == 27) || (hex.getIndexHexagone() == 28) || (hex.getIndexHexagone() == 32) || (hex.getIndexHexagone() == 33) || (hex.getIndexHexagone() == 36)) {

	        } else {
	        	villes.add(new Ville(new Point(hex.getD().getX(),hex.getD().getY())));
	        	villes.add(new Ville(new Point(hex.getA().getX(),hex.getA().getY())));
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
