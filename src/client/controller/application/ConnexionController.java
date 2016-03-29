package client.controller.application;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import client.controller.rmi.JoueurServeur;
import client.view.VuePrincipale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import serveur.ConnexionManager;
import serveur.Serveur;

public class ConnexionController implements Initializable {

	@FXML
	private TextField nomUtilisateur;
	
	@FXML
	private PasswordField mdp;
	
	@FXML
	private Button connexion;
	
	@FXML
	private Label utilisateurErreur;
	
	private Pane page = null;
	private FXMLLoader inscriptionChargement;
	public static Stage inscriptionFenetre, gameFenetre;
	
	public static String nomJoueur;
	
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	/**
	 * Méthode vérifiant la connexion. Si elle fonctionne, alors la méthode lance le jeu
	 * @throws RemoteException 
	 */
	@FXML
	public void connexion() throws RemoteException {
		boolean connexionOk = false;
		Serveur serveur = null;
		try {
			serveur = ConnexionManager.getStaticServeur();
			connexionOk = serveur.verificationConnexion(nomUtilisateur.getText(), mdp.getText());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(connexionOk){
			nomJoueur = nomUtilisateur.getText(); 
			JoueurServeur joueur = ConnexionManager.getStaticProxy();
			joueur.setNomUtilisateur(nomJoueur);
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
			inscriptionFenetre.setTitle("FenÃªtre d'inscription");
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
