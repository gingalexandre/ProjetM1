package client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import client.view.VuePageAccueil;
import client.view.VuePrincipale;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class PageAccueilController implements Initializable{
	
	@FXML
	public Pane panePageAccueil;
	
	@FXML
	public Button boutonJouer, boutonRegles, boutonQuitter;
	
	@FXML
	public ImageView imageCatan;
	
	private static final String cheminImage = "file:Ressources/autres/logo.png";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imageCatan.setImage(new Image(cheminImage));
		Platform.setImplicitExit(false);
	}
	
	/**
	 * Se déclenche lors d'un clic sur le boutton boutonJouer
	 */
	@FXML
	public void jouer(){
		Platform.runLater(() -> {
			 VuePageAccueil.stagePrincipal.close();
			 try {
				new VuePrincipale().start(new Stage());
			 } catch (Exception e) {
				e.printStackTrace();
			 }
         });
	}
	
	/**
	 * Se déclenche lors d'un clic sur le boutton boutonRegles
	 */
	@FXML
	public void regles(){
		VuePrincipale v= new VuePrincipale();
		String path = new java.io.File("" ).getAbsolutePath(); 
		v.getHostServices().showDocument("file://"+ path + "/rules.pdf");
	}
	
	/**
	 * Se déclenche lors d'un clic sur le boutton boutonQuitter
	 */
	@FXML
	public void quitter(){
		System.exit(0);
	}
}