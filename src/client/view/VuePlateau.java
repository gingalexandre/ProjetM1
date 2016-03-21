package client.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import client.modele.Plateau;
import client.modele.Point;
import client.modele.Route;
import client.modele.Ville;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class VuePlateau {

    Scene scene;
    ArrayList<Line> routeConstructible = new ArrayList<>();
    ArrayList<Ville> villeConstructible = new ArrayList<>();
    Plateau plateau;


   

    public VuePlateau() {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	double width = screenSize.getWidth();
    	double height = screenSize.getHeight();
    	
        Pane p = new Pane();
        scene = new Scene(p, width, height);
        plateau = Plateau.getInstance();
        p.getChildren().addAll(VueHexagone.transformVueHexagone(plateau.getHexagones()));
        Circle[] t = Ville.transformVilleVueVille(plateau.getVilles());
        p.getChildren().addAll(t);
        p.getChildren().addAll(Route.transformRouteVueRoute(plateau.getRoutes()));
        p.setStyle("-fx-background-color: #CEE4FF");

    }

   
 

    public Scene getScene() {
        return scene;
    }

    

    /*public Paint getPaintValue(int i) {
        Paint res = null;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
            case 9:
            case 14:
            case 15:
            case 21:
            case 22:
            case 27:
            case 28:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
                res = new ImagePattern(new Image(VueHexagone.mer));
                break;
            case 18:
                res = new ImagePattern(new Image(VueHexagone.dunevoleur));
                break;
            default:
                res = VueHexagone.getPaint(i);
        }
        return res;
    }*/
    

}