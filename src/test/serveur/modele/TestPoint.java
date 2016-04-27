package test.serveur.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import serveur.modele.Point;

public class TestPoint {

	/**
	 * Test de la méthode permettant de voir si deux Points sont égaux
	 */
	@Test
	public void testEqualsPoint() {
		Point p = new Point(1.0, 1.0);
		assertTrue(p.equals(p));
		
		Point p2 = new Point(2.0, 2.0);
		assertFalse(p.equals(p2));
	}
	
	/**
	 * Test de la méthode permettant de comparer deux Points
	 */
	@Test
	public void testComparePoint(){
		Point p = new Point(1.0, 1.0);
		Point p2 = new Point(2.0, 2.0);
		
		assertEquals(p2.compareTo(p),-10);
		
		assertEquals(p.compareTo(p2),0);
		
		assertEquals(p.compareTo(p),0);
		
		Point p3 = new Point(2.0,1.0);
		assertEquals(p.compareTo(p3),10);
		
		assertEquals(p3.compareTo(p),-10);
	}
}
