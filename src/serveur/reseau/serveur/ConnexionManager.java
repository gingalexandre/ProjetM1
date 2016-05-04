package serveur.reseau.serveur;import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import client.view.VuePageAccueil;
import client.view.VuePrincipale;
import javafx.scene.control.Alert;
import serveur.reseau.proxy.Proxy;

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
	 * Proxy connecté au serveur
	 */
	private Proxy proxy;

	/**
	 * Constructeur privé se connectant au serveur
	 */
	private ConnexionManager(){
		String serveurURL = "rmi://127.0.0.1:42000/serveur";
		try {
			this.serveur = (Serveur) Naming.lookup(serveurURL);
			this.proxy = new Proxy();
		} catch (Exception e) {
            VuePrincipale.stagePrincipal.close();
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

	/**
	 * @return le proxy du joueur
	 */
	public Proxy getProxy(){
		return this.proxy;
	}

	/**
	 * Permet d'obtenir le serveur de manière static
	 * @return le serveur
	 */
	public static Proxy getStaticProxy(){
		return ConnexionManager.getInstance().getProxy();
	}
}