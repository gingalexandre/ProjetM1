package serveur.modele;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import serveur.commun.DistributeurType;
import serveur.modele.service.HexagoneInterface;

public class Hexagone extends UnicastRemoteObject implements HexagoneInterface {
	
	private static final long serialVersionUID = 1L;
	
	private int indexHexagone;
	
	private int ressource;
	
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
			this.numero = this.numeroJeton.getNumeroJeton();
			setRessourceByType();
		} else {
			this.type = Hexagone.DESERT;
			VOLEUR = true;
		}

	}
	
	public void setRessourceByType(){
		switch (this.type) {
		case 1:
			this.ressource = Ressource.BOIS;
			break;
		case 2:
			this.ressource = Ressource.BLE;
			break;
		case 3:
			this.ressource = Ressource.ARGILE;
			break;
		case 4:
			this.ressource = Ressource.MINERAIE;
			break;
		case 5:
			this.ressource = Ressource.LAINE;
			break;

		default:
			break;
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
	
	public int getRessource() {
		return ressource;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public Ville[] getVilleAdj() {
		return villeAdj;
	}
	
	public void setVillesAdj(Ville v1, Ville v2, Ville v3, Ville v4, Ville v5, Ville v6){
		this.villeAdj[0] = v1;
		this.villeAdj[1] = v2;
		this.villeAdj[2] = v3;
		this.villeAdj[3] = v4;
		this.villeAdj[4] = v5;
		this.villeAdj[5] = v6;
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


	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hexagone other = (Hexagone) obj;
		if (VOLEUR != other.VOLEUR)
			return false;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (centre == null) {
			if (other.centre != null)
				return false;
		} else if (!centre.equals(other.centre))
			return false;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		if (e == null) {
			if (other.e != null)
				return false;
		} else if (!e.equals(other.e))
			return false;
		if (f == null) {
			if (other.f != null)
				return false;
		} else if (!f.equals(other.f))
			return false;
		if (indexHexagone != other.indexHexagone)
			return false;
		if (numero != other.numero)
			return false;
		if (numeroJeton == null) {
			if (other.numeroJeton != null)
				return false;
		} else if (!numeroJeton.equals(other.numeroJeton))
			return false;
		if (ressource != other.ressource)
			return false;
		if (type != other.type)
			return false;
		if (!Arrays.equals(villeAdj, other.villeAdj))
			return false;
		return true;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (VOLEUR ? 1231 : 1237);
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + ((centre == null) ? 0 : centre.hashCode());
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		result = prime * result + ((e == null) ? 0 : e.hashCode());
		result = prime * result + ((f == null) ? 0 : f.hashCode());
		result = prime * result + indexHexagone;
		result = prime * result + numero;
		result = prime * result + ((numeroJeton == null) ? 0 : numeroJeton.hashCode());
		result = prime * result + ressource;
		result = prime * result + type;
		result = prime * result + Arrays.hashCode(villeAdj);
		return result;
	}
	
	public Jeton getNumeroJeton() {
		return numeroJeton;
	}
	
	
}
