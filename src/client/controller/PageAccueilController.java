package client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class PageAccueilController implements Initializable{
	
	@FXML
	public Button boutonJouer, boutonRegles;
	
	@FXML
	public ImageView imageCatan;
	
	private static final String cheminImage = "file:Ressources/autres/logo.png";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imageCatan.setImage(new Image(cheminImage));
	}
}
