package test.serveur.reseau;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exception.TooMuchPlayerException;
import serveur.modele.service.JoueurInterface;
import serveur.reseau.proxy.JoueurServeur;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

/**
 * Test le serveur. SUPPOSE QUE LE SERVEUR EST EN ROUTE SINON IL Y AURA DES EXCEPTIONS. 
 * @author jerome
 */
public class TestServeur {
	
	private static String serveurURL;
	private static Serveur serveur = null;
	private static Proxy proxy = null;
	
	private ArrayList<JoueurServeur> joueursServeur;
	
	@BeforeClass
	public static void init() throws RemoteException, TooMuchPlayerException{
		serveurURL = "rmi://127.0.0.1:42000/serveur";
		try {
			serveur = (Serveur) Naming.lookup(serveurURL);
			ConnexionManager.getInstance();		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		proxy = ConnexionManager.getStaticProxy();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDateJoueur1 = LocalDate.parse("1994/12/13", dtf);
		
		serveur.enregistrerJoueur(proxy, "Paul", Date.valueOf(localDateJoueur1));
	}
	
	@Before
	public void getData() throws RemoteException{
		joueursServeur = serveur.getListeJoueurs();
	}
	
	@Test
	public void testServeurNotNull(){
		assertNotNull(serveur);
	}
	
	@Test
	public void testProxyNotNull(){
		assertNotNull(proxy);
	}
	
	@Test
	public void testJoueurAjoute() throws RemoteException{
		assertTrue(joueursServeur.size() > 0);
	}
	
	@Test
	public void testInformationsAjoute() throws RemoteException{
		JoueurInterface joueur1 = joueursServeur.get(0).getJoueur();

		assertTrue(joueur1.getNomUtilisateur().equals("Paul"));
		assertTrue(joueur1.getCouleur().equals("rouge"));
		assertTrue(joueur1.getDateDeNaissance().toString().equals("1994-12-13"));
	}
}
