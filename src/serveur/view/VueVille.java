package serveur.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import serveur.modele.Point;

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
	
	public Circle getCircle(String couleur) {
		this.circle.setFill(Color.BLUE);
		return this.circle;
	}
	
	
	
	
}
