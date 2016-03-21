package client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import client.modele.Plateau;
import client.modele.Route;
import client.modele.Ville;
import client.view.VueHexagone;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class plateauController implements Initializable{

	@FXML
	Pane mainPane;
	
	Plateau plateau;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		plateau = Plateau.getInstance();
		mainPane.getChildren().addAll(VueHexagone.transformVueHexagone(plateau.getHexagones()));
        Circle[] t = Ville.transformVilleVueVille(plateau.getVilles());
        mainPane.getChildren().addAll(t);
        mainPane.getChildren().addAll(Route.transformRouteVueRoute(plateau.getRoutes()));
        mainPane.setStyle("-fx-background-color: #CEE4FF");
	}

}
