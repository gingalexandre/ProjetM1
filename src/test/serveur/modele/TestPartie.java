package test.serveur.modele;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import serveur.modele.Joueur;
import serveur.modele.Partie;
import serveur.modele.Plateau;
import serveur.modele.service.JoueurInterface;

public class TestPartie {
	
	private Partie partie;
	private JoueurInterface joueur1, joueur2, joueur3;
	
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
		
		this.partie = new Partie(Plateau.getInstance());
		this.partie.setJoueur1(this.joueur1);
		this.partie.setJoueur2(this.joueur2);
		this.partie.setJoueur3(this.joueur3);
	}
	
	/**
	 * Test les joueurs
	 * @throws RemoteException
	 */
	@Test
	public void testJoueurs() throws RemoteException{
		assertEquals(this.partie.getJoueur1().getId(), this.joueur1.getId());
		assertEquals(this.partie.getJoueur1().getNomUtilisateur(), this.joueur1.getNomUtilisateur());
		assertEquals(this.partie.getJoueur1().getDateDeNaissance(), this.joueur1.getDateDeNaissance());
		assertEquals(this.partie.getJoueur1().getCouleur(), this.joueur1.getCouleur());
		
		assertEquals(this.partie.getJoueur2().getId(), this.joueur2.getId());
		assertEquals(this.partie.getJoueur2().getNomUtilisateur(), this.joueur2.getNomUtilisateur());
		assertEquals(this.partie.getJoueur2().getDateDeNaissance(), this.joueur2.getDateDeNaissance());
		assertEquals(this.partie.getJoueur2().getCouleur(), this.joueur2.getCouleur());
		
		assertEquals(this.partie.getJoueur3().getId(), this.joueur3.getId());
		assertEquals(this.partie.getJoueur3().getNomUtilisateur(), this.joueur3.getNomUtilisateur());
		assertEquals(this.partie.getJoueur3().getDateDeNaissance(), this.joueur3.getDateDeNaissance());
		assertEquals(this.partie.getJoueur3().getCouleur(), this.joueur3.getCouleur());
	}
	
	/**
	 * Test le nombre de joueurs de la partie
	 * @throws RemoteException
	 */
	@Test
	public void testNbJoueurs() throws RemoteException{
		assertEquals(this.partie.getNombreJoueurs(), 3);
	}
	
	/**
	 * Test l'incrémentation des tours de jeu
	 * @throws RemoteException
	 */
	@Test
	public void testTour() throws RemoteException{
		assertEquals(this.partie.getTour(), 1);
		this.partie.incrementeTour();
		assertEquals(this.partie.getTour(), 2);
		this.partie.incrementeTour();
		assertEquals(this.partie.getTour(), 3);
		this.partie.incrementeTour();
		assertEquals(this.partie.getTour(), 1);
		this.partie.incrementeTour();
		assertEquals(this.partie.getCompteurTourGlobal(), 4);
	}
	
	/**
	 * Test l'obtention d'un joueur via son nom
	 * @throws RemoteException 
	 */
	@Test
	public void testJoueurParNom() throws RemoteException{
		assertEquals(this.partie.getJoueurByName("Jerome"), this.joueur1);
		assertEquals(this.partie.getJoueurByName("Arthur"), this.joueur2);
		assertEquals(this.partie.getJoueurByName("Mathieu"), this.joueur3);
	}
	
	/**
	 * Test l'obtention d'un joueur via sa couleur
	 * @throws RemoteException 
	 */
	@Test
	public void testJoueurParCouleur() throws RemoteException{
		assertEquals(this.partie.getJoueurByCouleur("rouge"), this.joueur1);
		assertEquals(this.partie.getJoueurByCouleur("bleu"), this.joueur2);
		assertEquals(this.partie.getJoueurByCouleur("vert"), this.joueur3);
	}
	
	/**
	 * Test le joueur le plus vieux
	 */
	@Test
	public void testJoueurPlusVieux() throws RemoteException{
		this.partie.arrangerOrdreTour();
		assertEquals(this.partie.getJoueurLePlusVieux(), this.joueur1);
	}
	
	/**
	 * Test si l'ordre des tours est bon
	 * @throws RemoteException
	 */
	@Test
	public void testOrdreTour() throws RemoteException{
		this.partie.arrangerOrdreTour();
		// Le plus vieux commence
		assertEquals(this.partie.getOrdreJeu().get(0), this.joueur1);
		assertEquals(this.partie.getOrdreJeu().get(1), this.joueur2);
		assertEquals(this.partie.getOrdreJeu().get(2), this.joueur3);
	}
}
