package serveur.modele;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import serveur.modele.service.HexagoneInterface;
import serveur.modele.service.JetonInterface;
import serveur.modele.service.PlateauInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;

public class Plateau extends UnicastRemoteObject implements PlateauInterface{

	private static final long serialVersionUID = 1L;

	private static Plateau INSTANCE = null;
	
	private ArrayList<HexagoneInterface> hexagones ;

	private ArrayList<Point> points;

	private ArrayList<VilleInterface> villes ;
	
	private ArrayList<RouteInterface> routes ;

	private ArrayList<JetonInterface> jetons ;

	public static final int SIZE = 60;
	
	private Plateau() throws RemoteException{
		points = new ArrayList<Point>();
		hexagones = new ArrayList<HexagoneInterface>(Arrays.asList(this.getAllHexagone()));
		setPoints();
		setVilles();
		setRoutes();
		setJetons();
	}
	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public static int getSize() {
		return SIZE;
	}



	public void setJetons() throws RemoteException{
		jetons = new ArrayList<JetonInterface>();
		for(HexagoneInterface hex : hexagones){
			jetons.add(hex.getJeton());
		}
	}

	public ArrayList<JetonInterface> getJetons() throws RemoteException{
		return jetons;
	}

	 public ArrayList<HexagoneInterface> getHexagones() throws RemoteException{
		return hexagones;
	}

	public ArrayList<VilleInterface> getVilles() throws RemoteException{
		return villes;
	}

	public ArrayList<RouteInterface> getRoutes() throws RemoteException{
		return routes;
	}

	public void setPoints() throws RemoteException {
		points = new ArrayList<Point>();
		Set<Point> set = new HashSet<Point>() ;
		for (HexagoneInterface hex : hexagones){
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
	
	public void setVilles() throws RemoteException{
		villes = new ArrayList<VilleInterface>();
		for (Point p : points){
			villes.add(new Ville(p));
		}
		
		//Affectation des villes adjacentes
		int i = 0;
		for (VilleInterface v : villes){
			if(i<=2)
				v.setVillesAdj(-1, i+4, i+3);
			else if ((i==4)||(i==5))
				v.setVillesAdj(i-4, i-3, i+4);
			else if ((i>=7)&&(i<=10))
				v.setVillesAdj(i-4, i+5, i+4);
			else if ((i>=12)&&(i<=14))
				v.setVillesAdj(i-5, i-4, i+5);
			else if ((i>=16)&&(i<=20))
				v.setVillesAdj(i-5, i+5, i+6);
			else if ((i>=22)&&(i<=25))
				v.setVillesAdj(i-6, i-5, i+6);
			else if ((i>=28)&&(i<=31))
				v.setVillesAdj(i-6, i+5, i+6);
			else if ((i>=33)&&(i<=37))
				v.setVillesAdj(i-6, i-5, i+5);
			else if ((i>=39)&&(i<=41))
				v.setVillesAdj(i-5, i+4, i+5);
			else if ((i>=43)&&(i<=46))
				v.setVillesAdj(i-5, i-4, i+4);
			else if ((i==48)||(i==49))
				v.setVillesAdj(i-4, i+3, i+4);
			else if (i>=51)
				v.setVillesAdj(i-4, i-3, -1);
			i++;
		}
		
		villes.get(3).setVillesAdj(-1,  -1, 7);
		villes.get(6).setVillesAdj(2, -1, 10);
		villes.get(11).setVillesAdj(-1, 7, 16);
	    villes.get(15).setVillesAdj(10, -1, 20);
		villes.get(21).setVillesAdj(-1, 16, 27);
		villes.get(26).setVillesAdj(20, -1, 32);
		villes.get(27).setVillesAdj(21, 33, -1);
		villes.get(32).setVillesAdj(26, -1, 37);
		villes.get(38).setVillesAdj(33, 43, -1);
		villes.get(42).setVillesAdj(37, -1, 46);
		villes.get(47).setVillesAdj(43, 51, -1);
		villes.get(50).setVillesAdj(46, -1, 53);
		
	}
	
	public void setRoutes() throws RemoteException{
		routes = new ArrayList<RouteInterface>();
		HashMap<Point,VilleInterface> toutesLesVilles = new HashMap();
		for(VilleInterface v : villes){
			toutesLesVilles.put(v.getEmplacement(), v);
			if(v.getVille_adj1() !=  -1){
				ajoutListeRoute(new Route(v.getEmplacement(),villes.get(v.getVille_adj1()).getEmplacement()));
			}
			if(v.getVille_adj2() !=  -1){
				ajoutListeRoute(new Route(v.getEmplacement(),villes.get(v.getVille_adj2()).getEmplacement()));
			}
			if(v.getVille_adj3() !=  -1){
				ajoutListeRoute(new Route(v.getEmplacement(),villes.get(v.getVille_adj3()).getEmplacement()));
			}
		}
		
		Comparator<RouteInterface> c = new Comparator<RouteInterface>(){
            @Override
            public int compare(RouteInterface r1, RouteInterface r2) {
                try {
					return r1.compareTo(r2);
				} catch (RemoteException e) {
					e.printStackTrace();
					return -100;
				}
            }
        };
        
        routes.sort(c);
        Collections.reverse(routes);
        
        //Ajout des Routes aux villes 
        for (RouteInterface r : routes){
        	toutesLesVilles.get(r.getDepart()).ajouterRoute(r);
        	toutesLesVilles.get(r.getArrive()).ajouterRoute(r);
        }
        
        
	}
	
	public void ajoutListeRoute(Route r) throws RemoteException{
		boolean same = false;
		for(RouteInterface ajoutees : routes){
			if(ajoutees.equals(r)){
				same = true;
			}
		}
		if(!same){
			routes.add(r);
		}
	}

	public static Plateau getInstance() throws RemoteException{
		if (INSTANCE == null){ 	
			INSTANCE = new Plateau();	
		}
		return INSTANCE;
	}
	

	public HexagoneInterface[] getAllHexagone() throws RemoteException {
        HexagoneInterface[] res = new Hexagone[19];
        /* CREATION DES HEXAGONES */
        int ligne = 0;
        int index = 0;
        double decalage = SIZE * 2 * Math.sqrt(3);
        for (int i = 0; i < 19; i++) {  
            HexagoneInterface hex = new Hexagone(8 * SIZE + Math.sqrt(3) * index * SIZE - decalage, 3 * SIZE + ligne * 1.5 * SIZE, SIZE, i);
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

	public ArrayList<Point> getPoints() throws RemoteException{
		return points;
	}
	

	public HexagoneInterface getVoleur() throws RemoteException{
		for(HexagoneInterface hex: hexagones) {
			if(hex.getVOLEUR() == true){
				return hex;
			}
		}
		return null;
	}
	
	@Override
	public int getRessourceCase(int caseConcernee) throws RemoteException {
		for(HexagoneInterface h : hexagones){
			if(h.getJeton().getNumeroJeton()==caseConcernee){
				//...??
				//return h.getRessource();
			}
		}
		return 0;
	}
	
	@Override
	public Ville[] getVilleAdjacenteByCase(Integer caseConsernee) throws RemoteException {
		for(HexagoneInterface h : hexagones){
			if(h.getJeton().getNumeroJeton()==caseConsernee){
				return h.getVilleAdj();
			}
		}
		return null;
	}
	
	
	@Override
	public String toString() {
		return "Plateau [hexagones=" + hexagones + ", points=" + points + ", villes=" + villes + ", routes=" + routes
				+ "]";
	}

	@Override
	public void ajoutListeRoute(RouteInterface r) throws RemoteException {
		
	}


}
