package serveur.reseau.serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class ServeurMain {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		System.setProperty("java.security.policy", "./security.policy");
		System.setSecurityManager(new SecurityManager());
		LocateRegistry.createRegistry(42000);
		System.setProperty("java.rmi.server.hostname", "127.0.0.1");
		Naming.rebind("rmi://127.0.0.1:42000/serveur", new ServeurImpl());
		System.out.println("Serveur lancé correctement");
	}

}
