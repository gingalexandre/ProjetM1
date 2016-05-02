package serveur.modele;

import java.io.Serializable;

public class Point implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Valeur en abscisse du point
	 */
	private double x;
	
	/**
	 * Valeur en ordonnée du point
	 */
	private double y;

	/**
	 * Constructeur de la classe point
	 * @param x - valeur en absisse
	 * @param y - valeur en ordonnée
	 */
	public Point(double x, double y) {
		super();
		this.x = ((double) ((int) x));
		this.y = ((double) ((int) y));
	}

	/**
	 * Constructeur vide pour la désérialisation
	 */
	public Point() {}

	/**
	 * @return la valeur en abscisse du point
	 */
	public double getX() {
		return x;
	}

	/**
	 * Permet de spécifier la valeur en abscisse du point
	 * @param x - nouvelle valeur en abscisse du point
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * @return la valeur en ordonnée du point
	 */
	public double getY() {
		return y;
	}

	/**
	 * Permet de spécifier la valeur en ordonnée du point
	 * @param y - nouvelle valeur en ordonnée du point
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		return ((int) x) * 3 + ((int) y) * 7;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	public String toString() {
		return "x:" + this.x + "-y:" + this.y + "\n";
	}

	public int compareTo(Point p) {
		if (this.y > p.y) {
			return -10;
		} else if ((this.y == p.y) && (this.x > p.x)) {
			return -10;
		} else if ((this.y == p.y) && (this.x < p.x)) {
			return 10;
		} else {
			return 0;
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
