package client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import serveur.reseau.ConnexionManager;

/**
 * Controller principal
 * @author jerome
 */
public class MainController implements Initializable{
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Connexion au serveur
		ConnexionManager.getInstance();		
	}
}
