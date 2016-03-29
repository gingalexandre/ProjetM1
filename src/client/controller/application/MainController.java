package client.controller.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import serveur.ConnexionManager;
import serveur.Serveur;

/**
 * Controller principal
 * @author jerome
 */
public class MainController implements Initializable{
	
	@FXML
	public VBox plateau, chat, des, joueurActuel; 
	
	public Serveur serveur;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Connexion au serveur
		ConnexionManager.getInstance();		
	}
}