package test.serveur.modele;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import serveur.modele.Joueur;
import serveur.modele.Ressource;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

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
    public void testIdJoueur() throws RemoteException{
  	   Joueur j1 = new Joueur("");
  	   Joueur j2 = new Joueur("");
  	   Joueur j3 = new Joueur("");
  	   assertEquals(j1.getId(),1);
  	   assertEquals(j2.getId(),2);
  	   assertEquals(j3.getId(),3);
    }
    

  /**
   * Test de la méthode ajoutRessource()
   */
   @Test
  public void testAjoutRessource() throws RemoteException{
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
   public void testSuppressionRessource() throws RemoteException{
	   Joueur j = new Joueur("");
	   j.ajoutRessource(Ressource.BOIS, 2);
	   int bois1 = j.getStockRessource().get(Ressource.BOIS);
	   assertEquals(bois1,2);
	   j.supprimerRessource(Ressource.BOIS, 1);
	   int bois2 = j.getStockRessource().get(Ressource.BOIS);
	   assertEquals(bois2,1);
   }
   
  
  
}
