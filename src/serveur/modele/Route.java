package serveur.modele;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.shape.Line;
import serveur.view.VueRoute;

public class Route implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Point depart;
	private Point arrive;
	private Joueur oqp;
	
	public Route(Point depart, Point arrive) {
		super();
		this.depart = depart;
		this.arrive = arrive;
	}
	
	public static Line[] transformRouteVueRoute(ArrayList<Route> routes){
		Line[] vueRoutes = new Line[routes.size()];
		for(int i = 0; i < routes.size(); i ++){
			vueRoutes[i] =  new VueRoute(routes.get(i).depart,routes.get(i).arrive).getRoute();
		}
		return vueRoutes;
	}
	
	public Joueur getOqp(){
		return this.oqp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrive == null) ? 0 : arrive.hashCode());
		result = prime * result + ((depart == null) ? 0 : depart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Route){
			Route r = (Route)obj;
			if(((this.depart.equals(r.depart))||(this.depart.equals(r.arrive)))&&(this.arrive.equals(r.arrive)||(this.arrive.equals(r.depart)))){
				return true;
			}
		}
		return false;
	}
	
	public int compareTo(Route r){
		int pmx1 = ((int)(this.arrive.x+this.depart.x)/2);
		int pmy1 = ((int)(this.depart.y+this.arrive.y)/2);
		int pmx2 = ((int)(r.arrive.x+r.depart.x)/2);
	    int pmy2 = ((int)(r.depart.y+r.arrive.y)/2);
	    if(pmy1>pmy2){
	    	return -10;
	    }
	    else if (pmy1<pmy2){
	    	return 10;
	    }
	    else{
	    	if(pmx1>pmx2){
		    	return -10;
		    }
		    else if (pmx1<pmx2){
		    	return 10;
		    }
	    }
	    return 0;
	}
	
	public void setOQP(Joueur j){
		this.oqp = j;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Point getDepart() {
		return depart;
	}

	public Point getArrive() {
		return arrive;
	}
	
	

}
