package serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServeurMain {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(42000);
		System.setProperty("java.rmi.server.hostname", "127.0.0.1");
		Naming.rebind("rmi://127.0.0.1:42000/serveur", new ServeurImpl());
		System.out.println("Serveur lanc� correctement");
	}
}
