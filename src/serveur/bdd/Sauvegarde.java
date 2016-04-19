package serveur.bdd;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import serveur.modele.Joueur;

public class Sauvegarde {
	private static Joueur currentJoueur = null;
	private static ObjectMapper objectMapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.ANY);
;
	private static File jsonOutputFile;

	
	public Sauvegarde() throws RemoteException{
		PartieSauvegarde partieASauvegarder = new PartieSauvegarde();
		try {
			jsonOutputFile = new File("results.json");
			objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
			objectMapper.writeValue(new FileOutputStream("results.json"),partieASauvegarder);
			
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
