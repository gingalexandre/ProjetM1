package serveur.bdd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import serveur.modele.Joueur;
import serveur.modele.Partie;
import serveur.modele.Plateau;
import serveur.modele.Point;

public class Sauvegarde {
	private static Plateau plateauCourant;
	private static Joueur[] joueurs;
	private static Joueur currentJoueur = null;
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static File jsonOutputFile;

	
	public Sauvegarde(){
		plateauCourant = Plateau.getInstance();
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
		}
		
	}
	
	public void sauvegarderPartie(){
		
		jsonOutputFile = new File("results.json");
		try {
			objectMapper.writeValue(jsonOutputFile, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
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
