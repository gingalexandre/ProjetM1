package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.commun.Fonctions;
import serveur.modele.Point;
import serveur.modele.service.HexagoneInterface;

/**
 * Classe servant a convertir un HexagoneInterface en HexagoneSauvegarde pour la
 * sauvegarde de l'objet
 * 
 * @author Alexandre
 *
 */
public class HexagoneSauvegarde implements Serializable {
	/**
	 * Variable pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Place dans la liste d'hexagone de l'hexagone
	 */
	private int indexHexagone;

	/**
	 * Ressource que produit l'hexagone
	 */
	private int ressource;
	/**
	 * Id de l'hexagone
	 */
	private int numero;
	/**
	 * Tableau de ville adjacente à l'hexagone
	 */
	private ArrayList<VilleSauvegarde> villeAdj = new ArrayList<VilleSauvegarde>();
	/**
	 * Booléen stockant le fait que le voleur est ou non placé sur l'hexagone
	 */
	public boolean VOLEUR = false;
	/**
	 * Point a pour le tracé de l'Hexagone
	 */
	private Point a;
	/**
	 * Point b pour le tracé de l'Hexagone
	 */
	private Point b;
	/**
	 * Point c pour le tracé de l'Hexagone
	 */
	private Point c;
	/**
	 * Point d pour le tracé de l'Hexagone
	 */
	private Point d;
	/**
	 * Point e pour le tracé de l'Hexagone
	 */
	private Point e;
	/**
	 * Point f pour le tracé de l'Hexagone
	 */
	private Point f;
	/**
	 * Centre pour le placement du Jeton de l'Hexagone
	 */
	private Point centre;
	/**
	 * Type de l'Hexagone
	 */
	private int type;
	/**
	 * Jeton positionné sur l'Hexagone
	 */
	private JetonSauvegarde numeroJeton;

	/**
	 * Constructeur
	 * 
	 * @param hex
	 *            : HexagoneInterface : HexagoneInterface à convertir
	 * @throws RemoteException
	 */
	public HexagoneSauvegarde(HexagoneInterface hex) throws RemoteException {
		super();
		this.indexHexagone = hex.getIndexHexagone();
		this.ressource = hex.getRessource();
		this.numero = hex.getNumero();
		this.villeAdj = Fonctions.transformArrayVilleSauvegarde(hex.getVilleAdj());
		VOLEUR = hex.getVOLEUR();
		this.a = hex.getA();
		this.b = hex.getB();
		this.c = hex.getC();
		this.d = hex.getD();
		this.e = hex.getE();
		this.f = hex.getF();
		this.centre = hex.getCentre();
		this.type = hex.getType();
		if (numeroJeton != null) {
			this.numeroJeton = new JetonSauvegarde(hex.getJeton());
		} else {
			this.numeroJeton = null;
		}
	}

	/**
	 * Getter de l'Index de l'Hexagone
	 * 
	 * @return l'index de l'Hexagone
	 */
	public int getIndexHexagone() {
		return indexHexagone;
	}

	/**
	 * Setter de l'index de l'Hexagone
	 * 
	 * @param indexHexagone
	 *            index de l'Hexagone
	 */
	public void setIndexHexagone(int indexHexagone) {
		this.indexHexagone = indexHexagone;
	}

	/**
	 * Getter de la Ressource de l'Hexagone
	 * 
	 * @return la Ressource de l'Hexagone
	 */
	public int getRessource() {
		return ressource;
	}

	/**
	 * Setter de la Ressource de l'Hexagone
	 * 
	 * @param ressource
	 *            Ressource de l'Hexagone
	 */
	public void setRessource(int ressource) {
		this.ressource = ressource;
	}

	/**
	 * Getter du numéro de l'Hexagone
	 * 
	 * @return numéro de l'Hexagone
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Setter du numéro de l'Hexagone
	 * 
	 * @param numero
	 *            : numéro de l'Hexagone
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Getter des villes Adjacentes à l'Hexagone
	 * 
	 * @return Tableau de Ville de l'Hexagone
	 */
	public ArrayList<VilleSauvegarde> getVilleAdj() {
		return villeAdj;
	}

	/**
	 * Setter des villes Adjacentes à l'Hexagone
	 * 
	 * @param villeAdj
	 *            Tableau de Ville de l'Hexagone
	 */
	public void setVilleAdj(ArrayList<VilleSauvegarde> villeAdj) {
		this.villeAdj = villeAdj;
	}

	/**
	 * Getter du booléen pour savoir si le voleur est sur l'Hexagone
	 * 
	 * @return booléen
	 */
	public boolean isVOLEUR() {
		return VOLEUR;
	}

	/**
	 * Setter du booléen pour savoir si le voleur est sur l'Hexagone
	 * 
	 * @param VOLEUR
	 *            booléen
	 */
	public void setVOLEUR(boolean VOLEUR) {
		this.VOLEUR = VOLEUR;
	}

	/**
	 * Getter du Point A
	 * 
	 * @return Point
	 */
	public Point getA() {
		return a;
	}

	/**
	 * Setter du Point A
	 * 
	 * @param Point
	 */
	public void setA(Point a) {
		this.a = a;
	}

	/**
	 * Getter du Point B
	 * 
	 * @return Point
	 */
	public Point getB() {
		return b;
	}

	/**
	 * Setter du Point B
	 * 
	 * @param Point
	 */
	public void setB(Point b) {
		this.b = b;
	}

	/**
	 * Getter du Point C
	 * 
	 * @return Point
	 */
	public Point getC() {
		return c;
	}

	/**
	 * Setter du Point C
	 * 
	 * @param Point
	 */
	public void setC(Point c) {
		this.c = c;
	}

	/**
	 * Getter du Point D
	 * 
	 * @return Point
	 */
	public Point getD() {
		return d;
	}

	/**
	 * Setter du Point D
	 * 
	 * @param Point
	 */
	public void setD(Point d) {
		this.d = d;
	}

	/**
	 * Getter du Point E
	 * 
	 * @return Point
	 */
	public Point getE() {
		return e;
	}

	/**
	 * Setter du Point E
	 * 
	 * @param Point
	 */
	public void setE(Point e) {
		this.e = e;
	}

	/**
	 * Getter du Point F
	 * 
	 * @return Point
	 */
	public Point getF() {
		return f;
	}

	/**
	 * Setter du Point F
	 * 
	 * @param Point
	 */
	public void setF(Point f) {
		this.f = f;
	}

	/**
	 * Getter du Point Central
	 * 
	 * @return Point
	 */
	public Point getCentre() {
		return centre;
	}

	/**
	 * Setter du Point Centre
	 * 
	 * @param Point
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}

	/**
	 * Getter du Type de l'Hexagone
	 * 
	 * @return int
	 */
	public int getType() {
		return type;
	}
	/**
	 * Setter du Type de l'Hexagone
	 * 
	 * @param int
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * Getter du Jeton 
	 * 
	 * @return JetonSauvegarde
	 */
	public JetonSauvegarde getNumeroJeton() {
		return numeroJeton;
	}
	/**
	 * Setter du Jeton 
	 * 
	 * @param JetonSauvegarde
	 */
	public void setNumeroJeton(JetonSauvegarde numeroJeton) {
		this.numeroJeton = numeroJeton;
	}
	/**
	 * Getter du Serialversionuid pour la sérialisation 
	 * 
	 * @return long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
