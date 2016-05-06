package client.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import client.view.VuePrincipale;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

public class ReglesController implements Initializable{
	
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
	
	/**
	 * Fenêtre de stat
	 */
	public static Stage statsFenetre;
	
	/**
	 * Proxy client
	 */
	private Proxy proxy;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Initialisation du proxy
		proxy = ConnexionManager.getStaticProxy();
		try {
			proxy.setReglesController(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void afficherRegles(){
		VuePrincipale v= new VuePrincipale();
		v.getHostServices().showDocument(getClass().getResource("/rules.pdf").toExternalForm());
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
	
	/**
	 * Active/Desactive le bouton de sauvegarde
	 * @param boo
	 */
	public void setButtonsSauvegarde(boolean boo) {
		Platform.runLater(() -> boutonSauvegarde.setDisable(boo));
	}
	
	/**
	 * Se déclenche quand on appuie sur le bouton pour sauvegarder la partie
	 */
	@FXML
	private void sauvegarderPartie(){
		Serveur serveur = null;
		serveur = ConnexionManager.getStaticServeur();
		try {
			serveur.enregistrerPartie();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
