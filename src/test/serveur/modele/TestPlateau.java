package test.serveur.modele;

import org.junit.BeforeClass;
import org.junit.Test;
import serveur.modele.Plateau;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yohann Hugo on 14/04/2016.
 */
public class TestPlateau {

    /**
     * Test de la création du plateau, il doit y avoir bien 19 hexagones.
     */
    @Test
    public void TestCreationPlateau(){
        Plateau p= Plateau.getInstance();
        assertTrue(p.getHexagones().size() == 19);

    }

    /**
     * Test de la méthode de retour de l'hexagone contenant le voleur. De base c'est le 10ème hexagone qui le contient.
     */
    @Test
    public void TestRetourVoleur(){
        Plateau p = Plateau.getInstance();
        assertTrue(p.getVoleur() == p.getHexagones().get(10));
    }

    /**
     * Test de la méthode de retour de l'hexagone contenant le voleur. Après simulation de mouvement.
     */
    @Test
    public void TestRetourVoleurDeplacer(){
        Plateau p = Plateau.getInstance();
        p.getHexagones().get(9).setVOLEUR(false);
        p.getHexagones().get(2).setVOLEUR(true);
        assertTrue(p.getVoleur() == p.getHexagones().get(2));
        p.getHexagones().get(2).setVOLEUR(false);
        p.getHexagones().get(10).setVOLEUR(true);
        assertTrue(p.getVoleur() == p.getHexagones().get(10));
    }

}
