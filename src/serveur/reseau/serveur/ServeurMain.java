package serveur.reseau.serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServeurMain {

	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		lancerServeur(args[0]);
	}

	public static void lancerServeur(String ipServeur) throws RemoteException, MalformedURLException {
		System.setProperty("java.security.policy", "./security.policy");
		System.setSecurityManager(new SecurityManager());
		LocateRegistry.createRegistry(42000);
		Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(ipServeur);
		if (matcher.matches()) {
			System.setProperty("java.rmi.server.hostname", ipServeur);
			Naming.rebind("rmi://" + ipServeur + ":42000/serveur", new ServeurImpl());
			System.out.println("Serveur lancé correctement");
		} else {
			System.out.println("Adresse IP invalide !");
			System.exit(1);
		}
	}

}
