package serveur.bdd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import serveur.modele.Joueur;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Serveur;

public class Sauvegarde {
	private static Joueur currentJoueur = null;
	private static ObjectMapper objectMapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.ANY);;
	private static File jsonOutputFile;

	
	public Sauvegarde() throws RemoteException{
		PartieSauvegarde partieASauvegarder = new PartieSauvegarde();
		Partie partieActuelle = null;
		try {
			partieActuelle = Partie.getById(partieASauvegarder.getIdPartie());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (partieActuelle != null) {
			this.sauvegarderPartie(partieASauvegarder, partieActuelle.getPath());
		}
		else{
			String path = new java.io.File("" ).getAbsolutePath()+"/sauvegardes/"+partieASauvegarder.getJoueurActuel().getNomUtilisateur()+LocalDate.now().toString(); 
			jsonOutputFile = new File(path);
			objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
			String contenu = "" ;
			try {
				contenu = objectMapper.writeValueAsString(partieASauvegarder);
				partieActuelle = new Partie(partieASauvegarder.getIdPartie(), path, contenu);
				partieActuelle.insererPartie(partieASauvegarder.getJoueurs());
				this.sauvegarderPartie(partieASauvegarder, path);
				Partie partie = Partie.getIdByPath(path);
				Serveur serveur = ConnexionManager.getStaticServeur();
				serveur.getGestionnairePartie().getPartie().setId(partie.getIdPartie());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void sauvegarderPartie(PartieSauvegarde partieASauvegarder, String path){
		
		try {
			jsonOutputFile = new File(path);
			objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
			objectMapper.writeValue(new FileOutputStream(path),partieASauvegarder);
			
		}catch (Exception e){
			e.printStackTrace();
		}
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
