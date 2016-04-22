package client.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

public class PropositionController implements Initializable {
	
	@FXML
	private Label propostion, valeurs;
	
	@FXML
	private Button accepter, refuser;
	
	private Proxy proxy;

	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Initialisation du proxy
		proxy = ConnexionManager.getStaticProxy();
		proxy.setPropositionController(this);
		
		serveur = ConnexionManager.getStaticServeur();
	}
	
	public void accepterOffre(){
		
	}
	
	public void refuserOffre(){
		
	}

}
