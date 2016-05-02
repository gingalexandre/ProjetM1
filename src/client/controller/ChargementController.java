package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import serveur.modele.Partie;
import serveur.modele.service.PartieInterface;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

public class ChargementController implements Initializable{
	
	@FXML
	ComboBox<Integer> listePartie;
	
	@FXML
	Button boutonChargerPartie;
	
	/**
	 * Serveur de jeu
	 */
	Serveur serveur;

	public void initialize(URL location, ResourceBundle resources) {
		serveur = ConnexionManager.getStaticServeur();
		ArrayList<Integer> listeIdPartieSauvegarde = null;
		try {
			listeIdPartieSauvegarde = serveur.getGestionnaireBDD().recupererPartieByName(ConnexionController.nomJoueur);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		listePartie.getItems().addAll(listeIdPartieSauvegarde);
		
	}
	
	/**
	 * Charger une partie
	 * @throws InterruptedException 
	 * @throws RemoteException 
	 */
	public void chargerPartie() throws RemoteException, InterruptedException {
		Integer idPartie = listePartie.getValue();
		serveur.getGestionnaireBDD().chargerPartie(idPartie);
		PartieInterface partieChargee = serveur.getGestionnairePartie().recupererPartieChargee();
		serveur.getGestionnaireUI().setPlateau(partieChargee.getPlateau());
	}
}
