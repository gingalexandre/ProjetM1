package serveur.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Plateau implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Hexagone> hexagones ;
	private ArrayList<Point> points;
	private ArrayList<Ville> villes ;
	private ArrayList<Route> routes ;
	private static Plateau INSTANCE = null;
	private ArrayList<Jeton> jetons ;
	
	private static final int SIZE = 60;
	
	private Plateau(){
		points = new ArrayList<Point>();
		hexagones = new ArrayList<Hexagone>(Arrays.asList(this.getAllHexagone()));
		setPoints();
		setVilles();
		setRoutes();
		setJetons();
	}
	
	public void setJetons(){
		jetons = new ArrayList<Jeton>();
		for(Hexagone hex : hexagones){
			jetons.add(hex.getJeton());
		}
	}
	
	public ArrayList<Jeton> getJetons(){
		return jetons;
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
		
        points = new ArrayList<Point>(set) ;
        
        Comparator<Point> c = new Comparator<Point>() {
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
		int i = 0;
		for (Ville v : villes){
			if(i<=2)
				v.setVillesAdj(null, villes.get(i+4), villes.get(i+3));
			else if ((i==4)||(i==5))
				v.setVillesAdj(villes.get(i-4), villes.get(i-3), villes.get(i+4));
			else if ((i>=7)&&(i<=10))
				v.setVillesAdj(villes.get(i-4),villes.get(i+5), villes.get(i+4));
			else if ((i>=12)&&(i<=14))
				v.setVillesAdj(villes.get(i-5), villes.get(i-4), villes.get(i+5));
			else if ((i>=16)&&(i<=20))
				v.setVillesAdj(villes.get(i-5), villes.get(i+5), villes.get(i+6));
			else if ((i>=22)&&(i<=25))
				v.setVillesAdj(villes.get(i-6), villes.get(i-5), villes.get(i+6));
			else if ((i>=28)&&(i<=31))
				v.setVillesAdj(villes.get(i-6), villes.get(i+5), villes.get(i+6));
			else if ((i>=33)&&(i<=37))
				v.setVillesAdj(villes.get(i-6),villes.get(i-5),villes.get(i+5));
			else if ((i>=39)&&(i<=41))
				v.setVillesAdj(villes.get(i-5),villes.get(i+4),villes.get(i+5));
			else if ((i>=43)&&(i<=46))
				v.setVillesAdj(villes.get(i-5),villes.get(i-4),villes.get(i+4));
			else if ((i==48)||(i==49))
				v.setVillesAdj(villes.get(i-4),villes.get(i+3),villes.get(i+4));
			else if (i>=51)
				v.setVillesAdj(villes.get(i-4),villes.get(i-3),null);
			i++;
		}
		
		villes.get(3).setVillesAdj(null, villes.get(0), villes.get(7));
		villes.get(6).setVillesAdj(villes.get(2), null, villes.get(10));
		villes.get(11).setVillesAdj(null, villes.get(7), villes.get(16));
	    villes.get(15).setVillesAdj(villes.get(10), null, villes.get(20));
		villes.get(21).setVillesAdj(null, villes.get(16), villes.get(27));
		villes.get(26).setVillesAdj(villes.get(20), null, villes.get(32));
		villes.get(27).setVillesAdj(villes.get(21), villes.get(33), null);
		villes.get(32).setVillesAdj(villes.get(26), null, villes.get(37));
		villes.get(38).setVillesAdj(villes.get(33), villes.get(43), null);
		villes.get(42).setVillesAdj(villes.get(37), null, villes.get(46));
		villes.get(47).setVillesAdj(villes.get(43), villes.get(51), null);
		villes.get(50).setVillesAdj(villes.get(46), null, villes.get(53));
		
	}
	
	public void setRoutes(){
		routes = new ArrayList<Route>();
		for(Ville v : villes){
			if(v.getVilleAdj1() !=  null){
				ajoutListeRoute(new Route(v.getEmplacement(),v.getVilleAdj1().getEmplacement()));
			}
			if(v.getVilleAdj2() !=  null){
				ajoutListeRoute(new Route(v.getEmplacement(),v.getVilleAdj2().getEmplacement()));
			}
			if(v.getVilleAdj3() !=  null){
				ajoutListeRoute(new Route(v.getEmplacement(),v.getVilleAdj3().getEmplacement()));
			}
		}
		
		Comparator<Route> c = new Comparator<Route>() {
            @Override
            public int compare(Route r1, Route r2) {
                return r1.compareTo(r2);
            }
        };
        
        routes.sort(c);
        Collections.reverse(routes);
        
	}
	
	public void ajoutListeRoute(Route r){
		boolean same = false;
		for(Route ajoutees : routes){
			if(ajoutees.equals(r)){
				same = true;
			}
		}
		if(!same){
			routes.add(r);
		}
	}
	
	public static Plateau getInstance(){
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
            Hexagone hex = new Hexagone(8 * SIZE + Math.sqrt(3) * index * SIZE - decalage, 3 * SIZE + ligne * 1.5 * SIZE, SIZE, i);
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
