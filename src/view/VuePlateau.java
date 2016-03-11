package view;

import java.util.ArrayList;

import commun.DistributeurMap;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import modeles.Plateau;
import modeles.Point;
import modeles.Route;
import modeles.Ville;

public class VuePlateau {

    Scene scene;
    ArrayList<Line> routeConstructible = new ArrayList<>();
    ArrayList<Ville> villeConstructible = new ArrayList<>();
    Plateau plateau;


   

    public VuePlateau() {
        Pane p = new Pane();
        scene = new Scene(p, 662, 600);
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

    

    public Paint getPaintValue(int i) {
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
                res = new ImagePattern(new Image(DistributeurMap.mer));
                break;
            case 18:
                res = new ImagePattern(new Image(DistributeurMap.dunevoleur));
                break;
            default:
                res = new ImagePattern(DistributeurMap.getInstance().donnerImage());
        }
        return res;
    }

}
