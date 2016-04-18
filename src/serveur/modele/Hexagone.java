package serveur.modele;

import java.io.Serializable;
import java.util.Arrays;

import serveur.commun.DistributeurType;
import org.codehaus.jackson.annotate.*;

public class Hexagone implements Serializable {

	private static final long serialVersionUID = 1L;

	private int indexHexagone;

	private Ressource ressource;
	private int numero;
	private Ville[] villeAdj = new Ville[6];

	public final static int FORET = 1;
	public final static int CHAMPS = 2;
	public final static int CARRIERE = 3;
	public final static int MONTAGNE = 4;
	public final static int PRAIRIE = 5;

	public final static int DESERT = 6;

	public boolean VOLEUR = false;

	private Point a;
	private Point b;
	private Point c;
	private Point d;
	private Point e;
	private Point f;

	private Point centre;

	private int type;

	private Jeton numeroJeton;

	
	public Hexagone(int indexHexagone, Point a, Point b, Point c, Point d, Point e, Point f, int type) {
		super();
		this.indexHexagone = indexHexagone;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.type = type;
	}

	public Point getB() {
		return b;
	}

	public Point getC() {
		return c;
	}

	public Point getE() {
		return e;
	}

	public Point getF() {
		return f;
	}

	public Point getA() {
		return a;
	}

	public Point getD() {
		return d;
	}

	public Hexagone(double xCentre, double yCentre, double size, int indexHexagone) {
		double x1 = xCentre;
		double y1 = yCentre + size;
		a = new Point(x1, y1);

		double x2 = xCentre + (Math.sqrt(3) / 2) * size;
		double y2 = yCentre + size / 2;
		b = new Point(x2, y2);

		double x3 = x2;
		double y3 = yCentre - size / 2;
		c = new Point(x3, y3);

		double x4 = x1;
		double y4 = yCentre - size;
		d = new Point(x4, y4);

		double x5 = xCentre - (Math.sqrt(3) / 2) * size;
		double y5 = y3;
		e = new Point(x5, y5);

		double x6 = x5;
		double y6 = y2;
		f = new Point(x6, y6);

		centre = new Point(xCentre, yCentre);

		this.indexHexagone = indexHexagone;

		if (this.indexHexagone != 9) {
			this.type = DistributeurType.getInstance().donnerType();
			this.numeroJeton = new Jeton(this);
		} else {
			this.type = Hexagone.DESERT;
			VOLEUR = true;
		}

	}

	public Double[] getPoints() {
		Double[] res = { a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY(), d.getX(), d.getY(), e.getX(),
				e.getY(), f.getX(), f.getY() };
		return res;
	}

	public int getIndexHexagone() {
		return indexHexagone;
	}

	public int getType() {
		return type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public int getNumero() {
		return numero;
	}

	public Ville[] getVilleAdj() {
		return villeAdj;
	}

	@Override
	public String toString() {
		return "Hexagone [indexHexagone=" + indexHexagone + ", ressource=" + ressource + ", numero=" + numero
				+ ", villeAdj=" + Arrays.toString(villeAdj) + ", a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", e="
				+ e + ", f=" + f + ", type=" + type + "]";
	}

	public Point getCentre() {
		return centre;
	}

	public Jeton getJeton() {
		return this.numeroJeton;
	}

	public boolean isVOLEUR() {
		return VOLEUR;
	}

	public void setVOLEUR(boolean VOLEUR) {
		this.VOLEUR = VOLEUR;
	}
}
