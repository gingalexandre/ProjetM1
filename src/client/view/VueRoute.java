package client.view;

import client.modeles.Point;
import javafx.scene.shape.Line;

public class VueRoute extends Line {

	private Point depart;
	private Point arrive;
	private Line route;
	
	public VueRoute(Point depart, Point arrive) {
		super();
		this.depart = depart;
		this.arrive = arrive;
		this.route = new Line(depart.getX(),depart.getY(), arrive.getX(), arrive.getY());
	}

	public Line getRoute() {
		return route;
	}
	
	
	
	
}
