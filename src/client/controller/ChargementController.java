package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

public class ChargementController implements Initializable{
	
	@FXML
	ComboBox<Integer> listePartie;
	
	@FXML
	Button boutonChargerPartie;

	public void initialize(URL location, ResourceBundle resources) {
		Serveur serveur = ConnexionManager.getStaticServeur();
		ArrayList<Integer> listeIdPartieSauvegarde = null;
		try {
			listeIdPartieSauvegarde = serveur.getGestionnaireBDD()
					.recupererPartieByName(ConnexionController.nomJoueur);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listePartie.getItems().addAll(listeIdPartieSauvegarde);
		
	}
	
	/**
	 * Charger une partie
	 */
	public void chargerPartie() {
		
	}

}
