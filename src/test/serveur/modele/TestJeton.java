package test.serveur.modele;

import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import serveur.modele.Hexagone;
import serveur.modele.Jeton;

public class TestJeton {
	
	private Hexagone hexagone;
	private Jeton jeton;
	
	@Before
	public void init() throws RemoteException{
		this.hexagone = new Hexagone(4.0, 3.0, 2, 8);
		this.jeton = new Jeton(this.hexagone);
	}
	
	@Test
	public void testValeur() throws RemoteException{
		assertTrue(this.jeton.getNumeroJeton() >= 2 && this.jeton.getNumeroJeton() <= 12);
	}
	
	@Test
	public void testEmplacement() throws RemoteException{
		assertTrue(this.jeton.getEmplacement().getX() == this.hexagone.getCentre().getX());
		assertTrue(this.jeton.getEmplacement().getY() == this.hexagone.getCentre().getY());
	}
	
	@Test
	public void testPoints() throws RemoteException{
		assertTrue(this.jeton.getPoints()[0] == this.hexagone.getCentre().getX());
		assertTrue(this.jeton.getPoints()[1] == this.hexagone.getCentre().getY());
	}
}
