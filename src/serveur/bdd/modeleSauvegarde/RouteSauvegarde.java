package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;

import serveur.modele.Point;
import serveur.modele.service.RouteInterface;

/**
 * Classe servant a convertir un RouteInterface en RouteSauvegarde pour la sauvegarde de l'objet
 * @author Alexandre
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
	 * Joueur à qui appartient la route (null si personne)
	 */
	private JoueurSauvegarde oqp;

	/**
	 * Constructeur
	 * @param route - RouteInterface à convertir
	 * @throws RemoteException
	 */
	public RouteSauvegarde(RouteInterface route) throws RemoteException {
		super();
		this.depart = route.getDepart();
		this.arrive = route.getArrive();
		// Dans le cas où une route n'appartient à personne
		if (route.getOqp() != null) {
			this.oqp = new JoueurSauvegarde(route.getOqp());
		} else {
			this.oqp = null;
		}
	}

	/**
	 * Constructeur vide pour la désérialisation
	 */
	public RouteSauvegarde() throws RemoteException {}

	/**
	 * Getter du point de départ
	 * @return le point de départ
	 */
	public Point getDepart() {
		return depart;
	}

	/**
	 * Setter pour le point de départ
	 * @param depart - nouveau point de départ
	 */
	public void setDepart(Point depart) {
		this.depart = depart;
	}

	/**
	 * Getter du point d'arrivée
	 * @return le point d'arrivée
	 */
	public Point getArrive() {
		return arrive;
	}

	/**
	 * Setter pour le point d'arrivé
	 * @param arrive - nouveau point d'arrivée
	 */
	public void setArrive(Point arrive) {
		this.arrive = arrive;
	}

	/**
	 * Getter pour le joueur propriétaire de la route
	 * @return joueur qui occupe la route
	 */
	public JoueurSauvegarde getOqp() {
		return oqp;
	}

	/**
	 * Setter pour le joueur propriétaire de la route
	 * @param oqp - nouveau propriétaire
	 */
	public void setOqp(JoueurSauvegarde oqp) {
		this.oqp = oqp;
	}

	/**
	 * Méthode equals
	 */
	public boolean equals(Object o) {
		return o instanceof RouteSauvegarde && ((RouteSauvegarde) o).depart.equals(this.depart)
				&& ((RouteSauvegarde) o).arrive.equals(this.arrive);
	}
}
