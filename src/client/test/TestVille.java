package client.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import client.modele.Joueur;
import client.modele.Plateau;
import client.modele.Point;
import client.modele.Ressource;
import client.modele.Ville;

import static org.junit.Assert.*;

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
