package test.serveur.modele;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

import serveur.modele.Joueur;
import serveur.modele.Plateau;
import serveur.modele.Route;
import serveur.modele.Ville;

/**
 * @author Arthur
 * Classe de test
 */
public class TestVille {
	
	Plateau plateau ;
	ArrayList<Ville> listeVilles = plateau.getVilles();
	ArrayList<Route> listeRoutes = plateau.getRoutes();
	Joueur j1;
	Joueur j2;
	Joueur j3;
	
	@After
	public void creePlateau() throws RemoteException{
		plateau = Plateau.getInstance();
	}

	/**
	 * Test de la méthode permettant de tester si une ville est libre ou pas
	 */
	@Test
    public void testEstLibre(){
		listeRoutes.get(0).setOQP(j1);
		assertTrue(listeVilles.get(0).estLibre(j1, listeVilles));
		
		assertFalse(listeVilles.get(0).estLibre(j1, listeVilles));
		assertFalse(listeVilles.get(1).estLibre(j1, listeVilles));
		
		assertFalse(listeVilles.get(10).estLibre(j1, listeVilles));
		
		assertFalse(listeVilles.get(0).estLibre(j2, listeVilles));
		assertFalse(listeVilles.get(1).estLibre(j2, listeVilles));
    }
    
    
   
  
  
}
