package serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 
 * @author jerome
 */
public class ConnexionManager {
	
	private static ConnexionManager INSTANCE = null;
	
	private Serveur serveur;
	
	private ConnexionManager(){
		String serveurURL = "rmi://127.0.0.1:42000/serveur";
		try {
			this.serveur = (Serveur) Naming.lookup(serveurURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ConnexionManager getInstance(){
		if (INSTANCE == null){ 	
			INSTANCE = new ConnexionManager();	
		}
		return INSTANCE;
	}
	
	public Serveur getServeur(){
		return this.serveur;
	}
	
	public static Serveur getStaticServeur(){
		return ConnexionManager.getInstance().getServeur();
	}
}
