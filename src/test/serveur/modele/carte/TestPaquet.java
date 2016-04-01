package test.serveur.modele.carte;


import serveur.modele.carte.Paquet;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Classe de test du Paquet.
 *
 * @author Yohann Hugo
 */
public class TestPaquet {

	/**
	 * Test de la méthode de création des Paquets
	 */
    @Test
    public void testCreationPaquet(){
        Paquet paquet = new Paquet();
        assertTrue(paquet.getDeck().size() == 25);
    }

    /**
     * Test de la méthode de pioche
     */
    @Test
    public void testPioche(){
        Paquet paquet = new Paquet();
        paquet.pioche();
        assertTrue(paquet.getDeck().size() == 24);
        paquet.pioche();
        assertTrue(paquet.getDeck().size() == 23);
        paquet.pioche();
        assertTrue(paquet.getDeck().size() == 22);
    }
}
