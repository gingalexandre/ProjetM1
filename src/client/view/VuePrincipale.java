package client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		FXMLLoader preloader = new FXMLLoader(getClass().getResource("fxml/PageAccueil.fxml"));
		
		Parent root = preloader.load();
		
        scene = new Scene(root,925,715);
    
        stage.setTitle("Les Colons de Catanes");
        stage.setScene(scene);   
        stagePrincipal = stage;
        stage.show();
	}

	
}
