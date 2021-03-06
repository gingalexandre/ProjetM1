package test.serveur.bdd;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import serveur.bdd.modeleSauvegarde.CarteSauvegarde;
import serveur.bdd.modeleSauvegarde.HexagoneSauvegarde;
import serveur.bdd.modeleSauvegarde.JetonSauvegarde;
import serveur.bdd.modeleSauvegarde.JoueurSauvegarde;
import serveur.bdd.modeleSauvegarde.PaquetSauvegarde;
import serveur.bdd.modeleSauvegarde.RessourceSauvegarde;
import serveur.bdd.modeleSauvegarde.RouteSauvegarde;
import serveur.bdd.modeleSauvegarde.VilleSauvegarde;
import serveur.modele.Hexagone;
import serveur.modele.Jeton;
import serveur.modele.Joueur;
import serveur.modele.Point;
import serveur.modele.Ressource;
import serveur.modele.Route;
import serveur.modele.Ville;
import serveur.modele.carte.Paquet;
import serveur.modele.service.CarteInterface;

public class TestSauvegarde {
	private static ObjectMapper objectMapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.ANY);;
	private static Paquet paquet;

	@Test
	public void genererSauvegardeHexagone() throws JsonGenerationException, JsonMappingException, IOException {
		Hexagone hex = new Hexagone(1.0, 2.0, 3, 1);
		HexagoneSauvegarde hexSauv = new HexagoneSauvegarde(hex);
		String contenu = "";
		contenu = objectMapper.writeValueAsString(hexSauv);
		assertTrue(contenu != "");
		HexagoneSauvegarde h2 = objectMapper.readValue(contenu, HexagoneSauvegarde.class);
		assertTrue(h2.equals(hexSauv));
	}

	@Test
	public void genererSauvegardeJeton() throws JsonGenerationException, JsonMappingException, IOException {
		Hexagone hexagone = new Hexagone(1.0, 2.0, 3, 1);
		Jeton jeton = new Jeton(hexagone);
		JetonSauvegarde jetonSauv = new JetonSauvegarde(jeton);
		String contenu = "";
		contenu = objectMapper.writeValueAsString(jetonSauv);
		assertTrue(contenu != "");
		JetonSauvegarde jet2 = objectMapper.readValue(contenu, JetonSauvegarde.class);
		assertTrue(jet2.equals(jetonSauv));
	}

	@Test
	public void genererSauvegardeVille() throws JsonGenerationException, JsonMappingException, IOException {
		Ville ville = new Ville(new Point(1.0, 2.0));
		ville.ajouterRoute(new Route(new Point(1.0, 2.0), new Point(1.0, 2.0)));
		ville.ajouterRoute(new Route(new Point(1.0, 2.0), new Point(1.0, 2.0)));
		ville.ajouterRoute(new Route(new Point(1.0, 2.0), new Point(1.0, 2.0)));
		VilleSauvegarde villeSauv = new VilleSauvegarde(ville);
		String contenu = "";
		contenu = objectMapper.writeValueAsString(villeSauv);
		assertTrue(contenu != "");
		VilleSauvegarde v2 = objectMapper.readValue(contenu, VilleSauvegarde.class);
		assertTrue(v2.equals(villeSauv));
	}

	@Test
	public void genererSauvegardeRoute() throws JsonGenerationException, JsonMappingException, IOException {
		Route route = new Route(new Point(1.0, 2.0), new Point(3.0, 4.0));
		RouteSauvegarde routeSauv = new RouteSauvegarde(route);
		String contenu = "";
		contenu = objectMapper.writeValueAsString(routeSauv);
		assertTrue(contenu != "");
		RouteSauvegarde r2 = objectMapper.readValue(contenu, RouteSauvegarde.class);
		assertTrue(r2.equals(routeSauv));
	}

	@Test
	public void genererSauvegardeJoueur() throws JsonGenerationException, JsonMappingException, IOException {
		Joueur joueur = new Joueur(1, "toto", Date.valueOf(LocalDate.now()), "bleu", true, 0, 0, 0, 0,
				new HashMap<Integer, Integer>(), new ArrayList<CarteInterface>());
		paquet = new Paquet();
		joueur.addCarte(paquet.pioche());
		JoueurSauvegarde joueurSauv = new JoueurSauvegarde(joueur);
		String contenu = "";
		contenu = objectMapper.writeValueAsString(joueurSauv);
		assertTrue(contenu != "");
		JoueurSauvegarde j2 = objectMapper.readValue(contenu, JoueurSauvegarde.class);
		assertTrue(j2.equals(joueurSauv));
	}

	@Test
	public void genererSauvegardeCarte() throws JsonGenerationException, JsonMappingException, IOException {
		CarteInterface carte = paquet.pioche();
		CarteSauvegarde carteSauv = new CarteSauvegarde(carte);
		String contenu = "";
		contenu = objectMapper.writeValueAsString(carteSauv);
		assertTrue(contenu != "");
		CarteSauvegarde c2 = objectMapper.readValue(contenu, CarteSauvegarde.class);
		assertTrue(c2.equals(carteSauv));
	}
	
	@Test
	public void genererSauvegardePaquet() throws JsonGenerationException, JsonMappingException, IOException {
		String contenu = "";
		PaquetSauvegarde pSauv = new PaquetSauvegarde(paquet);
		contenu = objectMapper.writeValueAsString(pSauv);
		assertTrue(contenu != "");
		PaquetSauvegarde p2 = objectMapper.readValue(contenu, PaquetSauvegarde.class);
		assertTrue(p2.equals(pSauv));
	}
	
	@Test
	public void genererSauvegardeRessource() throws JsonGenerationException, JsonMappingException, IOException {
		String contenu = "";
		Ressource r = new Ressource();
		RessourceSauvegarde rSauv = new RessourceSauvegarde(r);
		contenu = objectMapper.writeValueAsString(rSauv);
		assertTrue(contenu != "");	
		RessourceSauvegarde rSauv2 = objectMapper.readValue(contenu, RessourceSauvegarde.class);
		assertTrue(rSauv2.equals(rSauv));
	}

}
