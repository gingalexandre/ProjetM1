package client.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author jerome
 */
public class VuePrincipale extends Application{
	
	public static Scene scene;
	public static Stage stagePrincipal;
	public static Pane paneUsed;

	
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("BEFORE VUE PRINCIPALE");
		FXMLLoader preloader = new FXMLLoader(getClass().getResource("fxml/Accueil.fxml"));
		
		Parent root = preloader.load();
		
        scene = new Scene(root,0,0);

        stage.setTitle("Les Colons de Catanes");
        stage.setScene(scene);   
        stage.setMaximized(true);
        stagePrincipal = stage;
        stage.show();
		System.out.println("AFTER VUE PRINCIPALE");
	}	
}
