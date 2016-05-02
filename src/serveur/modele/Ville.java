package serveur.modele;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javafx.scene.shape.Circle;
import serveur.bdd.modeleSauvegarde.VilleSauvegarde;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;
import serveur.view.VueVille;

public class Ville extends UnicastRemoteObject implements VilleInterface {

	private static final long serialVersionUID = 1L;

	/**
	 * Emplacement du point
	 */
	private Point emplacement;

	/**
	 * Villes adjacentes
	 */
	private int ville_adj1, ville_adj2, ville_adj3;

	/**
	 * Routes adjacentes
	 */
	private RouteInterface route_adj1, route_adj2, route_adj3;

	/**
	 * Joueur qui possède la ville
	 */
	private JoueurInterface oqp;

	/**
	 * Gain que procure la ville
	 */
	private int gain;
	
	/**
	 * Indique si la ville est une colonie
	 */
	private boolean colonieVille;

	/**
	 * Constructeur de la classe Ville
	 * @param emplacement
	 * @throws RemoteException
	 */
	public Ville(Point emplacement) throws RemoteException{
		this.emplacement = emplacement;
	}

	/**
	 * Constructeur pour la sauvegarde
	 * @param ville
	 * @throws RemoteException
	 */
	public Ville(VilleSauvegarde ville) throws RemoteException{
		this.emplacement = ville.getEmplacement();
		this.ville_adj1 = ville.getVille_adj1();
		this.ville_adj2 = ville.getVille_adj2();
		this.ville_adj3 = ville.getVille_adj3();
		this.route_adj1 = new Route(ville.getRoute_adj1());
		this.route_adj2 = new Route(ville.getRoute_adj2());
		// Dans le cas où la dernière est null suite aux contraintes du plateau
		if (ville.getRoute_adj3() != null) {
			this.route_adj3 = new Route(ville.getRoute_adj3());
		}
		else{
			this.route_adj3 = null;
		}
		if (ville.getville() != null) {
			this.oqp = new Joueur(ville.getville());
		} else {
			this.oqp = null;
		}
		this.gain = ville.getGain();
	}

	/**
	 * Constructeur vide pour la déserialisarion
	 * @throws RemoteException
	 */
	public Ville() throws RemoteException{}
	
	/**
	 * @return l'emplacement de la ville
	 * @throws RemoteException
	 */
	public Point getEmplacement() {
		return emplacement;
	}

	/**
	 * Indique si c'est possible de construire la ville
	 * @param proprio - proprietaire de la ville
	 * @param villes - liste des autres villes
	 * @return true si il est possible de construire la ville, false sinon
	 * @throws RemoteException
	 */
	public boolean estLibre(JoueurInterface proprio, ArrayList<VilleInterface> villes) throws RemoteException{
		return ((this.oqp == null)
				&& ((((this.ville_adj1 != -1) && (villes.get(this.ville_adj1).getOqp() == null) || this.ville_adj1 == -1))
						&& (((this.ville_adj2 != -1) && (villes.get(this.ville_adj2).getOqp() == null)|| this.ville_adj2 == -1))
						&& (((this.ville_adj3 != -1) && (villes.get(this.ville_adj3).getOqp() == null))|| this.ville_adj3 == -1))
				&& ((this.route_adj1.getOqp() == proprio) || (this.route_adj2.getOqp() == proprio)
						|| ((this.route_adj3==null) || (this.route_adj3.getOqp() == proprio))));
	}
	
	/**
	 * @return le joueur qui possede la ville
	 * @throws RemoteException
	 */
	public JoueurInterface getOqp() {
		return oqp;
	}
	
	/**
	 * @param j - joueur qui occupera la ville
	 * @throws RemoteException
	 */
	public void setOQP(JoueurInterface j) throws RemoteException{
		this.oqp = j;
	}

	/**
	 * @return la première ville adjacente
	 * @throws RemoteException
	 */
	public RouteInterface getRoute_adj1() {
		return route_adj1;
	}

	/**
	 * @return la deuxième ville adjacente
	 * @throws RemoteException
	 */
	public RouteInterface getRoute_adj2() {
		return route_adj2;
	}

	/**
	 * @return la troisième ville adjacente
	 * @throws RemoteException
	 */
	public RouteInterface getRoute_adj3() {
		return route_adj3;
	}
	
	/**
	 * @return la ville adjacente 1
	 * @throws RemoteException
	 */
	public int getVille_adj1() {
		return ville_adj1;
	}

	/**
	 * @return la ville adjacente 2
	 * @throws RemoteException
	 */
	public int getVille_adj2() {
		return ville_adj2;
	}

	/**
	 * @return la ville adjacente 3
	 * @throws RemoteException
	 */
	public int getVille_adj3() {
		return ville_adj3;
	}
	
	/**
	 * @param v1 - ville adjacente numero 1
	 * @param v2 - ville adjacente numero 2
	 * @param v3 - ville adjacente numero 3
	 * @throws RemoteException
	 */
	public void setVillesAdj(int v1, int v2, int v3) throws RemoteException{
		this.ville_adj1 = v1;
		this.ville_adj2 = v2;
		this.ville_adj3 = v3;
	}
	
	/**
	 * @return le gain de la ville
	 * @throws RemoteException
	 */
	public int getGain() {
		return gain;
	}

	/**
	 * @return si la colonie est une ville
	 * @throws RemoteException
	 */
	public boolean isVille() {
		return colonieVille;
	}
	
	/**
	 * Ajouter une route à une ville
	 * @param r - route à ajouter
	 * @throws RemoteException
	 */
	public void ajouterRoute(RouteInterface r) throws RemoteException {
		if (this.route_adj1 == null) {
			route_adj1 = r;
			return ;
		}
		if (this.route_adj2 == null) {
			route_adj2 = r;
			return ;
		}
		if (this.route_adj3 == null) {
			route_adj3 = r;
			return ;
		}
	}
	
	/**
	 * Transforme une colonie en ville
	 * @param j
	 * @throws RemoteException
	 */
	public void colonieToVille(JoueurInterface j) throws RemoteException{
		j.setNbColonie(j.getNbColonie() + 1);
		j.setNbVille(j.getNbVille() - 1);

		this.colonieVille = false;
	}
	public static Circle[] transformVilleVueVille(ArrayList<VilleInterface> villes) throws RemoteException {
		Circle[] vueVille = new Circle[villes.size()];
		for (int i = 0; i < villes.size(); i++) {
			vueVille[i] = new VueVille(villes.get(i).getEmplacement()).getCircle();
		}
		return vueVille;
	}

	@Override
	public String toString() {
		return "Ville [emplacement=" + emplacement + ", oqp=" + oqp + ", gain=" + gain + ", colonieVille="
				+ colonieVille + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
