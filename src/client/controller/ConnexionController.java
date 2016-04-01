package client.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ResourceBundle;

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
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;
import serveur.reseau.Serveur;

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
	
	public Date dateNaissance;
	
	public static String nomJoueur;
	
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	/**
	 * M�thode v�rifiant la connexion. Si elle fonctionne, alors la m�thode lance le jeu
	 * @throws RemoteException 
	 */
	@FXML
	public void connexion() throws RemoteException, InterruptedException {
		boolean connexionOk = false;
		Serveur serveur = null;
		try {
			serveur = ConnexionManager.getStaticServeur();
			connexionOk = serveur.verificationConnexion(nomUtilisateur.getText(), mdp.getText());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Si le Joueur existe
		if(connexionOk){
			nomJoueur = nomUtilisateur.getText(); 
			Proxy joueur = ConnexionManager.getStaticProxy();
			joueur.setNomUtilisateur(nomJoueur);
			dateNaissance = joueur.getDateNaissance(nomJoueur);
			lancerJeu();
		}
		else{
			utilisateurErreur.setText("Erreur, utilisateur inconnu, inscrivez-vous.");
		}
	}
	
	/**
	 * M�thode permettant de lancer le jeu une fois connect�
	 */
	public void lancerJeu(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/Game.fxml"));
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
	 * M�thode permettant d'afficher la fen�tre d'inscription
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
