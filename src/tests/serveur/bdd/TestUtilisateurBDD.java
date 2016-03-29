package tests.serveur.bdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import serveur.bdd.Base;
import serveur.bdd.Utilisateur;

public class testUtilisateurBDD {
	
	/**
	 * Méthode permettant d'insérer un utilisateur pour tester la méthode d'inscription
	 * @throws InterruptedException
	 */
	@Before
	public void creerUtilisateur() throws InterruptedException{
		Utilisateur test = new Utilisateur("testtest", "azerty");
		assertEquals(test.inscription(),2);
	}
	
	@Test
	/**
	 * Méthode permettant de tester une inscription avec un pseudo identique
	 * @throws InterruptedException
	 */
	public void inscriptionIdentiqueTest() throws InterruptedException{
		Utilisateur test = new Utilisateur("testtest", "azerty");
		assertEquals(test.inscription(),1);
	}

	/**
	 * Méthode permettant de voir si la connexion fonctionne
	 * @throws InterruptedException
	 */
	@Test
	public void connexionUtilisateurTest() throws InterruptedException {
		Utilisateur test = new Utilisateur("testtest", "azerty");
		assertTrue(test.verificationConnexion());
	}
	
	/**
	 * Méthode permettant de supprimer un utilisateur
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	@After
	public void supprimerUtilisateur() throws InterruptedException, SQLException{
		Connection connection = Base.connexion();
		String query = "DELETE FROM Joueur WHERE pseudo=?;";
		PreparedStatement prestmt = connection.prepareStatement(query);
		prestmt.setString(1, "testtest");
		prestmt.executeUpdate();
		connection.commit();
		connection.close();
		
	}

}
