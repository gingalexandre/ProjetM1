package test.serveur.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import serveur.modele.Message;

public class TestMessage {
	
	private Message message1;
	private Message message2;
	
	@Before
	public void init(){
		this.message1 = new Message("Vive les tests !");
		this.message2 = new Message("Paul", "Moi j'aime pas les tests !", "rouge");
	}
	
	/**
	 * Test les auteurs
	 */
	@Test
	public void testAuteur(){
		assertNull(this.message1.getAuteur());
		assertEquals(this.message2.getAuteur(), "Paul");
	}
	
	/**
	 * Test les couleurs
	 */
	@Test
	public void testCouleur(){
		assertEquals(this.message1.getCouleur(), "Noir");
		assertEquals(this.message2.getCouleur(), "rouge");
	}
	
	/**
	 * Test le contenu
	 */
	@Test
	public void testMessage(){
		assertEquals(this.message1.getMessage(), "Vive les tests !");
		assertEquals(this.message2.getMessage(), "Moi j'aime pas les tests !");
	}
}
