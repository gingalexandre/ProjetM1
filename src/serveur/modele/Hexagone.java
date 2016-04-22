package serveur.modele;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import serveur.commun.DistributeurType;
import serveur.modele.service.HexagoneInterface;
import serveur.modele.service.VilleInterface;

public class Hexagone extends UnicastRemoteObject implements HexagoneInterface {
	
	private static final long serialVersionUID = 1L;
	
	private int indexHexagone;
	
	private Ressource ressource;
	
	private int numero;
	
	private VilleInterface[] villeAdj = new Ville[6];
	
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

	
	public Hexagone(int indexHexagone, Point a, Point b, Point c, Point d, Point e, Point f, int type) throws RemoteException {
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
	
	public Hexagone(double xCentre, double yCentre, double size, int indexHexagone) throws RemoteException{
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
	
	public VilleInterface[] getVilleAdj() {
		return villeAdj;
	}
	


	
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
	
	public boolean getVOLEUR() {
		return VOLEUR;
	}

	public void setVOLEUR(boolean VOLEUR) {
		this.VOLEUR = VOLEUR;
	}


	
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Hexagone hexagone = (Hexagone) o;

		if (getIndexHexagone() != hexagone.getIndexHexagone()) return false;
		if (getNumero() != hexagone.getNumero()) return false;
		if (getVOLEUR() != hexagone.getVOLEUR()) return false;
		if (getType() != hexagone.getType()) return false;
		if (getRessource() != null ? !getRessource().equals(hexagone.getRessource()) : hexagone.getRessource() != null)
			return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(getVilleAdj(), hexagone.getVilleAdj())) return false;
		if (getA() != null ? !getA().equals(hexagone.getA()) : hexagone.getA() != null) return false;
		if (getB() != null ? !getB().equals(hexagone.getB()) : hexagone.getB() != null) return false;
		if (getC() != null ? !getC().equals(hexagone.getC()) : hexagone.getC() != null) return false;
		if (getD() != null ? !getD().equals(hexagone.getD()) : hexagone.getD() != null) return false;
		if (getE() != null ? !getE().equals(hexagone.getE()) : hexagone.getE() != null) return false;
		if (getF() != null ? !getF().equals(hexagone.getF()) : hexagone.getF() != null) return false;
		if (getCentre() != null ? !getCentre().equals(hexagone.getCentre()) : hexagone.getCentre() != null)
			return false;
		return numeroJeton != null ? numeroJeton.equals(hexagone.numeroJeton) : hexagone.numeroJeton == null;

	}


	
	public int hashCode() {
		int result = getIndexHexagone();
		result = 31 * result + (getRessource() != null ? getRessource().hashCode() : 0);
		result = 31 * result + getNumero();
		result = 31 * result + Arrays.hashCode(getVilleAdj());
		result = 31 * result + (getVOLEUR() ? 1 : 0);
		result = 31 * result + (getA() != null ? getA().hashCode() : 0);
		result = 31 * result + (getB() != null ? getB().hashCode() : 0);
		result = 31 * result + (getC() != null ? getC().hashCode() : 0);
		result = 31 * result + (getD() != null ? getD().hashCode() : 0);
		result = 31 * result + (getE() != null ? getE().hashCode() : 0);
		result = 31 * result + (getF() != null ? getF().hashCode() : 0);
		result = 31 * result + (getCentre() != null ? getCentre().hashCode() : 0);
		result = 31 * result + getType();
		result = 31 * result + (numeroJeton != null ? numeroJeton.hashCode() : 0);
		return result;
	}
	
	public Jeton getNumeroJeton() {
		return numeroJeton;
	}
	
	
}
