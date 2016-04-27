package test.serveur.modele;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import serveur.modele.Hexagone;

public class TestHexagone {

	private Hexagone hexagone;
	
	@Before
	public void constructionHexagone() throws RemoteException{
		this.hexagone = new Hexagone(4.0, 3.0, 2, 8);
	}
	
	/**
	 * Test de la méthode de construction des Hexagone, concernant le désert
	 * @throws RemoteException 
	 */
	@Test
	public void testDesert() throws RemoteException{
		Hexagone hex = new Hexagone(1.0,1.0,50.0,9);
		assertTrue(hex.getType() == Hexagone.DESERT);
		
		Hexagone hexBis = new Hexagone(1.0,1.0,50.0,8);
		assertFalse(hexBis.getType() == Hexagone.DESERT);
	}
	
	/**
	 * Test si le centre a été construit au bon endroit
	 */
	@Test
	public void testCentre(){
		assertTrue(this.hexagone.getCentre().getX() == 4.0);
		assertTrue(this.hexagone.getCentre().getY() == 3.0);
	}
	
	/**
	 * Test si le point A a été construit au bon endroit
	 */
	@Test
	public void testPointA(){
		assertTrue(this.hexagone.getA().getX() == 4.0);
		assertTrue(this.hexagone.getA().getY() == 5.0);
	}
	
	/**
	 * Test si le point B a été construit au bon endroit
	 */
	@Test
	public void testPointB(){
		assertTrue(this.hexagone.getB().getX() == 5.0);
		assertTrue(this.hexagone.getB().getY() == 3.0+(2.0/2));
	}
	
	/**
	 * Test si le point C a été construit au bon endroit
	 */
	@Test
	public void testPointC(){
		assertTrue(this.hexagone.getC().getX() == 5.0);
		assertTrue(this.hexagone.getC().getY() == (3.0 - (2.0/2)));
	}
	
	/**
	 * Test si le point D a été construit au bon endroit
	 */
	@Test
	public void testPointD(){
		assertTrue(this.hexagone.getD().getX() == 4.0);
		assertTrue(this.hexagone.getD().getY() == 3.0-2.0);
	}
	
	/**
	 * Test si le point E a été construit au bon endroit
	 */
	@Test
	public void testPointE(){
		assertTrue(this.hexagone.getE().getX() == 2.0);
		assertTrue(this.hexagone.getE().getY() == 3.0-(2.0/2));
	}
	
	/**
	 * Test si le point E a été construit au bon endroit
	 */
	@Test
	public void testPointF(){
		assertTrue(this.hexagone.getF().getX() == 2.0);
		assertTrue(this.hexagone.getF().getY() == 3.0+(2.0/2));
	}
}
