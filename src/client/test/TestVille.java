package client.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import client.modeles.Joueur;
import client.modeles.Plateau;
import client.modeles.Point;
import client.modeles.Ressource;
import client.modeles.Ville;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * @author Arthur
 * Classe de test
 */
public class TestVille {
	
	ArrayList<Ville> listeVilles = Plateau.getVilles();
	Joueur j1 = new Joueur ("Joueur 1");
	Joueur j2 = new Joueur ("Joueur 2");
	Joueur j3 = new Joueur ("Joueur 3");
	

	@Test
    public void testEstLibre(){
		//assertEquals(true,listeVilles.get(0).estLibre(j1));
    }
    
    
   
  
  
}
