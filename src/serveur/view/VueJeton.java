package serveur.view;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import serveur.modele.Jeton;
import serveur.modele.Point;
import serveur.modele.service.JetonInterface;

public class VueJeton extends Circle {

	public final static String UN = "file:Ressources/jetons/1.png";
	public final static String DEUX = "file:Ressources/jetons/2.png";
	public final static String TROIS = "file:Ressources/jetons/3.png";
	public final static String QUATRE = "file:Ressources/jetons/4.png";
	public final static String CINQ = "file:Ressources/jetons/5.png";
	public final static String SIX = "file:Ressources/jetons/6.png";
	public final static String HUIT = "file:Ressources/jetons/8.png";
	public final static String NEUF = "file:Ressources/jetons/9.png";
	public final static String DIX = "file:Ressources/jetons/10.png";
	public final static String ONZE = "file:Ressources/jetons/11.png";
	public final static String DOUZE = "file:Ressources/jetons/12.png";
	private Circle circle;

	public VueJeton(JetonInterface jeton) throws RemoteException {
		super();
		circle = new Circle(jeton.getEmplacement().getX(), jeton.getEmplacement().getY(), 20);
		circle.setFill(this.getPaint(jeton.getNumeroJeton()));
	}

	public Circle getCircle() {
		return this.circle;
	}

	public Paint getPaint(int id) {
		switch (id) {
		case Jeton.NUMERO1:
			return new ImagePattern(new Image(UN));
		case Jeton.NUMERO2:
			return new ImagePattern(new Image(DEUX));
		case Jeton.NUMERO3:
			return new ImagePattern(new Image(TROIS));
		case Jeton.NUMERO4:
			return new ImagePattern(new Image(QUATRE));
		case Jeton.NUMERO5:
			return new ImagePattern(new Image(CINQ));
		case Jeton.NUMERO6:
			return new ImagePattern(new Image(SIX));
		case Jeton.NUMERO8:
			return new ImagePattern(new Image(HUIT));
		case Jeton.NUMERO9:
			return new ImagePattern(new Image(NEUF));
		case Jeton.NUMERO10:
			return new ImagePattern(new Image(DIX));
		case Jeton.NUMERO11:
			return new ImagePattern(new Image(ONZE));
		case Jeton.NUMERO12:
			return new ImagePattern(new Image(DOUZE));
		}
		return null;
	}
}
