package test.serveur.modele;

import org.junit.Test;
import static org.junit.Assert.*;

import serveur.modele.Des;

public class TestDes {

	@Test
	/**
	 * Test de la méthode qui lance des dés.
	 */
	public void testLanceDes(){
		Des des = new Des();
		assertTrue(des.lancerDes()[0]<7 && des.lancerDes()[0]>0 && des.lancerDes()[1] < 7 && des.lancerDes()[0]>0);
	}

}
