package client.controller.application;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import client.controller.rmi.Joueur;
import client.modele.Plateau;
import client.modele.Route;
import client.modele.Ville;
import client.view.VueHexagone;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import serveur.ConnexionManager;
import serveur.Serveur;

/**
 * Controller du plateau
 * @author jerome
 */
public class PlateauController implements Initializable{

	@FXML
	public Pane mainPane;
	
	/**
	 * Plateau de jeu
	 */
	private Plateau plateau;
	
	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;
	
	/**
	 * Proxy avec le serveur
	 */
	private Joueur proxy;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recupererAttributs();
		enregistrerController();
		try {
			recupererPlateau();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		Platform.runLater(() -> dessinerPlateau());
	}

	
	/**
	 * Récupère les attributs de ConnexionManager
	 */
	private void recupererAttributs() {
		this.serveur = ConnexionManager.getStaticServeur();
		this.proxy = ConnexionManager.getStaticProxy();
	}

	/**
	 * Indique au proxy que cette classe est le controller du plateau
	 */
	private void enregistrerController() {
		Joueur joueur = ConnexionManager.getStaticProxy();
		joueur.setPlateauController(this);
	}

	/**
	 * Demande le plateau au serveur
	 * @throws RemoteException 
	 */
	private void recupererPlateau() throws RemoteException {
		serveur.envoyerPlateau(proxy);
	}
	
	/**
	 * Dessine le plateau
	 * @throws RemoteException 
	 */
	public void dessinerPlateau() {
		Image img = new Image("file:Ressources/cases/mer.png");
		ImageView imgView = new ImageView(img);
		imgView.setFitHeight(725);
		imgView.setFitWidth(650);
		imgView.setLayoutX(50);
		mainPane.getChildren().add(imgView);
		mainPane.getChildren().addAll(VueHexagone.transformVueHexagone(plateau.getHexagones()));
        Circle[] t = Ville.transformVilleVueVille(plateau.getVilles());
        mainPane.getChildren().addAll(t);
        mainPane.getChildren().addAll(Route.transformRouteVueRoute(plateau.getRoutes()));
        mainPane.setStyle("-fx-background-color: #4e6c91");
	}
	
	/**
	 * Permet de set l'attribut plateau
	 * @param plateau - Plateau envoyé par le proxy RMI 
	 */
	public void setPlateau(Plateau plateau){
		this.plateau = plateau;
	}
}
