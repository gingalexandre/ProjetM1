package client.controller;

import java.io.IOException;
import java.rmi.RemoteException;

import client.view.VuePrincipale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Serveur;

public class ReglesController {
	
	@FXML
	private Button boutonRegles;
	@FXML
	private Button boutonSatistiques;
	@FXML
	private Button boutonsauvegarde;
	@FXML
	private Button boutonStatistiques;
	@FXML
	private Button boutonSauvegarde;
	
	private Pane page = null;
	public static Stage statsFenetre;
	
	public void afficherRegles(){
				VuePrincipale v= new VuePrincipale();
				String path = new java.io.File("" ).getAbsolutePath(); 
				v.getHostServices().showDocument("file://"+ path + "/rules.pdf");
	}
	
	@FXML
	private void voirStatistiques(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/Statistiques.fxml"));
		try {
			page = (Pane) loader.load();
			statsFenetre = new Stage();
			statsFenetre.setTitle("Les Colons de Catanes");
		    Scene scene = new Scene(page,430,500);
		    statsFenetre.setScene(scene);
		    statsFenetre.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void sauvegarderPartie(){
		Serveur serveur = null;
		serveur = ConnexionManager.getStaticServeur();
		try {
			serveur.enregistrerPartie();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
