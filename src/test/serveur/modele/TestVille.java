package test.serveur.modele;

import org.junit.Test;

import serveur.modele.Joueur;
import serveur.modele.Plateau;
import serveur.modele.Ville;

import java.util.ArrayList;

/**
 * @author Arthur
 * Classe de test
 */
public class TestVille {
	
	Plateau plateau = Plateau.getInstance();
	ArrayList<Ville> listeVilles = plateau.getVilles();
	Joueur j1 = new Joueur ("Joueur 1");
	Joueur j2 = new Joueur ("Joueur 2");
	Joueur j3 = new Joueur ("Joueur 3");
	

	@Test
    public void testEstLibre(){
		//assertEquals(true,listeVilles.get(0).estLibre(j1));
    }
    
    
   
  
  
}
