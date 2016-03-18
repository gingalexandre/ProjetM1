package client.commun;
import java.util.ArrayList;

import client.view.VuePlateau;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
	    VuePlateau p = new VuePlateau();
	    Scene scene = p.getScene();
	    stage.setTitle("Plateau de jeu");
	    stage.setScene(scene);
	    stage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}
	
}
