package test.serveur.modele;

import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import org.junit.Test;

import serveur.modele.Plateau;

/**
 * Created by Yohann Hugo on 14/04/2016.
 */
public class TestPlateau {

    /**
     * Test de la création du plateau, il doit y avoir bien 19 hexagones.
     * @throws RemoteException 
     */
    @Test
    public void TestCreationPlateau() throws RemoteException{
        Plateau p= Plateau.getInstance();
        assertTrue(p.getHexagones().size() == 19);

    }

    /**
     * Test de la méthode de retour de l'hexagone contenant le voleur. De base c'est le 10ème hexagone qui le contient.
     * @throws RemoteException 
     */
    @Test
    public void TestRetourVoleur() throws RemoteException{
        Plateau p = Plateau.getInstance();
        assertTrue(p.getVoleur() == p.getHexagones().get(10));
    }

    /**
     * Test de la méthode de retour de l'hexagone contenant le voleur. Après simulation de mouvement.
     * @throws RemoteException 
     */
    @Test
    public void TestRetourVoleurDeplacer() throws RemoteException{
        Plateau p = Plateau.getInstance();
        p.getHexagones().get(9).setVOLEUR(false);
        p.getHexagones().get(2).setVOLEUR(true);
        assertTrue(p.getVoleur() == p.getHexagones().get(2));
        p.getHexagones().get(2).setVOLEUR(false);
        p.getHexagones().get(10).setVOLEUR(true);
        assertTrue(p.getVoleur() == p.getHexagones().get(10));
    }

}
