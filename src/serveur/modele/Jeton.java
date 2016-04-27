package serveur.modele;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javafx.scene.shape.Circle;
import serveur.bdd.modeleSauvegarde.JetonSauvegarde;
import serveur.commun.DistributeurJeton;
import serveur.modele.service.JetonInterface;
import serveur.view.VueJeton;

public class Jeton extends UnicastRemoteObject implements JetonInterface {

	private static final long serialVersionUID = 1L;

	public final static int NUMERO1 = 1;
	public final static int NUMERO2 = 2;
	public final static int NUMERO3 = 3;
	public final static int NUMERO4 = 4;
	public final static int NUMERO5 = 5;
	public final static int NUMERO6 = 6;
	public final static int NUMERO8 = 8;
	public final static int NUMERO9 = 9;
	public final static int NUMERO10 = 10;
	public final static int NUMERO11 = 11;
	public final static int NUMERO12 = 12;

	private Point emplacement;

	private int numeroJeton;

	public Jeton() throws RemoteException{}
	
	public Jeton(Hexagone hexagone) throws RemoteException{
		numeroJeton = DistributeurJeton.getInstance().donnerJeton();
		emplacement = hexagone.getCentre();
	}
	
	public Jeton(JetonSauvegarde jetonSauvegarde) throws RemoteException{
		numeroJeton = DistributeurJeton.getInstance().donnerJeton();
		emplacement = jetonSauvegarde.getEmplacement();
	}

	public int getNumeroJeton() throws RemoteException{
		return this.numeroJeton;
	}

	public Point getEmplacement() throws RemoteException{
		return this.emplacement;
	}

	public Double[] getPoints() throws RemoteException{
		Double[] res = { emplacement.getX(), emplacement.getY() };
		return res;
	}

	public static Circle[] transformVueJeton(ArrayList<JetonInterface> jetons) throws RemoteException{
		jetons.remove(null);
		Circle[] vueJetons = new Circle[jetons.size()];
		for (int i = 0; i < jetons.size(); i++) {
			if (jetons.get(i) != null) {
				vueJetons[i] = new VueJeton(jetons.get(i)).getCircle();
			}
		}
		return vueJetons;
	}
}
