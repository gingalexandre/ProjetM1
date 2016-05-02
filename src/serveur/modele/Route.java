package serveur.modele;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
		return ((a && (b2 || c2)) || (a && b && c && d ));
	}
	
	//public Route() throws RemoteException{}

	/**
	 * Methode pour savoir si une route est une extremité
	 * @param villes Map<Point,VilleInterface> donnant pour chaque point la ville a cette emplacement
	 * @return 1 : extremité sur le depart , -1 extremité sur l'arrivée, 0 route avec suite 
	 */
	public int isExtremite(HashMap<Point,VilleInterface> villes) throws RemoteException {
		JoueurInterface proprio = getOqp();
		VilleInterface vdep = villes.get(this.getDepart());
		VilleInterface varr = villes.get(this.getArrive());
		if (vdep.getOqp()!=null && !vdep.getOqp().equals(proprio)) return 1;
		if (varr.getOqp()!=null && !varr.getOqp().equals(proprio)) return -1;
		RouteInterface r1,r2,r3;
		r1 = vdep.getRoute_adj1();
		r2 = vdep.getRoute_adj2();
		r3 = vdep.getRoute_adj3();
		boolean r1PasAMoi = ((r1.getOqp()==null) || (r1.getOqp()!=null && !r1.getOqp().equals(proprio)));
		boolean r2PasAMoi = ((r2.getOqp()==null)||(r2.getOqp()!=null && !r2.getOqp().equals(proprio)));
		boolean r3PasAMoi = ((r3!=null && r3.getOqp()!=null && !r3.getOqp().equals(proprio)) || (r3==null));
		if (equals(r1) && r2PasAMoi && r3PasAMoi) return 1;
		if (equals(r2) && r1PasAMoi && r3PasAMoi) return 1;
		if (r3!=null && equals(r3) && r2PasAMoi && r1PasAMoi) return 1;
		r1 = varr.getRoute_adj1();
		r2 = varr.getRoute_adj2();
		r3 = varr.getRoute_adj3();
		r1PasAMoi = ((r1.getOqp()==null) || (r1.getOqp()!=null && !r1.getOqp().equals(proprio)));
		r2PasAMoi = ((r2.getOqp()==null) || (r2.getOqp()!=null && !r2.getOqp().equals(proprio)));
		r3PasAMoi = ((r3!=null && r3.getOqp()!=null && !r3.getOqp().equals(proprio)) || (r3==null) || (r3!=null && r3.getOqp()==null));
		if (equals(r1) && r2PasAMoi && r3PasAMoi) return -1;
		if (equals(r2) && r1PasAMoi && r3PasAMoi) return -1;
		if (r3!=null && equals(r3) && r2PasAMoi && r1PasAMoi) return -1;
		return 0;
	}

	public ArrayList<RouteInterface> getSuccesseurs(Point propagation, JoueurInterface j, HashMap<Point,VilleInterface> villes,Set<RouteInterface> visite) throws RemoteException {
		ArrayList<RouteInterface> res = new ArrayList<RouteInterface>();
		VilleInterface v = villes.get(propagation);
		RouteInterface r1 = v.getRoute_adj1();
		RouteInterface r2 = v.getRoute_adj2();
		RouteInterface r3 = v.getRoute_adj3();
		if (!equals(r1) && r1.getOqp()!=null && r1.getOqp().equals(j) && !visite.contains(r1)) res.add(r1);
		if (!equals(r2) && r2.getOqp()!=null && r2.getOqp().equals(j) && !visite.contains(r2)) res.add(r2);
		if (r3!=null && !equals(r3) && r3.getOqp()!=null && r3.getOqp().equals(j) && !visite.contains(r3)) res.add(r3);
		//if (v.getRoute_adj1().getOqp()!=null && v.getRoute_adj1().getOqp().equals(j) && !visite.contains(v.getRoute_adj1())) res.add(v.getRoute_adj1());
		//if (v.getRoute_adj2().getOqp()!=null && v.getRoute_adj2().getOqp().equals(j) && !visite.contains(v.getRoute_adj2())) res.add(v.getRoute_adj2());
		//if (v.getRoute_adj3()!= null && v.getRoute_adj3().getOqp()!=null && v.getRoute_adj3().getOqp().equals(j) && !visite.contains(v.getRoute_adj3())) res.add(v.getRoute_adj3());
		
		
		
		return res;
	}
}
