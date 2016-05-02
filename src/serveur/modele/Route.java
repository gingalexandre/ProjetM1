package serveur.modele;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javafx.scene.shape.Line;
import serveur.bdd.modeleSauvegarde.RouteSauvegarde;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;
import serveur.view.VueRoute;

public class Route extends UnicastRemoteObject implements RouteInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Point de départ de la route
	 */
	private Point depart;
	
	/**
	 * Point d'arrivée de la route
	 */
	private Point arrive;
	
	/**
	 * Joueur qui possède la route
	 */
	private JoueurInterface oqp;
	
	/**
	 * Constructeur de la classe route
	 * @param depart
	 * @param arrive
	 * @throws RemoteException
	 */
	public Route(Point depart, Point arrive) throws RemoteException{
		super();
		this.depart = depart;
		this.arrive = arrive;
	}
	
	/**
	 * Constructeur pour la sauvegarde
	 * @param route
	 * @throws RemoteException
	 */
	public Route(RouteSauvegarde route)  throws RemoteException{
		this.depart = route.getDepart();
		this.arrive = route.getArrive();
		if (route.getOqp() != null) {
			this.oqp = new Joueur(route.getOqp());
		} else {
			this.oqp = null;
		}
	}

	/**
	 * Constructeur vide pour la désirialisation
	 * @throws RemoteException
	 */
	public Route() throws RemoteException{};
	
	/**
	 * @return le joueur qui occupe la route
	 * @throws RemoteException
	 */
	public JoueurInterface getOqp() throws RemoteException{
		return this.oqp;
	}
	
	/**
	 * Définit le joueur qui occupe la route
	 * @param j - joueur qui va occuper la route
	 * @throws RemoteException
	 */
	public void setOQP(JoueurInterface j) throws RemoteException{
		this.oqp = j;
	}
	
	/**
	 * @return le point de depart de la route
	 * @throws RemoteException
	 */
	public Point getDepart() throws RemoteException{
		return depart;
	}

	/**
	 * @return le point d'arrive de la route
	 * @throws RemoteException
	 */
	public Point getArrive() throws RemoteException{
		return arrive;
	}
	
	/**
	 * Méthode permettant de savoir si le joueur donnée en paramètre peut construire sur la route
	 * @param villes Table qui a chaque Point associe la Ville ou Colonie 
	 * @param joueurCourrant Joueur qui souhaite construire une route
	 * @param mesExtremitesDeRoute Ensemble des points de depart et d'arrivée des routes du joueur en parametre
	 * @return Booléen indiquant si oui ou non le joueur peut construire
	 */
	public boolean estConstructible(HashMap<Point,VilleInterface> villes, JoueurInterface joueurCourrant , HashSet<Point> mesExtremitesDeRoute, VilleInterface villeIgnored) throws RemoteException {
		// Verification si la route est libre
		boolean a = this.oqp == null;
		// Verification si la route a le depart ou l'arrivé qui a une colonie a moi 
		boolean b =  villes.get(depart).getOqp() == null ;
		boolean b2 = !villes.get(depart).equals(villeIgnored) && villes.get(depart).getOqp()!=null && villes.get(depart).getOqp().equals(joueurCourrant) ;
		boolean c =  villes.get(arrive).getOqp() == null;
		boolean c2 = !villes.get(arrive).equals(villeIgnored) && villes.get(arrive).getOqp()!=null && villes.get(arrive).getOqp().equals(joueurCourrant);
		// Verification si la route est la continuité d'une de mes routes
		boolean d = mesExtremitesDeRoute.contains(this.depart) || mesExtremitesDeRoute.contains(this.arrive);
		if (villeIgnored!=null){
			return ((a && (b2 || c2)) || (a && b && c && d ));
		} else {
			return a && (b2 || c2);
		}
	}
	
	/**
	 * Compare deux routes
	 * @param r2 - deuxième route à comparer
	 * @return -1, 0 ou 1
	 * @throws RemoteException
	 */
	public int compareTo(RouteInterface r) throws RemoteException{
		int pmx1 = ((int)(this.arrive.getX()+this.depart.getX())/2);
		int pmy1 = ((int)(this.depart.getY()+this.arrive.getY())/2);
		int pmx2 = ((int)(r.getArrive().getX()+r.getDepart().getX())/2);
	    int pmy2 = ((int)(r.getDepart().getY()+r.getArrive().getY())/2);
	    if(pmy1>pmy2){
	    	return -1;
	    }
	    else if (pmy1<pmy2){
	    	return 1;
	    }
	    else{
	    	if(pmx1>pmx2){
		    	return -1;
		    }
		    else if (pmx1<pmx2){
		    	return 1;
		    }
	    }
	    return 0;
	}

	public static Line[] transformRouteVueRoute(ArrayList<RouteInterface> routes) throws RemoteException{
		Line[] vueRoutes = new Line[routes.size()];
		for(int i = 0; i < routes.size(); i ++){
			vueRoutes[i] =  new VueRoute(routes.get(i).getDepart(),routes.get(i).getArrive()).getRoute();
		}
		return vueRoutes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrive == null) ? 0 : arrive.hashCode());
		result = prime * result + ((depart == null) ? 0 : depart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Route){
			Route r = (Route)obj;
			if(((this.depart.equals(r.depart))||(this.depart.equals(r.arrive)))&&(this.arrive.equals(r.arrive)||(this.arrive.equals(r.depart)))){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Route [depart=" + depart + ", arrive=" + arrive + ", oqp=" + oqp + "]";
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
