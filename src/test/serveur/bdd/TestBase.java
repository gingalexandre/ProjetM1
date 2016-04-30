package test.serveur.bdd;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import serveur.bdd.Base;

/**
*
* @author Alexandre
*/

public class TestBase {

    @Test
    /**
     * Test du constructeur
     */
    public void createTest(){
        Base base = new Base();
        assertNotNull(base);
    }
    
    @Test
    /**
     * Test de la connexion
     * @throws InterruptedException
     */
    public void connexionTest() throws InterruptedException{
        assertNotNull(Base.connexion());
    }
   


}
