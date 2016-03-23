package client.controller.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import serveur.ConnexionManager;
import serveur.Serveur;

/**
 * 
 * @author jerome
 */
public class MainApplicationController implements Initializable{
	
	@FXML
	public VBox plateau, chat; 
	
	public Serveur serveur;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ConnexionManager.getInstance();
	}
}
