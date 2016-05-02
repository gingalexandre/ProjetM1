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

	
	/**
	 * Constance correspondant aux numéros
	 */
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

	/**
	 * Emplacement du jeton
	 */
	private Point emplacement;

	/**
	 * Numéro du jeton
	 */
	private int numeroJeton;

	public Jeton() throws RemoteException{}
	
	/**
	 * Constructeur de la classe Jeton
	 * @param hexagone
	 * @throws RemoteException
	 */
	public Jeton(Hexagone hexagone) throws RemoteException{
		numeroJeton = DistributeurJeton.getInstance().donnerJeton();
		emplacement = hexagone.getCentre();
	}
	
	/**
	 * Constructeur de la sauvegarde
	 * @param jetonSauvegarde
	 * @throws RemoteException
	 */
	public Jeton(JetonSauvegarde jetonSauvegarde) throws RemoteException{
		numeroJeton = jetonSauvegarde.getNumeroJeton();
		emplacement = jetonSauvegarde.getEmplacement();
	}

	/**
	 * @return le numero du jeton
	 * @throws RemoteException
	 */
	public int getNumeroJeton() throws RemoteException{
		return this.numeroJeton;
	}

	/**
	 * @return l'emplacement du jeton
	 * @throws RemoteException
	 */
	public Point getEmplacement() throws RemoteException{
		return this.emplacement;
	}

	/**
	 * @return les points du jeton sous forme de tableau
	 * @throws RemoteException
	 */
	public Double[] getPoints() throws RemoteException{
		Double[] res = { emplacement.getX(), emplacement.getY() };
		return res;
	}

	/**
	 * Gère les vues des jetons
	 * @param jetons
	 * @return
	 * @throws RemoteException
	 */
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
