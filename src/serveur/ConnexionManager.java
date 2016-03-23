package serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Singleton gérant la connexion au serveur
 * @author jerome
 */
public class ConnexionManager {
	
	/**
	 * Instance de la classe
	 */
	private static ConnexionManager INSTANCE = null;
	
	/**
	 * Serveur de connexion
	 */
	private Serveur serveur;
	
	/**
	 * Constructeur privé se connectant au serveur
	 */
	private ConnexionManager(){
		String serveurURL = "rmi://127.0.0.1:42000/serveur";
		try {
			this.serveur = (Serveur) Naming.lookup(serveurURL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet de récupérer l'instance unique
	 * @return l'instance unique
	 */
	public static ConnexionManager getInstance(){
		if (INSTANCE == null){ 	
			INSTANCE = new ConnexionManager();	
		}
		return INSTANCE;
	}
	
	/**
	 * Permet d'obtenir le serveur
	 * @return le serveur
	 */
	public Serveur getServeur(){
		return this.serveur;
	}
	
	/**
	 * Permet d'obtenir le serveur de manière static
	 * @return le serveur
	 */
	public static Serveur getStaticServeur(){
		return ConnexionManager.getInstance().getServeur();
	}
}
