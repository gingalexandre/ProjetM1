package serveur.bdd;

import java.io.File;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import serveur.modele.Hexagone;
import serveur.modele.Joueur;
import serveur.modele.Plateau;
import serveur.modele.Point;

public class Sauvegarde {
	private static Plateau plateauCourant;
	private static Joueur[] joueurs;
	private static Joueur currentJoueur = null;
	private static ObjectMapper objectMapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.ANY);
;
	private static File jsonOutputFile;

	
	public Sauvegarde(){
		plateauCourant = Plateau.getInstance();
		//System.out.println(plateauCourant);
		try {
			objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
			String jsonString = objectMapper.writeValueAsString(plateauCourant);
			System.out.println(jsonString);
		}catch (Exception e){
			e.printStackTrace();
		}
		/*
		joueurs = Partie.getJoueurs();
		// TODO : Ici avoir le Joueur Courant
		// currentJoueur = Partie.getCurrentJoueur();
		jsonOutputFile = new File("results.json");
		try {
			objectMapper.writeValue(
				    new FileOutputStream("results.json"), new Point(1.0,2.0));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	public void sauvegarderPartie(){
		/*
		jsonOutputFile = new File("results.json");
		try {
			objectMapper.writeValue(jsonOutputFile, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		*/
	}

	public static Plateau getPlateauCourant() {
		return plateauCourant;
	}

	public static void setPlateauCourant(Plateau plateauCourant) {
		Sauvegarde.plateauCourant = plateauCourant;
	}

	public static Joueur[] getJoueurs() {
		return joueurs;
	}

	public static void setJoueurs(Joueur[] joueurs) {
		Sauvegarde.joueurs = joueurs;
	}

	public static Joueur getCurrentJoueur() {
		return currentJoueur;
	}

	public static void setCurrentJoueur(Joueur currentJoueur) {
		Sauvegarde.currentJoueur = currentJoueur;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		Sauvegarde.objectMapper = objectMapper;
	}

	public File getJsonOutputFile() {
		return jsonOutputFile;
	}

	public void setJsonOutputFile(File jsonOutputFile) {
		Sauvegarde.jsonOutputFile = jsonOutputFile;
	}
	
	

}
