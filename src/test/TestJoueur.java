package test;

import modeles.Joueur;
import modeles.Ressource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Arthur
 * Classe de test
 */
public class TestJoueur {

    public TestJoueur() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    /**
     * Test de l'id des joueurs()
     */
     @Test
    public void testIdJoueur() {
  	   Joueur j1 = new Joueur("");
  	   Joueur j2 = new Joueur("");
  	   Joueur j3 = new Joueur("");
  	   assertEquals(j2.getId(),2);
  	   assertEquals(j3.getId(),3);
    }
    

  /**
   * Test de la méthode ajoutRessource()
   */
   @Test
  public void testAjoutRessource() {
	   Joueur j = new Joueur("");
	   j.ajoutRessource(Ressource.BOIS, 2);
	   int bois1 = j.getStockRessource().get(Ressource.BOIS);
	   assertEquals(bois1,2);
	   j.ajoutRessource(Ressource.BOIS, 1);
	   int bois2 = j.getStockRessource().get(Ressource.BOIS);
	   assertEquals(bois2,3);
  }
   
   /**
    * Test de la méthode suppressionRessource()
    */
   @Test
   public void testSuppressionRessource() {
	   Joueur j = new Joueur("");
	   j.ajoutRessource(Ressource.BOIS, 2);
	   int bois1 = j.getStockRessource().get(Ressource.BOIS);
	   assertEquals(bois1,2);
	   j.supprimerRessource(Ressource.BOIS, 1);
	   int bois2 = j.getStockRessource().get(Ressource.BOIS);
	   assertEquals(bois2,1);
   }
   
  
  
}
