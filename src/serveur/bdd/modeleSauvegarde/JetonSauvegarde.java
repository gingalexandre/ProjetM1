package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;

import serveur.modele.Point;
import serveur.modele.service.JetonInterface;

/**
 * Classe servant a convertir un JetonInterface en JetonSauvegarde pour la
 * sauvegarde de l'objet
 * 
 * @author Alexandre
 *
 */
public class JetonSauvegarde implements Serializable {
	/**
	 * Variable pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Point : emplacement du Jeton
	 */
	private Point emplacement;
	/**
	 * Integer : numéro du Jeton
	 */
	private int numeroJeton;

	/**
	 * Constructeur
	 * 
	 * @param jeton
	 *            : JetonInterface à convertir
	 * @throws RemoteException
	 */
	public JetonSauvegarde(JetonInterface jeton) throws RemoteException {
		super();
		if (jeton != null) {
			this.emplacement = jeton.getEmplacement();
			this.numeroJeton = jeton.getNumeroJeton();
		}
	}

	/**
	 * Getter de l'Emplacement
	 * 
	 * @return Point
	 */
	public Point getEmplacement() {
		return emplacement;
	}

	/**
	 * Setter de l'Emplacement
	 * 
	 * @param emplacement
	 *            Point
	 */
	public void setEmplacement(Point emplacement) {
		this.emplacement = emplacement;
	}

	/**
	 * Getter du numéro du Jeton
	 * 
	 * @return Integer
	 */
	public int getNumeroJeton() {
		return numeroJeton;
	}

	/**
	 * Setter du numéro du Jeton
	 * 
	 * @param numeroJeton
	 *            : Integer
	 */
	public void setNumeroJeton(int numeroJeton) {
		this.numeroJeton = numeroJeton;
	}

}
