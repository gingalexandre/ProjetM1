package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonValue;

import serveur.modele.Route;
import serveur.modele.carte.ArmeePuissante;
import serveur.modele.carte.Chevalier;
import serveur.modele.carte.Invention;
import serveur.modele.carte.LongueRoute;
import serveur.modele.carte.Monopole;
import serveur.modele.carte.Victoire;
import serveur.modele.service.CarteInterface;

public class CarteSauvegarde implements Serializable {

	private String nom;

	private String chemin;

	private String type;

	private static final long serialVersionUID = 1L;

	public CarteSauvegarde(CarteInterface carte) throws RemoteException {
		super();
		this.nom = carte.getNom();
		this.chemin = carte.getCheminImage();

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

	public CarteSauvegarde() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object o) {
		return o instanceof CarteSauvegarde && ((CarteSauvegarde) o).nom.equals(this.nom)
				&& ((CarteSauvegarde) o).chemin.equals(this.chemin);
	}

}
