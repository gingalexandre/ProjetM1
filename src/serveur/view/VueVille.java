package serveur.view;

import serveur.modele.Point;
import javafx.scene.shape.Circle;

public class VueVille extends Circle{

	private static final int SIZE = 3;
	private Point centre;
	private Circle circle;
	
	
	public VueVille(Point centre) {
		super();
		this.centre = centre;
		circle = new Circle(centre.getX(), centre.getY(), 4);
		
	}

	public Circle getCircle() {
		return this.circle;
	}
	
	
	
	
	
	
}
