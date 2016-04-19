package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

public class StatistiquesController implements Initializable {

	@FXML
	private Label gagnee, jouee, pourcentage;
	
	@FXML
	private Button ok;
	
	/**
	 * Méthode d'initialisation et qui récupère et affiche les stats du Joueur
	 */
	public void initialize(URL location, ResourceBundle resources) {
		Serveur serveur = null;
		serveur = ConnexionManager.getStaticServeur();
		Integer[] stats = new Integer[2];
		try {
			stats = serveur.getGestionnaireBDD().getStatistiques(ConnexionController.nomJoueur);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		jouee.setText(Integer.toString(stats[0]));
		gagnee.setText(Integer.toString(stats[1]));
		
		// Dans le cas d'un nouveau joueur pour éviter la division par 0
		if(stats[0] != 0){
			pourcentage.setText(Integer.toString(stats[1] * 100 / stats[0]) + " %");
		}
		else{
			pourcentage.setText("0 %");
		}
		
	}
	
	/**
	 * Méthode pour fermer la fenêtre de stats
	 */
	@FXML
	private void fermerFenetre(){
		ReglesController.statsFenetre.close();
	}
	
	
}
