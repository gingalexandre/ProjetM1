package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;

import serveur.modele.Route;
import serveur.modele.carte.ArmeePuissante;
import serveur.modele.carte.Chevalier;
import serveur.modele.carte.Invention;
import serveur.modele.carte.LongueRoute;
import serveur.modele.carte.Monopole;
import serveur.modele.carte.Victoire;
import serveur.modele.service.CarteInterface;

public class CarteSauvegarde implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Nom de la carte
	 */
	private String nom;

	/**
	 * Chemin de la carte
	 */
	private String chemin;

	/**
	 * Chemin de la carte
	 */
	private boolean utilisable;

	/**
	 * Type de la carte
	 */
	private String type;

	public CarteSauvegarde() {}
	
	/**
	 * Constructeur de la classe CarteSauvegarde
	 * @param carte
	 * @throws RemoteException
	 */
	public CarteSauvegarde(CarteInterface carte) throws RemoteException {
		super();
		this.nom = carte.getNom();
		this.chemin = carte.getCheminImage();
		this.utilisable = carte.getUtilisable();

		if (carte instanceof ArmeePuissante) {
			this.type = "ArmeePuissante";
		} else if (carte instanceof Chevalier) {
			this.type = "Chevalier";
		} else if (carte instanceof Invention) {
			this.type = "Invention";
		} else if (carte instanceof LongueRoute) {
			this.type = "LongueRoute";
		} else if (carte instanceof Monopole) {
			this.type = "Monopole";
		} else if (carte instanceof Route) {
			this.type = "Route";
		} else if (carte instanceof Victoire) {
			this.type = "Victoire";
		}

	}

	/**
	 * Getter du nom de la carte
	 * @return le nom de la carte
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter du nom de la carte
	 * @param nom - nouveau nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter du chemin de la carte
	 * @return le chemin de la carte
	 */
	public String getChemin() {
		return chemin;
	}

	/**
	 * Setter du chemin de la carte
	 * @param chemin - nouveau chemin
	 */
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	/**
	 * Getter du type de la carte
	 * @return le type de la carte
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter du type de la carte
	 * @param type - nouveau type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter de l'utilisabilité de la carte
	 * @return le boolean si utilisable ou non de la carte
	 */
	public boolean getUtilisable() {
		return utilisable;
	}

	/**
	 * de l'utilisabilité de la carte
	 * @param value le nouveau boolean d'utilisabilité ou non
	 */
	public void setType(boolean value) {
		this.utilisable = value;
	}

	public boolean equals(Object o) {
		return o instanceof CarteSauvegarde && ((CarteSauvegarde) o).nom.equals(this.nom)
				&& ((CarteSauvegarde) o).chemin.equals(this.chemin);
	}
}
