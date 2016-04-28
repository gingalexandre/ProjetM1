package test.serveur.modele;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import serveur.modele.Plateau;

/**
 * Created by Yohann Hugo on 14/04/2016.
 */
public class TestPlateau {
	
	private Plateau plateau;
	
    @Before
    public void init() throws RemoteException{
        this.plateau = Plateau.getInstance();
    }

    /**
     * Test de la création du plateau, il doit y avoir bien 19 hexagones.
     * @throws RemoteException 
     */
    @Test
    public void TestCreationPlateau() throws RemoteException{
        assertEquals(this.plateau.getHexagones().size(), 19);
    }
    
    /**
     * Test de la méthode de retour de l'hexagone contenant le voleur. De base c'est le 10ème hexagone qui le contient.
     * @throws RemoteException 
     */
    @Test
    public void TestRetourVoleur() throws RemoteException{
        assertTrue(plateau.getVoleur() == plateau.getHexagones().get(10));
    }

    /**
     * Test de la méthode de retour de l'hexagone contenant le voleur. Après simulation de mouvement.
     * @throws RemoteException 
     */
    @Test
    public void TestRetourVoleurDeplacer() throws RemoteException{
        plateau.getHexagones().get(9).setVOLEUR(false);
        plateau.getHexagones().get(2).setVOLEUR(true);
        assertTrue(plateau.getVoleur() == plateau.getHexagones().get(2));
        plateau.getHexagones().get(2).setVOLEUR(false);
        plateau.getHexagones().get(10).setVOLEUR(true);
        assertTrue(plateau.getVoleur() == plateau.getHexagones().get(10));
    }

}
