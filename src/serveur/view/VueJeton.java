package serveur.view;

import java.rmi.RemoteException;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import serveur.modele.Jeton;
import serveur.modele.service.JetonInterface;

public class VueJeton extends Circle {

	public final static String UN = "/jetons/1.png";
	public final static String DEUX = "/jetons/2.png";
	public final static String TROIS = "/jetons/3.png";
	public final static String QUATRE = "/jetons/4.png";
	public final static String CINQ = "/jetons/5.png";
	public final static String SIX = "/jetons/6.png";
	public final static String HUIT = "/jetons/8.png";
	public final static String NEUF = "/jetons/9.png";
	public final static String DIX = "/jetons/10.png";
	public final static String ONZE = "/jetons/11.png";
	public final static String DOUZE = "/jetons/12.png";
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
			return new ImagePattern(new Image(getClass().getResource(UN).toExternalForm()));
		case Jeton.NUMERO2:
			return new ImagePattern(new Image(getClass().getResource(DEUX).toExternalForm()));
		case Jeton.NUMERO3:
			return new ImagePattern(new Image(getClass().getResource(TROIS).toExternalForm()));
		case Jeton.NUMERO4:
			return new ImagePattern(new Image(getClass().getResource(QUATRE).toExternalForm()));
		case Jeton.NUMERO5:
			return new ImagePattern(new Image(getClass().getResource(CINQ).toExternalForm()));
		case Jeton.NUMERO6:
			return new ImagePattern(new Image(getClass().getResource(SIX).toExternalForm()));
		case Jeton.NUMERO8:
			return new ImagePattern(new Image(getClass().getResource(HUIT).toExternalForm()));
		case Jeton.NUMERO9:
			return new ImagePattern(new Image(getClass().getResource(NEUF).toExternalForm()));
		case Jeton.NUMERO10:
			return new ImagePattern(new Image(getClass().getResource(DIX).toExternalForm()));
		case Jeton.NUMERO11:
			return new ImagePattern(new Image(getClass().getResource(ONZE).toExternalForm()));
		case Jeton.NUMERO12:
			return new ImagePattern(new Image(getClass().getResource(DOUZE).toExternalForm()));
		}
		return null;
	}
}
