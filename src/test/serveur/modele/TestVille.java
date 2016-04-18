package test.serveur.modele;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	
	Plateau plateau = Plateau.getInstance();
	ArrayList<Ville> listeVilles = plateau.getVilles();
	ArrayList<Route> listeRoutes = plateau.getRoutes();
	Joueur j1;
	Joueur j2;
	Joueur j3;
	

	/**
	 * Test de la méthode permettant de tester si une ville est libre ou pas
	 */
	@Test
    public void testEstLibre(){
		listeRoutes.get(0).setOQP(j1);
		assertTrue(listeVilles.get(0).estLibre(j1));
		
		assertFalse(listeVilles.get(0).estLibre(j1));
		assertFalse(listeVilles.get(1).estLibre(j1));
		
		assertFalse(listeVilles.get(10).estLibre(j1));
		
		assertFalse(listeVilles.get(0).estLibre(j2));
		assertFalse(listeVilles.get(1).estLibre(j2));
    }
    
    
   
  
  
}
