package modeles;

import java.util.ArrayList;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import view.VueRoute;
import view.VueVille;

public class Route {
	
	private Point depart;
	private Point arrive;
	
	
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
	
	

}
