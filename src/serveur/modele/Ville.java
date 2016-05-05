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

	private Point emplacement;

	private int ville_adj1;

	private int ville_adj2;

	private int ville_adj3;

	private RouteInterface route_adj1;

	private RouteInterface route_adj2;

	private RouteInterface route_adj3;

	private JoueurInterface oqp;

	private int gain;
	
	/**
	 * Est à -1 si ce n'est pas un port
	 * Est à 0 si c'est un port sans ressource particulière
	 * sinon prend la valeur de la ressource
	 */
	private int port = -1;

	// Colonie : True & Ville : False
	private boolean colonieVille;

	public Ville(Point emplacement) throws RemoteException{
		this.emplacement = emplacement;
	}

	public Ville(VilleSauvegarde ville) throws RemoteException{
		this.emplacement = ville.getEmplacement();
		this.ville_adj1 = ville.getVille_adj1();
		this.ville_adj2 = ville.getVille_adj2();
		this.ville_adj3 = ville.getVille_adj3();
		this.route_adj1 = new Route(ville.getRoute_adj1());
		this.route_adj2 = new Route(ville.getRoute_adj2());
		this.colonieVille = ville.isColonieVille();
		this.port = ville.getPort1();
		// Dans le cas où la dernière est null suite aux contraintes du plateau
		if (ville.getRoute_adj3() != null) {
			this.route_adj3 = new Route(ville.getRoute_adj3());
		}
		else{
			this.route_adj3 = null;
		}
		if (ville.getVille() != null) {
			this.oqp = new Joueur(ville.getVille());
		} else {
			this.oqp = null;
		}
		this.gain = ville.getGain();
	}

	public Point getEmplacement() {
		return emplacement;
	}

	public static Circle[] transformVilleVueVille(ArrayList<VilleInterface> villes) throws RemoteException {
		Circle[] vueVille = new Circle[villes.size()];
		for (int i = 0; i < villes.size(); i++) {
			if(villes.get(i).isPort()==-1){
				vueVille[i] = new VueVille(villes.get(i).getEmplacement()).getCircle();
			}
			//si c'est un port
			else{
				vueVille[i] = new VueVille(villes.get(i).getEmplacement()).getCircle("Bleu");
			}
		}
		return vueVille;
	}

	public boolean estLibre(JoueurInterface proprio, ArrayList<VilleInterface> villes) throws RemoteException{
		return ((this.oqp == null)
				&& ((((this.ville_adj1 != -1) && (villes.get(this.ville_adj1).getOqp() == null) || this.ville_adj1 == -1))
						&& (((this.ville_adj2 != -1) && (villes.get(this.ville_adj2).getOqp() == null)|| this.ville_adj2 == -1))
						&& (((this.ville_adj3 != -1) && (villes.get(this.ville_adj3).getOqp() == null))|| this.ville_adj3 == -1))
				&& ((this.route_adj1.getOqp() == proprio) || (this.route_adj2.getOqp() == proprio)
						|| ((this.route_adj3==null) || (this.route_adj3.getOqp() == proprio))));
	}

	public void colonieToVille(JoueurInterface j) throws RemoteException{
		j.setNbColonie(j.getNbColonie() + 1);
		j.setNbVille(j.getNbVille() - 1);

		this.colonieVille = false;
	}

	public void setOQP(JoueurInterface j) throws RemoteException{
		this.oqp = j;
	}
	
	public int isPort(){
		return this.port;
	}

	public void setVillesAdj(int v1, int v2, int v3) throws RemoteException{
		this.ville_adj1 = v1;
		this.ville_adj2 = v2;
		this.ville_adj3 = v3;
	}

	public RouteInterface getRoute_adj1() {
		return route_adj1;
	}

	public RouteInterface getRoute_adj2() {
		return route_adj2;
	}

	public RouteInterface getRoute_adj3() {
		return route_adj3;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getVille_adj1() {
		return ville_adj1;
	}


	public int getVille_adj2() {
		return ville_adj2;
	}


	public int getVille_adj3() {
		return ville_adj3;
	}

	public JoueurInterface getOqp() {
		return oqp;
	}

	public int getGain() {
		return gain;
	}

	public boolean isColonie() {
		return colonieVille;
	}

	@Override
	public String toString() {
		return "Ville [emplacement=" + emplacement + ", oqp=" + oqp + ", gain=" + gain + ", colonieVille="
				+ colonieVille + "]";
	}

	@Override
	public void ajouterRoute(RouteInterface r) throws RemoteException {
		// TODO Auto-generated method stub
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
	
	public Ville() throws RemoteException{}

	@Override
	public void setPort() throws RemoteException {
		this.port = 0;		
	}

	@Override
	public void setPort(int ressource) throws RemoteException {
		this.port = ressource;
	}

	public boolean isColonieVille()  throws RemoteException{
		return colonieVille;
	}

	public void setColonieVille(boolean colonieVille) {
		this.colonieVille = colonieVille;
	}

	public int getPort()  throws RemoteException {
		return port;
	}
	
	@Override
	public void devientUneVille() throws RemoteException {
		// TODO Auto-generated method stub
		colonieVille = true;
	}

}
