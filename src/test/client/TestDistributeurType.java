package test.client;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import serveur.commun.DistributeurType;

public class TestDistributeurType {
	private static int NBTUILEMIN = 3;
	private static int NBTUILEMAX = 4;
	private static int TAILLE = 18;

	/**
	 * Test de la méthode permettant de tester si le nombre de case est bien
	 * respecté lors de la création
	 */
	@Test
	public void testNombreCaseDifferente() {
		DistributeurType distributeur = DistributeurType.getInstance();
		int nbCarriere = 0;
		int nbMontagne = 0;
		int nbChamps = 0;
		int nbPrairie = 0;
		int nbForet = 0;
		for (int id = 0; id < distributeur.getTerritoiredispo().size(); id++) {
			System.out.println(distributeur.getTerritoiredispo().size());
			switch (distributeur.getTerritoiredispo().get(id)) {
			case 1:
				nbForet++;
				break;
			case 2:
				nbChamps++;
				break;
			case 3:
				nbCarriere++;
				break;
			case 4:
				nbMontagne++;
				break;
			case 5:
				nbPrairie++;
				break;
			default:
				break;
			}
		}
		System.out.println(nbForet);
		assertTrue(nbForet == NBTUILEMAX);
		assertTrue(nbChamps == NBTUILEMAX);
		assertTrue(nbCarriere == NBTUILEMIN);
		assertTrue(nbMontagne == NBTUILEMIN);
		assertTrue(nbPrairie == NBTUILEMAX);
		assertTrue(distributeur.getTerritoiredispo().size() == TAILLE);
	}

	/**
	 * Test de la méthode permettant de donner les types
	 */
	@After
	public void testDonnerType() {
		DistributeurType distributeur = DistributeurType.getInstance();
		assertTrue(distributeur.donnerType() < 7 && distributeur.donnerType() > 0);

	}

}
