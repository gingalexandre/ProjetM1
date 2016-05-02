package test.serveur.bdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import serveur.bdd.Base;
import serveur.bdd.modeleBDD.Statistiques;
import serveur.bdd.modeleBDD.Utilisateur;

public class TestUtilisateurBDD {

	/**
	 * Méthode permettant d'insérer un utilisateur pour tester la méthode
	 * d'inscription
	 * 
	 * @throws InterruptedException
	 */
	@Before
	public void creerUtilisateur() throws InterruptedException {
		Utilisateur test = new Utilisateur("testtest", "azerty", LocalDate.now());
		assertEquals(test.inscription(), "Inscription réussie");
	}

	@Test
	/**
	 * Méthode permettant de tester une inscription avec un pseudo identique
	 * 
	 * @throws InterruptedException
	 */
	public void inscriptionIdentiqueTest() throws InterruptedException {
		Utilisateur test = new Utilisateur("testtest", "azerty", null);
		assertEquals(test.inscription(), "Nom d'utilisateur déjà existant, veuillez recommencer.");
	}

	/**
	 * Méthode permettant de voir si la connexion fonctionne
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void connexionUtilisateurTest() throws InterruptedException {
		Utilisateur test = new Utilisateur("testtest", "azerty", LocalDate.now());
		assertTrue(test.verificationConnexion());

	}



	/**
	 * Méthode permettant de voir si l'insertion des statistiques à la fin de
	 * partie fonctionne
	 */
	@Test
	public void addStatistiquesTest(){
		// Lors de l'ajout d'une victoire
		Statistiques.addStatistique(1, "testtest");
		Integer[] res = Statistiques.getStatistiques("testtest");
		assertTrue(res[0] == 1);
		assertTrue(res[1] == 1);
		
		// Lors de l'ajout d'une défaite
		Statistiques.addStatistique(0, "testtest");
		res = Statistiques.getStatistiques("testtest");
		assertTrue(res[0] == 2);
		assertTrue(res[1] == 1);
	}
	
	



	/**
	 * M�thode permettant de supprimer un utilisateur
	 * 
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	@After
	public void supprimerUtilisateur() throws InterruptedException, SQLException {
		Connection connection = Base.connexion();
		String query = "DELETE FROM Joueur WHERE pseudo=?;";
		PreparedStatement prestmt = connection.prepareStatement(query);
		prestmt.setString(1, "testtest");
		prestmt.executeUpdate();
		connection.commit();
		connection.close();

	}
	
	

}
