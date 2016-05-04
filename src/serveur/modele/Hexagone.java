package serveur.modele;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import serveur.bdd.modeleSauvegarde.HexagoneSauvegarde;
import serveur.commun.DistributeurType;
import serveur.commun.Fonctions;
import serveur.modele.service.HexagoneInterface;
import serveur.modele.service.VilleInterface;

public class Hexagone extends UnicastRemoteObject implements HexagoneInterface {

	private static final long serialVersionUID = 1L;

	/**
	 * Ibdex de l'hexagone
	 */
	private int indexHexagone;

	/**
	 * Ressource de l'hexagone
	 */
	private int ressource;

	/**
	 * Numéro de l'hexagone
	 */
	private int numero;

	/**
	 * Villes adjacentes à l'hexagone
	 */
	private ArrayList<VilleInterface> villeAdj = new ArrayList<VilleInterface>();

	/**
	 * Constantes correspondant à l'environnement de l'hexagone
	 */
	public final static int FORET = 1;

	public final static int CHAMPS = 2;

	public final static int CARRIERE = 3;

	public final static int MONTAGNE = 4;

	public final static int PRAIRIE = 5;

	public final static int DESERT = 6;

	/**
	 * Boolean indiquant si le voleur se trouve sur l'hexagone
	 */
	public boolean VOLEUR = false;

	/**
	 * Points de l'hexagone
	 */
	private Point a, b, c, d, e, f, centre;

	/**
	 * Type de l'hexagone
	 */
	private int type;

	/**
	 * Jeton correspondant
	 */
	private Jeton numeroJeton;

	/**
	 * Constructeur de la classe Hexagone
	 * 
	 * @param indexHexagone
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @param type
	 * @throws RemoteException
	 */
	public Hexagone(int indexHexagone, Point a, Point b, Point c, Point d, Point e, Point f, int type)
			throws RemoteException {
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

	/**
	 * Constructeur pour la sauvegarde
	 * 
	 * @param hex
	 * @throws RemoteException
	 */
	public Hexagone(HexagoneSauvegarde hex) throws RemoteException {
		this.indexHexagone = hex.getIndexHexagone();
		this.a = hex.getA();
		this.b = hex.getB();
		this.c = hex.getC();
		this.d = hex.getD();
		this.e = hex.getE();
		this.f = hex.getF();
		this.type = hex.getType();
		this.centre = hex.getCentre();
		this.numero = hex.getNumero();
		this.villeAdj = Fonctions.transformArrayVille(hex.getVilleAdj());
		this.VOLEUR = hex.isVOLEUR();
		if (hex.getNumeroJeton() != null) {
			this.numeroJeton = new Jeton(hex.getNumeroJeton());
		} else {
			this.numeroJeton = null;
		}
	}

	/**
	 * Constructeur de la classe Hexagone
	 * 
	 * @param xCentre
	 *            - coordonnées en abscisse du centre
	 * @param yCentre
	 *            - coordonnées en ordonnée du centre
	 * @param size
	 *            - taille de l'hexagone
	 * @param indexHexagone
	 *            - index de l'hexagone
	 * @throws RemoteException
	 */
	public Hexagone(double xCentre, double yCentre, double size, int indexHexagone) throws RemoteException {
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
		if (Plateau.getDifficulte() != null) {
			if (Plateau.getDifficulte().equals("Expert")) {
				if (this.indexHexagone != 9) {
					this.type = DistributeurType.getInstance().donnerType();
					this.numeroJeton = new Jeton(this);
					this.numero = this.numeroJeton.getNumeroJeton();
					setRessourceByType();
				} else {
					this.type = Hexagone.DESERT;
					VOLEUR = true;
				}
			} else {
				switch (this.indexHexagone) {
				case 0:
					this.type = FORET;
					this.numeroJeton = new Jeton(this, 6);
					break;
				case 1:
					this.type = PRAIRIE;
					this.numeroJeton = new Jeton(this, 3);
					break;
				case 2:
					this.type = PRAIRIE;
					this.numeroJeton = new Jeton(this, 8);
					break;
				case 3:
					this.type = CHAMPS;
					this.numeroJeton = new Jeton(this, 2);
					break;
				case 4:
					this.type = MONTAGNE;
					this.numeroJeton = new Jeton(this, 4);
					break;
				case 5:
					this.type = CHAMPS;
					this.numeroJeton = new Jeton(this, 5);
					break;
				case 6:
					this.type = FORET;
					this.numeroJeton = new Jeton(this, 10);
					break;
				case 7:
					this.type = FORET;
					this.numeroJeton = new Jeton(this, 5);
					break;
				case 8:
					this.type = CARRIERE;
					this.numeroJeton = new Jeton(this, 9);
					break;
				case 9:
					this.type = DESERT;
					this.VOLEUR = true;
					break;
				case 10:
					this.type = MONTAGNE;
					this.numeroJeton = new Jeton(this, 6);
					break;
				case 11:
					this.type = CHAMPS;
					this.numeroJeton = new Jeton(this, 9);
					break;
				case 12:
					this.type = CHAMPS;
					this.numeroJeton = new Jeton(this, 10);
					break;
				case 13:
					this.type = MONTAGNE;
					this.numeroJeton = new Jeton(this, 11);
					break;
				case 14:
					this.type = FORET;
					this.numeroJeton = new Jeton(this, 3);
					break;
				case 15:
					this.type = PRAIRIE;
					this.numeroJeton = new Jeton(this, 12);
					break;
				case 16:
					this.type = CARRIERE;
					this.numeroJeton = new Jeton(this, 8);
					break;
				case 17:
					this.type = PRAIRIE;
					this.numeroJeton = new Jeton(this, 4);
					break;
				case 18:
					this.type = CARRIERE;
					this.numeroJeton = new Jeton(this, 11);
					break;
				default:
					this.type = DistributeurType.getInstance().donnerType();
				}
			}
		}
	}

	public Hexagone() throws RemoteException {
	}

	/**
	 * @return le point A
	 * @throws RemoteException
	 */
	public Point getA() {
		return a;
	}

	/**
	 * @return le point B
	 * @throws RemoteException
	 */
	public Point getB() {
		return b;
	}

	/**
	 * @return le point C
	 * @throws RemoteException
	 */
	public Point getC() {
		return c;
	}

	/**
	 * @return le point D
	 * @throws RemoteException
	 */
	public Point getD() {
		return d;
	}

	/**
	 * @return le point E
	 * @throws RemoteException
	 */
	public Point getE() {
		return e;
	}

	/**
	 * @return le point F
	 * @throws RemoteException
	 */
	public Point getF() {
		return f;
	}

	/**
	 * Permet de mettre le type d'une ressource
	 */
	public void setRessourceByType() {
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

	/**
	 * @return tous les points
	 * @throws RemoteException
	 */
	public Double[] getPoints() {
		Double[] res = { a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY(), d.getX(), d.getY(), e.getX(),
				e.getY(), f.getX(), f.getY() };
		return res;
	}

	/**
	 * @return l'index de l'hexagone
	 * @throws RemoteException
	 */
	public int getIndexHexagone() {
		return indexHexagone;
	}

	/**
	 * @return le type de l'hexagone
	 * @throws RemoteException
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return la ressource associe à l'hexagone
	 * @throws RemoteException
	 */
	public int getRessource() {
		return ressource;
	}

	/**
	 * @return le numero de l'hexagone
	 * @throws RemoteException
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @return les villes adjacentres de l'hexagone sous forme de tableau
	 * @throws RemoteException
	 */
	public ArrayList<VilleInterface> getVilleAdj() {
		return villeAdj;
	}

	/**
	 * @return le centre de l'hexagone
	 * @throws RemoteException
	 */
	public Point getCentre() {
		return centre;
	}

	/**
	 * @return le jeton de l'hexagone
	 * @throws RemoteException
	 */
	public Jeton getJeton() {
		return this.numeroJeton;
	}

	/**
	 * @return true si le voleur est sur l'hexagone, false sinon
	 * @throws RemoteException
	 */
	public boolean getVOLEUR() {
		return VOLEUR;
	}

	/**
	 * @param VOLEUR
	 *            - true si le voleur est sur l'hexagone, false sinon
	 * @throws RemoteException
	 */
	public void setVOLEUR(boolean VOLEUR) {
		this.VOLEUR = VOLEUR;
	}

	/**
	 * @return le numéro du jeton
	 */
	public Jeton getNumeroJeton() {
		return numeroJeton;
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
		if (villeAdj == null) {
			if (other.villeAdj != null)
				return false;
		} else if (!villeAdj.equals(other.villeAdj))
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
		result = prime * result + ((villeAdj == null) ? 0 : villeAdj.hashCode());
		return result;
	}

	public String toString() {
		return "Hexagone [indexHexagone=" + indexHexagone + ", ressource=" + ressource + ", numero=" + numero
				+ ", villeAdj=" + villeAdj + ", a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", e=" + e + ", f=" + f
				+ ", type=" + type + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
