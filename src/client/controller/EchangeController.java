package client.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;
import serveur.reseau.Serveur;

/**
*
* @author Arthur
*/
public class EchangeController implements Initializable {

	@FXML
	private Button boutonPropositionOffre;
	
	@FXML
	private ImageView imgBois, imgLaine, imgMineraie, imgArgile, imgBle;
	
	private Proxy proxy;
	
	
	/**
	 * Méthode d'initialisation
	 */
	public void initialize(URL location, ResourceBundle resources) {
		proxy = ConnexionManager.getStaticProxy();
		proxy.setEchangeController(this);
		
		imgLaine.setImage(new Image("file:Ressources/ressources/ressource_laine.png"));
		imgBois.setImage(new Image("file:Ressources/ressources/ressource_Bois.png"));
		imgMineraie.setImage(new Image("file:Ressources/ressources/ressource_Mineraie.png"));
		imgArgile.setImage(new Image("file:Ressources/ressources/ressource_Argile.png"));
		imgBle.setImage(new Image("file:Ressources/ressources/ressource_Ble.png"));
	}
	
	/**
	 * Méthode pour fermer la fenêtre des échanges
	 */
	@FXML
	private void proposerOffre(){
		//TODO méthode de proposition (pourquoi pas une popup de demande de validation au joueur concerné)
		MenuController.fenetreEchange.close();
	}
	
	
}
