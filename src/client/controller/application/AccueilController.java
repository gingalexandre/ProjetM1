package client.controller.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.view.VuePrincipale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import serveur.bdd.Utilisateur;

public class AccueilController implements Initializable {

	@FXML
	private TextArea utilisateur;
	
	@FXML
	private PasswordField mdp;
	
	@FXML
	private Button inscription, connexion;
	
	@FXML
	private Label utilisateurErreur;
	
	private Pane page = null;
	private FXMLLoader inscriptionChargement, gameChargement;
	public static Stage inscriptionFenetre, gameFenetre;
	
	public static String nomJoueur;
	
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	/**
	 * Méthode vérifiant la connexion. Si elle fonctionne, alors la méthode lance le jeu
	 */
	@FXML
	public void connexion() {
		Utilisateur user = new Utilisateur(utilisateur.getText(), mdp.getText());
		boolean connexionOk = false;
		try {
			connexionOk = user.verificationConnexion();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(connexionOk);
		if(connexionOk){
			
			nomJoueur = utilisateur.getText(); 
			
			lancerJeu();
			
		}
		else{
			utilisateurErreur.setText("Erreur, utilisateur inconnu, inscrivez-vous.");
		}
	}
	
	/**
	 * Méthode permettant de lancer le jeu une fois connecté
	 */
	public void lancerJeu(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
		try {
			page = (AnchorPane) loader.load();
			gameFenetre = new Stage();
			gameFenetre.setTitle("Les Colons de Catanes");
		    Scene scene = new Scene(page,0,0);
		    gameFenetre.setFullScreen(true);
		    gameFenetre.setScene(scene);
		    VuePrincipale.stagePrincipal.close();
		    gameFenetre.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant d'afficher la fenêtre d'inscription
	 */
	@FXML
	public void inscription() {
		inscriptionChargement = new FXMLLoader(getClass().getResource("/fxml/Inscription.fxml"));
		try {
			page = (Pane) inscriptionChargement.load();
			inscriptionFenetre = new Stage();
			inscriptionFenetre.setTitle("Fenêtre d'inscription");
			inscriptionFenetre.initModality(Modality.WINDOW_MODAL);
		    Scene miniScene = new Scene(page);
		    inscriptionFenetre.setScene(miniScene);
		    
		    inscriptionFenetre.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}


}
