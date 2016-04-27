package serveur.reseau.serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import serveur.reseau.proxy.Proxy;

/**
 * Singleton g�rant la connexion au serveur
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
	 * Proxy connect� au serveur
	 */
	private Proxy proxy;
	
	/**
	 * Constructeur priv� se connectant au serveur
	 */
	private ConnexionManager(){
		//System.setSecurityManager(new SecurityManager());
		//String serveurURL = "rmi://10.10.190.80:42000/serveur";
		String serveurURL = "rmi://127.0.0.1:42000/serveur";
		try {
			this.serveur = (Serveur) Naming.lookup(serveurURL);
			this.proxy = new Proxy();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Permet de r�cup�rer l'instance unique
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
	 * Permet d'obtenir le serveur de mani�re static
	 * @return le serveur
	 */
	public static Serveur getStaticServeur(){
		return ConnexionManager.getInstance().getServeur();
	}
	
	public Proxy getProxy(){
		return this.proxy;
	}
	
	/**
	 * Permet d'obtenir le serveur de mani�re static
	 * @return le serveur
	 */
	public static Proxy getStaticProxy(){
		return ConnexionManager.getInstance().getProxy();
	}
}
