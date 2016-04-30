package test.serveur.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import serveur.modele.Joueur;
import serveur.modele.Ressource;
import serveur.modele.carte.Chevalier;

/**
 * @author Arthur
 * Classe de test
 */
public class TestJoueur {

	private Joueur joueur1, joueur2, joueur3;
	
	@Before
	public void init() throws RemoteException{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDateJoueur1 = LocalDate.parse("1994/12/13", dtf);
		LocalDate localDateJoueur2 = LocalDate.parse("1995/12/13", dtf);
		LocalDate localDateJoueur3 = LocalDate.parse("1996/12/13", dtf);
		
		
		this.joueur1 = new Joueur("Jerome");
		this.joueur1.setId(1);
		this.joueur1.setDateDeNaissance(Date.valueOf(localDateJoueur1));
		this.joueur1.setCouleur("rouge");
		this.joueur1.setPret(true);
		
		this.joueur2 = new Joueur("Arthur");
		this.joueur2.setId(2);
		this.joueur2.setDateDeNaissance(Date.valueOf(localDateJoueur2));
		this.joueur2.setCouleur("bleu");
		this.joueur2.setPret(true);
		
		this.joueur3 = new Joueur("Mathieu");
		this.joueur3.setId(3);
		this.joueur3.setDateDeNaissance(Date.valueOf(localDateJoueur3));
		this.joueur3.setCouleur("vert");
		this.joueur3.setPret(false);
	}
    
    /**
     * Test de l'id des joueurs()
     */
    @Test
    public void testId() throws RemoteException{
    	assertTrue(joueur1.getId() == 1);
  	   	assertTrue(joueur2.getId() == 2);
  	   	assertTrue(joueur3.getId() == 3);
    }
     
    /**
    * Test du nom des joueurs()
    */
    @Test
    public void testNom() throws RemoteException{
        assertTrue(joueur1.getNomUtilisateur().equals("Jerome"));
   	 	assertTrue(joueur2.getNomUtilisateur().equals("Arthur"));
   	 	assertTrue(joueur3.getNomUtilisateur().equals("Mathieu"));
    }
    
    /**
     * Test de la date de naissance des joueurs()
     */
     @Test
     public void testDateDeNaissance() throws RemoteException{
         assertTrue(joueur1.getDateDeNaissance().toString().equals("1994-12-13"));
    	 assertTrue(joueur2.getDateDeNaissance().toString().equals("1995-12-13"));
    	 assertTrue(joueur3.getDateDeNaissance().toString().equals("1996-12-13"));
     }
     
     /**
      * Test de la couleur des joueurs()
      */
      @Test
      public void testCouleur() throws RemoteException{
         assertTrue(joueur1.getCouleur().equals("rouge"));
         assertTrue(joueur2.getCouleur().equals("bleu"));
         assertTrue(joueur3.getCouleur().equals("vert"));
      }
      
      /**
      * Test si les joueurs sont prêts
      */
      @Test
      public void testPrets() throws RemoteException{
         assertEquals(joueur1.getPret(), true);
         assertEquals(joueur2.getPret(), true);
         assertEquals(joueur3.getPret(), false);
      }

	  /**
	   * Test de la méthode ajoutRessource()
	   */
	   @Test
	  public void testAjoutRessource() throws RemoteException{
		   joueur1.ajoutRessource(Ressource.BOIS, 2);
		   int bois1 = joueur1.getStockRessource().get(Ressource.BOIS);
		   assertEquals(bois1, 2);
		   
		   joueur1.ajoutRessource(Ressource.BOIS, 1);
		   int bois2 = joueur1.getStockRessource().get(Ressource.BOIS);
		   assertEquals(bois2, 3);
		   
		   joueur2.ajoutRessource(Ressource.LAINE, 2);
		   int laine1 = joueur2.getStockRessource().get(Ressource.LAINE);
		   assertEquals(laine1, 2);
		   
		   joueur2.ajoutRessource(Ressource.LAINE, 1);
		   int laine2 = joueur2.getStockRessource().get(Ressource.LAINE);
		   assertEquals(laine2, 3);
		   
		   joueur3.ajoutRessource(Ressource.BLE, 2);
		   int ble1 = joueur3.getStockRessource().get(Ressource.BLE);
		   assertEquals(ble1, 2);
		   
		   joueur3.ajoutRessource(Ressource.BLE, 1);
		   int ble2 = joueur3.getStockRessource().get(Ressource.BLE);
		   assertEquals(ble2, 3);
	  }
   
	   /**
	    * Test de la méthode suppressionRessource()
	    */
	   @Test
	   public void testSuppressionRessource() throws RemoteException{
		   joueur1.ajoutRessource(Ressource.BOIS, 2);
		   joueur1.supprimerRessource(Ressource.BOIS, 1);
		   int bois = joueur1.getStockRessource().get(Ressource.BOIS);
		   assertEquals(bois, 1);
		   
		   joueur2.ajoutRessource(Ressource.LAINE, 2);
		   joueur2.supprimerRessource(Ressource.LAINE, 1);
		   int laine = joueur2.getStockRessource().get(Ressource.LAINE);
		   assertEquals(laine, 1);
		   
		   joueur3.ajoutRessource(Ressource.BLE, 2);
		   joueur3.supprimerRessource(Ressource.BLE, 1);
		   int ble = joueur3.getStockRessource().get(Ressource.BLE);
		   assertEquals(ble, 1);
	   }
	   
	   /**
	    * Test des points de victoires
	    */
	   @Test
	   public void testPointVictoire() throws RemoteException{
		   this.joueur1.ajouterPointVictoire();
		   assertEquals(this.joueur1.getPointVictoire(), 1);
		   
		   this.joueur2.ajouterPointVictoire();
		   this.joueur2.ajouterPointVictoire();
		   assertEquals(this.joueur2.getPointVictoire(), 2);
		   
		   this.joueur3.ajouterPointVictoire();
		   this.joueur3.ajouterPointVictoire();
		   this.joueur3.ajouterPointVictoire();
		   assertEquals(this.joueur3.getPointVictoire(), 3);
	   }
	   
	   /**
	    * Test des colonies
	    */
	   @Test
	   public void testColonies() throws RemoteException{
		   this.joueur1.setNbColonie(2);
		   assertEquals(this.joueur1.getNbColonie(), 2);
		   
		   this.joueur2.setNbColonie(3);
		   assertEquals(this.joueur2.getNbColonie(), 3);
		   
		   this.joueur3.setNbColonie(0);
		   assertEquals(this.joueur3.getNbColonie(), 0);
	   }
	   
	   /**
	    * Test des villes
	    */
	   @Test
	   public void testVilles() throws RemoteException{
		   this.joueur1.setNbVille(2);
		   assertEquals(this.joueur1.getNbVille(), 2);
		   
		   this.joueur2.setNbVille(3);
		   assertEquals(this.joueur2.getNbVille(), 3);
		   
		   this.joueur3.setNbVille(0);
		   assertEquals(this.joueur3.getNbVille(), 0);
	   }
	   
	   /**
	    * Test des routes
	    */
	   @Test
	   public void testRoutes() throws RemoteException{
		   this.joueur1.setNbRoute(2);
		   assertEquals(this.joueur1.getNbRoute(), 2);
		   
		   this.joueur2.setNbRoute(3);
		   assertEquals(this.joueur2.getNbRoute(), 3);
		   
		   this.joueur3.setNbRoute(0);
		   assertEquals(this.joueur3.getNbRoute(), 0);
	   }
	   
	   /**
	    * Test l'ajout de carte
	 * @throws RemoteException 
	    */
	   public void testAddCarte() throws RemoteException{
		   this.joueur1.addCartes(new Chevalier());
		   assertEquals(this.joueur1.getCartes().size(), 1);
			
		   this.joueur2.addCartes(new Chevalier());
		   this.joueur2.addCartes(new Chevalier());
		   assertEquals(this.joueur2.getCartes().size(), 2);
		   
		   this.joueur3.addCartes(new Chevalier());
		   this.joueur2.addCartes(new Chevalier());
		   this.joueur2.addCartes(new Chevalier());
		   assertEquals(this.joueur3.getCartes().size(), 3);
	   }
}
