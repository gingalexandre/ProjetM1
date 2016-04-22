package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;

import serveur.modele.Joueur;
import serveur.modele.Point;
import serveur.modele.service.RouteInterface;

/**
 * Classe servant a convertir un RouteInterface en RouteSauvegarde pour la
 * sauvegarde de l'objet
 * 
 * @author Alexandre
 *
 */
public class RouteSauvegarde implements Serializable {
	/**
	 * Variable pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Point départ de la route
	 */
	private Point depart;
	/**
	 * Point arrivée de la route
	 */
	private Point arrive;
	/**
	 * Joueur : à qui appartient la route (null si personne)
	 */
	private Joueur oqp;

	/**
	 * Constructeur
	 * 
	 * @param route
	 *            : RouteInterface à convertir
	 * @throws RemoteException
	 */
	public RouteSauvegarde(RouteInterface route) throws RemoteException {
		super();
		this.depart = route.getDepart();
		this.arrive = route.getArrive();
		// Dans le cas où une route n'appartient à personne
		if (route.getOqp() != null) {
			this.oqp = route.getOqp();
		} else {
			this.oqp = null;
		}
	}

	/**
	 * Getter du Point de départ
	 * 
	 * @return Point
	 */
	public Point getDepart() {
		return depart;
	}

	/**
	 * Setter pour le Point de départ
	 * 
	 * @param Point
	 *            depart
	 */
	public void setDepart(Point depart) {
		this.depart = depart;
	}

	/**
	 * Getter du Point d'arrivé
	 * 
	 * @return Point
	 */
	public Point getArrive() {
		return arrive;
	}

	/**
	 * Setter pour le Point d'arrivé
	 * 
	 * @param Point
	 *            arrive
	 */
	public void setArrive(Point arrive) {
		this.arrive = arrive;
	}

	/**
	 * Getter pour le Joueur propriétaire de la Route
	 * 
	 * @return Joueur
	 */
	public Joueur getOqp() {
		return oqp;
	}

	/**
	 * Setter pour le Joueur propriétaire de la Route
	 * 
	 * @param Joueur oqp
	 */
	public void setOqp(Joueur oqp) {
		this.oqp = oqp;
	}

}
