package serveur.bdd.modeleBDD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import serveur.bdd.modeleSauvegarde.PartieSauvegarde;
import serveur.modele.Joueur;
import serveur.modele.Message;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

/**
 * Classe permettant la sauvegarde de la partie
 * 
 * @author Alexandre
 */
public class Sauvegarde {

	/**
	 * Joueur courant
	 */
	private static Joueur currentJoueur = null;

	/**
	 * Objet permettant la sérialisation
	 */
	private static ObjectMapper objectMapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.ANY);;

	/**
	 * Objet représentatant le fichier de sortie
	 */
	private static File jsonOutputFile;

	private static serveur.modele.Partie partieChargee;

	/**
	 * Constructeur de la partie
	 * 
	 * @throws RemoteException
	 */
	public Sauvegarde() throws RemoteException {
		PartieSauvegarde partieASauvegarder = new PartieSauvegarde(true);
		Partie partieActuelle = null;
		Serveur serveur = ConnexionManager.getStaticServeur();
		try {
			// Recherche si la partie existe déjà
			partieActuelle = Partie.getById(partieASauvegarder.getIdPartie());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Dans le cas où elle existe déjà
		if (partieActuelle != null) {
			this.sauvegarderPartie(partieASauvegarder, partieActuelle.getPath());
		} else {
			// Récupération de la date précise pour avoir un minimum de
			// collision entre les fichiers
			String format = "dd_MM_yy_H_mm_ss";

			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
			java.util.Date date = new java.util.Date();
			String path = new java.io.File("").getAbsolutePath() + "/sauvegardes/"
					+ partieASauvegarder.getJoueurActuel().getNomUtilisateur() + formater.format(date) + ".json";
			jsonOutputFile = new File(path);
			objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
			String contenu = "";
			try {
				// Sérialisation du contenu en String pour faire le checksum
				contenu = objectMapper.writeValueAsString(partieASauvegarder);
				partieActuelle = new Partie(partieASauvegarder.getIdPartie(), path, contenu);
				// Insertion de la partie dans la BDD
				partieActuelle.insererPartie(partieASauvegarder.getJoueurs());
				// Écriture dans le fichier
				this.sauvegarderPartie(partieASauvegarder, path);
				// Récupération de l'id de la partie nouvellement insérée
				Partie partie = Partie.getPartieByPath(path);
				
				serveur.getGestionnairePartie().getPartie().setId(partie.getIdPartie());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			serveur.getGestionnaireUI().diffuserMessage(new Message("La partie vient d'être sauvegardée."));
			
		}

	}

	/**
	 * Méthode permettant d'écrire la Partie dans un fichier au format JSON
	 * 
	 * @param partieASauvegarder
	 *            - partie que l'on souhaite sauvegarder
	 * @param path
	 *            - chemin où le fichier va se trouver
	 */
	public void sauvegarderPartie(PartieSauvegarde partieASauvegarder, String path) {
		try {
			jsonOutputFile = new File(path);
			String pathDossier = new java.io.File("").getAbsolutePath() + "/sauvegardes/";
			File testDossier = new File(pathDossier);
			if(!testDossier.exists()){
				testDossier.mkdirs();
			}
			objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
			objectMapper.writeValue(new FileOutputStream(path), partieASauvegarder);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getter du Joueur Courant
	 * 
	 * @return Joueur
	 */
	public static Joueur getCurrentJoueur() {
		return currentJoueur;
	}

	/**
	 * Setter du Joueur Courant
	 * 
	 * @param currentJoueur
	 *            - joueur courrant
	 */
	public static void setCurrentJoueur(Joueur currentJoueur) {
		Sauvegarde.currentJoueur = currentJoueur;
	}

	/**
	 * Getter de l'objet permettant la sérialisation
	 * 
	 * @return ObjectMapper
	 */
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	/**
	 * Setter de l'objet permettant la sérialisation
	 * 
	 * @param ObjectMapper
	 *            - objectMapper
	 */
	public void setObjectMapper(ObjectMapper objectMapper) {
		Sauvegarde.objectMapper = objectMapper;
	}

	/**
	 * Getter de l'objet représentant le fichier de sortie
	 * 
	 * @return File
	 */
	public File getJsonOutputFile() {
		return jsonOutputFile;
	}

	/**
	 * Setter de l'objet représentant le fichier de sortie
	 * 
	 * @param File
	 *            - jsonOutputFile
	 */
	public void setJsonOutputFile(File jsonOutputFile) {
		Sauvegarde.jsonOutputFile = jsonOutputFile;
	}

	/**
	 * Méthode a appeler pour désérialiser
	 */
	public static void chargerPartie(int id) {
		try {
			Partie partieSauvegarde = Partie.getById(id);
			InputStream flux = new FileInputStream(partieSauvegarde.getPath());
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			StringBuffer json = new StringBuffer();
			String ligne;
			// Lecture du contenu
			while ((ligne = buff.readLine()) != null) {
				json.append(ligne);
			}
			buff.close();
			String res = new String(json);
			// Déserialisation
			PartieSauvegarde partieACharger = PartieSauvegarde.deserialiser(res);
			partieChargee = new serveur.modele.Partie(partieACharger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static serveur.modele.Partie getPartieChargee() {
		return partieChargee;
	}
}
