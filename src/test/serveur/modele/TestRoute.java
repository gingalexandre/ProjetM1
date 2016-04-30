package test.serveur.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import org.junit.Test;

import serveur.modele.Point;
import serveur.modele.Route;

public class TestRoute {

	/**
	 * Test de la méthode permettant de voir si deux Routes sont identiques
	 * @throws RemoteException 
	 */
	@Test
	public void testEqualsRoute() throws RemoteException {

		Route r = new Route(new Point(1.0, 1.0), new Point(2.0, 2.0));
		Route r2 = new Route(new Point(3.0, 3.0), new Point(4.0, 4.0));
		
		assertTrue(r.equals(r));
		assertFalse(r.equals(r2));
	}
	
	/**
	 * Test de la méthode permettant de comparer deux Routes
	 * @throws RemoteException 
	 */
	@Test
	public void testCompareRoute() throws RemoteException {

		Route r = new Route(new Point(1.0, 1.0), new Point(2.0, 2.0));
		Route r2 = new Route(new Point(3.0, 3.0), new Point(4.0, 4.0));
		
		assertEquals(r.compareTo(r),0);
		assertEquals(r.compareTo(r2),10);
		assertEquals(r2.compareTo(r),-10);
		
		Route r3 = new Route(new Point(1.0, 1.0), new Point(2.0, 3.0));
		assertEquals(r.compareTo(r3),10);
		assertEquals(r3.compareTo(r),-10);
	}

}
