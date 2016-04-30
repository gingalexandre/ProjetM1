package test.serveur.modele;

import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import org.junit.Test;

import serveur.modele.Des;

public class TestDes {

	@Test
	/**
	 * Test de la méthode qui lance des dés.
	 */
	public void testLanceDes() throws RemoteException{
		Des des = new Des();
		assertTrue(des.lancerDes()[0]<7 && des.lancerDes()[0]>0 && des.lancerDes()[1] < 7 && des.lancerDes()[0]>0);
		assertTrue((des.lancerDes()[0]+des.lancerDes()[1]) >= 2 && (des.lancerDes()[0]+des.lancerDes()[1]) <= 14);
	}

}
