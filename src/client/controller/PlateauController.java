package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import serveur.modele.*;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;
import serveur.reseau.Serveur;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import serveur.view.VueHexagone;
import serveur.modele.Jeton;
import serveur.modele.Plateau;
import serveur.modele.Route;
import serveur.modele.Ville;
import serveur.view.VueJeton;

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
	private Proxy proxy;

	/**
	 * Group rassemblant les hexagones dans le mainPane
	 */
	private Group hexagones;

	/**
	 * Group rassemblant les villes dans le mainPane
	 */
	private Group villes;

	/**
	 * Group rassemblant les routes dans le mainPane
	 */
	private Group routes;

	/**
	 * Group rassemblant les routes dans le mainPane
	 */
	private Group jetons;

	/**
	 *
	 * @param location
	 * @param resources
     */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recupererAttributs();
		enregistrerController();
		mainPane.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event){
				boolean trouve = false;
				int depart = plateau.getHexagones().indexOf(plateau.getVoleur());
				Point2D point = new Point2D(event.getX(),event.getY());
				int i = 0;
				for (Hexagone hex: plateau.getHexagones()) {
					Polygon polygon = new Polygon();
					polygon.getPoints().addAll(hex.getPoints());
					if(polygon.contains(point)){
						plateau.getVoleur().setVOLEUR(false);
						plateau.getHexagones().get(i).setVOLEUR(true);
						trouve = true;
						break;
					}else{
						i++;
					}
				}
				//mainPane.getChildren().clear();
				int arrive = i;
				if(trouve) Platform.runLater(() -> deplaceVoleur(depart,arrive));
			}
		});
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
		Proxy proxy = ConnexionManager.getStaticProxy();
		proxy.setPlateauController(this);
	}

	/**
	 * Demande le plateau au serveur
	 * @throws RemoteException 
	 */
	private void recupererPlateau() throws RemoteException {
		serveur.getGestionnaireUI().envoyerPlateau(proxy);
	}
	
	/**
	 * Dessine le plateau
	 * @throws RemoteException 
	 */
	public void dessinerPlateau() {
		//Dessin du fond
		Image img = new Image("file:Ressources/cases/mer.png");
		ImageView imgView = new ImageView(img);
		imgView.setFitHeight(725);
		imgView.setFitWidth(650);
		imgView.setLayoutX(50);
		mainPane.getChildren().add(imgView);

		// Ajout des hexagones
		hexagones = new Group();
		hexagones.getChildren().addAll(VueHexagone.transformVueHexagone(plateau.getHexagones()));

		//Ajout des villes
		villes = new Group();
        Circle[] t = Ville.transformVilleVueVille(plateau.getVilles());
		villes = new Group();
		villes.getChildren().addAll(t);

		//Ajout des routes
		routes = new Group();
        routes.getChildren().addAll(Route.transformRouteVueRoute(plateau.getRoutes()));

		//Ajout des jetons
		jetons = new Group();
		Circle[] jetonsDessine = Jeton.transformVueJeton(plateau.getJetons());
		jetons.getChildren().addAll(jetonsDessine);

		// Construction du Pane principal
		mainPane.getChildren().add(hexagones);
		mainPane.getChildren().add(villes);
		mainPane.getChildren().add(routes);
		mainPane.getChildren().add(jetons);
        mainPane.setStyle("-fx-background-color: #4e6c91");

	}
	
	/**
	 * Permet de set l'attribut plateau
	 * @param plateau - Plateau envoy� par le proxy RMI 
	 */
	public void setPlateau(Plateau plateau){
		this.plateau = plateau;
	}


	/**
	 * Update des hexagones concerné par le changement du voleur
	 * @param depart hexagone de départ
	 * @param arrive hexagone d'arrivé
	 */
	public void deplaceVoleur(int depart, int arrive){
		ColorAdjust colorAdjust = new ColorAdjust();
		hexagones.getChildren().get(depart).setEffect(null);
		colorAdjust.setSaturation(-1);
		hexagones.getChildren().get(arrive).setEffect(colorAdjust);
	}
}
