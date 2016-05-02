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
	 * Point : emplacement du jeton
	 */
	private Point emplacement;
	
	/**
	 * Integer : numéro du jeton
	 */
	private int numeroJeton;

	/**
	 * Constructeur
	 * @param jeton - JetonInterface à convertir
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
	 * Constructeur vide pour la désérialisation
	 */
	public JetonSauvegarde() throws RemoteException {}

	/**
	 * Getter de l'emplacement
	 * @return Point
	 */
	public Point getEmplacement() {
		return emplacement;
	}

	/**
	 * Setter de l'emplacement
	 * @param emplacement - nouvel emplacement
	 */
	public void setEmplacement(Point emplacement) {
		this.emplacement = emplacement;
	}

	/**
	 * Getter du numéro du jeton
	 * @return Integer
	 */
	public int getNumeroJeton() {
		return numeroJeton;
	}

	/**
	 * Setter du numéro du jeton
	 * @param numeroJeton - nouveau numéro de jeton
	 */
	public void setNumeroJeton(int numeroJeton) {
		this.numeroJeton = numeroJeton;
	}

	/**
	 * Méthode equals
	 */
	public boolean equals(Object o) {
		return o instanceof JetonSauvegarde && ((JetonSauvegarde) o).emplacement.equals(this.emplacement)
				&& ((JetonSauvegarde) o).numeroJeton == this.numeroJeton;
	}

}
