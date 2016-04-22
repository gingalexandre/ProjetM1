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

import serveur.bdd.modeleSauvegarde.HexagoneSauvegarde;
import serveur.bdd.modeleSauvegarde.JetonSauvegarde;
import serveur.bdd.modeleSauvegarde.JoueurSauvegarde;
import serveur.bdd.modeleSauvegarde.RouteSauvegarde;
import serveur.bdd.modeleSauvegarde.VilleSauvegarde;
import serveur.modele.Hexagone;
import serveur.modele.Jeton;
import serveur.modele.Joueur;
import serveur.modele.Point;
import serveur.modele.Route;
import serveur.modele.Ville;
import serveur.modele.carte.Carte;

public class TestSauvegarde {
	private static ObjectMapper objectMapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.ANY);;

	@Test
	public void genererSauvegardeHexagone() throws JsonGenerationException, JsonMappingException, IOException {
		Hexagone hex = new Hexagone(1.0, 2.0, 3, 1);
		HexagoneSauvegarde hexSauv = new HexagoneSauvegarde(hex);
		String contenu = "";
		contenu = objectMapper.writeValueAsString(hexSauv);
		assertTrue(contenu != "");
	}

	@Test
	public void genererSauvegardeJeton() throws JsonGenerationException, JsonMappingException, IOException {
		Hexagone hexagone = new Hexagone(1.0, 2.0, 3, 1);
		Jeton jeton = new Jeton(hexagone);
		JetonSauvegarde jetonSauv = new JetonSauvegarde(jeton);
		String contenu = "";
		contenu = objectMapper.writeValueAsString(jetonSauv);
		assertTrue(contenu != "");
	}

	@Test
	public void genererSauvegardeVille() throws JsonGenerationException, JsonMappingException, IOException {
		Ville ville = new Ville(new Point(1.0, 2.0));
		VilleSauvegarde villeSauv = new VilleSauvegarde(ville);
		String contenu = "";
		contenu = objectMapper.writeValueAsString(villeSauv);
		assertTrue(contenu != "");
	}

	@Test
	public void genererSauvegardeRoute() throws JsonGenerationException, JsonMappingException, IOException {
		Route route = new Route(new Point(1.0, 2.0), new Point(3.0, 4.0));
		RouteSauvegarde routeSauv = new RouteSauvegarde(route);
		String contenu = "";
		contenu = objectMapper.writeValueAsString(routeSauv);
		assertTrue(contenu != "");
	}

	@Test
	public void genererSauvegardeJoueur() throws JsonGenerationException, JsonMappingException, IOException {
		Joueur joueur = new Joueur(1, "toto", Date.valueOf(LocalDate.now()), "bleu", true, 0, 0, 0, 0,
				new HashMap<Integer, Integer>(), new ArrayList<Carte>());
		JoueurSauvegarde joueurSauv = new JoueurSauvegarde(joueur);
		String contenu = "";
		contenu = objectMapper.writeValueAsString(joueurSauv);
		assertTrue(contenu != "");
	}

}
