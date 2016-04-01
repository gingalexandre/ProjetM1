package test.serveur.modele;

import static org.junit.Assert.*;

import org.junit.Test;

import serveur.modele.Hexagone;

public class TestHexagone {

	/**
	 * Test de la méthode de construction des Hexagone, concernant le désert
	 */
	@Test
	public void testConstructHexagone(){
		Hexagone hex = new Hexagone(1.0,1.0,50.0,9);
		assertTrue(hex.getType() == Hexagone.DESERT);
		
		Hexagone hexBis = new Hexagone(1.0,1.0,50.0,8);
		assertFalse(hexBis.getType() == Hexagone.DESERT);
	}

}
